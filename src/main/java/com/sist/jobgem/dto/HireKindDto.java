package com.sist.jobgem.dto;

<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> 3478be65b4c004f21ff9bfcd354150278af49786
public class HireKindDto {
    private Integer id;
    private String hkName;
}
