package com.sist.jobgem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sist.jobgem.repository.PostRepository;
import com.sist.jobgem.repository.EducationRepository;
import com.sist.jobgem.repository.CareerRepository;
import com.sist.jobgem.repository.SkillRepository;
import com.sist.jobgem.repository.LocationDoRepository;
import com.sist.jobgem.repository.LocationGuSiRepository;
import com.sist.jobgem.repository.HireKindRepository;
import com.sist.jobgem.dto.IdNameDto;
import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.PostListDto;
import com.sist.jobgem.dto.PostSetDto;
import com.sist.jobgem.entity.Post;
import com.sist.jobgem.mapper.PostMapper;

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
    private LocationDoRepository locationDoRepository;

    @Autowired
    private LocationGuSiRepository locationGuSiRepository;

    @Autowired
    private HireKindRepository hireKindRepository;

    public PostListDto getPosts(Map<String, Object> map, int coIdx) {
        PostListDto postListDto = new PostListDto();
        List<PostCountApplyDto> postList = postRepository.findByFilterWithApplyCount(map);
        postListDto.setPostList(postList);
        setPostList(postListDto, coIdx);
        return postListDto;
    }

    public PostDto getPost(int poIdx) {
        Post post = postRepository.findById(poIdx);
        System.out.println("company: " + post.getCompany().getCoName());
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
        postListDto.setDeadline(postRepository.countByCoIdxAndPoDeadline(coIdx, LocalDate.now()));
    }

    public Slice<PostDto> getPostList(Pageable pageable) {
        Slice<PostDto> postList = postRepository.getPostListSlice(pageable);
        return postList;
    }
}
