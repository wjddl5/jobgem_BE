package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.entity.Post;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    PostDto toDto(Post post);
    
    Post toEntity(PostDto postDto);
    
    List<PostDto> toDtoList(List<Post> posts);


    List<Post> toEntityList(List<PostDto> postDtos);
}
