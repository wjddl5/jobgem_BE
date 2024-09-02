package com.sist.jobgem.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.sist.jobgem.entity.Board;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
  private Integer id;
  private String usId;
  private String usPw;
  private Instant joinDate;
  private LocalDate leaveDate;
  private Integer usType;
  private Integer usState;
  private String accessToken;
  private String refreshToken;
}
