package com.sist.jobgem.dto;

import java.util.List;

import com.sist.jobgem.entity.LocationDo;
import com.sist.jobgem.entity.LocationGuSi;
import com.sist.jobgem.dto.IdNameDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSetDto {
    private List<IdNameDto> education;
    private List<LocationGuSi> locationGuSi;
    private List<LocationDo> locationDo;
    private List<IdNameDto> hiringType;
    private List<IdNameDto> career;
    private List<IdNameDto> skill;
}
