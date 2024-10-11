package com.sist.jobgem.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.IdNameDto;
import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.PostListDto;
import com.sist.jobgem.dto.PostSetDto;
import com.sist.jobgem.dto.RecruitRequest;
import com.sist.jobgem.entity.Post;
import com.sist.jobgem.mapper.PostMapper;
import com.sist.jobgem.repository.ApplymentRepository;
import com.sist.jobgem.repository.CareerRepository;
import com.sist.jobgem.repository.EducationRepository;
import com.sist.jobgem.repository.HireKindRepository;
import com.sist.jobgem.repository.LocationDoRepository;
import com.sist.jobgem.repository.LocationGuSiRepository;
import com.sist.jobgem.repository.PostRepository;
import com.sist.jobgem.repository.SkillRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ApplymentRepository applymentRepository;

    @Autowired
    private LocationDoRepository locationDoRepository;

    @Autowired
    private LocationGuSiRepository locationGuSiRepository;

    @Autowired
    private HireKindRepository hireKindRepository;

    public Page<PostCountApplyDto> getPosts(Map<String, Object> map) {
        int currentPage = Integer.parseInt(map.get("curPage").toString());
        int pageSize = 10;
        if(map.get("size") != null){
            pageSize = Integer.parseInt(map.get("size").toString());
        }
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<PostCountApplyDto> postList = postRepository.findByFilterWithApplyCount(map, pageable);
        return postList;
    }

    public Map<String, Object> getPostListInfo(int coIdx) {
        Map<String, Object> response = new HashMap<>();
        response.put("progress", postRepository.countByPoStateAndCoIdx(1, coIdx));
        response.put("all", postRepository.countByCoIdx(coIdx));
        response.put("complete", postRepository.countByPoStateAndCoIdx(2, coIdx));
        response.put("deadline", postRepository.countByCoIdxAndPoDeadline(coIdx));
        return response;
    }

    public PostDto getPost(int poIdx) {
        Post post = postRepository.findById(poIdx);
        return PostMapper.INSTANCE.toDto(post);
    }

    public int create(PostDto postDto) {
        postDto.setPoState(1);
        Post post = PostMapper.INSTANCE.toEntity(postDto);
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updatePostState() {
        postRepository.updateStateByDeadline();
    }

    public PostSetDto getPostSet() {
        PostSetDto dto = new PostSetDto();

        dto.setEducation(educationRepository.findAll().stream().map(education -> {
            IdNameDto idNameDto = new IdNameDto();
            idNameDto.setId(education.getId());
            idNameDto.setName(education.getEdName());
            return idNameDto;
        }).collect(Collectors.toList()));

        dto.setHiringType(hireKindRepository.findAll().stream().map(hireKind -> {
            IdNameDto idNameDto = new IdNameDto();
            idNameDto.setId(hireKind.getId());
            idNameDto.setName(hireKind.getHkName());
            return idNameDto;
        }).collect(Collectors.toList()));

        dto.setCareer(careerRepository.findAll().stream().map(career -> {
            IdNameDto idNameDto = new IdNameDto();
            idNameDto.setId(career.getId());
            idNameDto.setName(career.getCrName());
            return idNameDto;
        }).collect(Collectors.toList()));

        dto.setSkill(skillRepository.findAll().stream().map(skill -> {
            IdNameDto idNameDto = new IdNameDto();
            idNameDto.setId(skill.getId());
            idNameDto.setName(skill.getSkName());
            return idNameDto;
        }).collect(Collectors.toList()));

        dto.setLocationDo(locationDoRepository.findAll());

        dto.setLocationGuSi(locationGuSiRepository.findAll());

        return dto;
    }

    public void setPostList(PostListDto postListDto, int coIdx) {
        postListDto.setProgress(postRepository.countByPoStateAndCoIdx(1, coIdx));
        postListDto.setAll(postRepository.countByCoIdx(coIdx));
        postListDto.setComplete(postRepository.countByPoStateAndCoIdx(2, coIdx));
        postListDto.setDeadline(postRepository.countByCoIdxAndPoDeadline(coIdx));
    }

    public Slice<PostDto> getPostList(Pageable pageable) {
        Slice<PostDto> postList = postRepository.getPostListSlice(pageable);
        return postList;
    }

    public Map<String, Object> getDetail(int id) {
        Map<String, Object> response = new HashMap<>();
        response.put("title", postRepository.findTitleById(id));
        response.put("applyCount", applymentRepository.countByPoIdx(id));
        response.put("viewCount", applymentRepository.countByPoIdxAndApReadAndJobseekerUserUsState(id, 1,1));
        response.put("unviewCount", applymentRepository.countByPoIdxAndApReadAndJobseekerUserUsState(id, 0,1));
        return response;
    }

    public int delete(int id) {
        return postRepository.updateStateById(id);
    } 

    public Page<PostDto> searchPosts(String keyword, int curPage) {
        int pageSize = 12;
        Pageable pageable = PageRequest.of(curPage, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> posts = postRepository.findByPoTitleContainsAndPoState(keyword, 1, pageable);
        List<PostDto> postDtos = PostMapper.INSTANCE.toDtoList(posts.getContent());

        return new PageImpl<>(postDtos, pageable, posts.getTotalElements());
    }

    public Slice<PostDto> findByRecruit(RecruitRequest request) {
        int pageSize = 12;
        Pageable pageable = PageRequest.of(request.getCurPage(), pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Slice<Post> posts = postRepository.findByRecruit(request, pageable);
        List<PostDto> postDtos = PostMapper.INSTANCE.toDtoList(posts.getContent());
        
        return new SliceImpl<>(postDtos, posts.getPageable(), posts.hasNext());
    }

    public List<PostDto> findAllPosts() {
        return postRepository.findAllWithDto();
    }
}
