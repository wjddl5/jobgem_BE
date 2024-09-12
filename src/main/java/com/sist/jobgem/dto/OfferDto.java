package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.Offer;
import com.sist.jobgem.entity.Review;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDto {
    private Integer id;

    private Integer coIdx;

    private Integer joIdx;

    private String ofContent;

    private Integer ofType;

    private LocalDate ofDate;

    private Integer ofState;

    private Company company;

    private Jobseeker jobseeker;

    public static OfferDto fromEntity(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .joIdx(offer.getJoIdx())
                .company(offer.getCompany())
                .ofContent(offer.getOfContent())
                .ofDate(offer.getOfDate())
                .ofState(offer.getOfState())
                .build();
    }
}
