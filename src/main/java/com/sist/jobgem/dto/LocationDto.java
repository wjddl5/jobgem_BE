package com.sist.jobgem.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {

    private int id;
    private String ldName;
    private List<LocationGuSiDto> locationGuSiDto;
}
