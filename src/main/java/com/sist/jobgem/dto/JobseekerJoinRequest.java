package com.sist.jobgem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobseekerJoinRequest {

    @Getter
    @Setter
    public static class JoinUser extends UserDto {

        @Email
        private String usId;
        @NotBlank
        private String usPw;
    }

    @Getter
    @Setter
    public static class JoinJobseeker extends JobseekerDto {

        @NotBlank
        private String joName;
        @NotBlank
        private String joTel;
        @NotBlank
        private String joGender;
    }

    @Valid
    private JoinUser user;

    @Valid
    private JoinJobseeker jobseeker;
}