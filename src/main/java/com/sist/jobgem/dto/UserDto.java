package com.sist.jobgem.dto;

import java.time.Instant;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
