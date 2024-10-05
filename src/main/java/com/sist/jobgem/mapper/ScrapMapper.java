package com.sist.jobgem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.ScrapDto;
import com.sist.jobgem.dto.ScrapResponse;
import com.sist.jobgem.entity.Post;
import com.sist.jobgem.entity.Scrap;

@Mapper
public interface ScrapMapper {
    ScrapMapper INSTANCE = Mappers.getMapper(ScrapMapper.class);
    
    ScrapDto toDto(Scrap scrap);
    Scrap toEntity(ScrapDto scrapDto); 
    
    PostDto toPostDto(Post post);

    @Mapping(source = "post", target = "postDto")
    ScrapResponse toScrapResponse(Scrap scrap); 

    List<ScrapResponse> toScrapResponseList(List<Scrap> scraps); 
}
