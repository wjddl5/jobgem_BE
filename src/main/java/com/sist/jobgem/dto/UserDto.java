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
  private Integer id;
  private String usId;
  private String usPw;
  private Instant joinDate;
  private LocalDate leaveDate;
  private Integer usType;
  private Integer usState;
  private String accessToken;
  private String refreshToken;

  // Entity -> DTO 변환
  public static UserDto fromEntity(User user) {
    return new UserDto(
        user.getId(),
        user.getUsId(),
        user.getUsPw(),
        user.getJoinDate(),
        user.getLeaveDate(),
        user.getUsType(),
        user.getUsState(),
        user.getAccessToken(),
        user.getRefreshToken());
  }

}
