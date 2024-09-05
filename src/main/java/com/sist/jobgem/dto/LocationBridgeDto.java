package com.sist.jobgem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationBridgeDto {

    
    private Integer id;
    private Integer lgIdx;
    private Integer poIdx;
    private Integer ldIdx;
    
}
