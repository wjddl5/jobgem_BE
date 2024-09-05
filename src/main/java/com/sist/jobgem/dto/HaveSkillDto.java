package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Skill;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaveSkillDto{
    private Skill skill;
}