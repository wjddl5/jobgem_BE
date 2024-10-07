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
public class CompanyJoinRequest {

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
    public static class JoinCompany extends CompanyDto {
        @NotBlank
        private String coName;
        @NotBlank
        private String coAddress;
        @NotBlank
        private String coTel;
        @NotBlank
        private String coNumber;
        @NotBlank
        private String coType;
        @NotBlank
        private String coManagerName;
        @NotBlank
        private String coManagerTel;
    }

    @Valid
    private JoinUser user;
    
    @Valid
    private JoinCompany company;
}
