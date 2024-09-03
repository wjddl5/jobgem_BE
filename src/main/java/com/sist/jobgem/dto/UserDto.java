package com.sist.jobgem.dto;

import java.time.Instant;
import java.time.LocalDate;

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
  private Instant usJoinDate;
  private LocalDate usLeaveDate;
  private Integer usType;
  private Integer usState;
  private String accessToken;
  private String refreshToken;
}
