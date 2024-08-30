package com.sist.jobgem.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.sist.jobgem.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  // public UserDto(User user) {
  // this.id = user.getId();
  // this.usId = user.getUsId();
  // this.usPw = user.getUsPw();
  // this.joinDate = user.getJoinDate();
  // this.leaveDate = user.getLeaveDate();
  // this.usType = user.getUsType();
  // this.usState = user.getUsState();
  // this.accessToken = user.getAccessToken();
  // this.refreshToken = user.getRefreshToken();
  // }

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
