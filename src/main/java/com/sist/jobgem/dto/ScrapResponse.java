package com.sist.jobgem.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScrapResponse {
    
    private Integer id;
    private Integer poIdx;
    private Integer joIdx;
    private LocalDate scDate; 
    private Integer scState;

    private PostDto postDto;
}
