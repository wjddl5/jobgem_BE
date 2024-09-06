package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.sist.jobgem.repository.PostRepository;
import com.sist.jobgem.repository.EducationRepository;
import com.sist.jobgem.repository.CareerRepository;
import com.sist.jobgem.repository.SkillRepository;
import com.sist.jobgem.repository.LocationDoRepository;
import com.sist.jobgem.repository.LocationGuSiRepository;
import com.sist.jobgem.repository.HireKindRepository;
import com.sist.jobgem.dto.IdNameDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.PostSetDto;
import com.sist.jobgem.entity.Post;
import com.sist.jobgem.mapper.LocationDoMapper;
import com.sist.jobgem.mapper.LocationGuSiMapper;
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

    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos=null;
        if (!posts.isEmpty()) {
            postDtos = PostMapper.INSTANCE.toDtoList(posts);

        }
        return postDtos;
    }

    public int create(PostDto postDto) {
        postDto.setPoState(1);
        Post post = PostMapper.INSTANCE.toEntity(postDto);
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
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
}
