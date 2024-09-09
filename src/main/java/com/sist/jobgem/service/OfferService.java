package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import com.sist.jobgem.dto.OfferDto;
import com.sist.jobgem.entity.Offer;
import com.sist.jobgem.repository.OfferRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;

    public Page<OfferDto> getOfferList(int id, Pageable pageable) {
        Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Offer> offerList = offerRepository.findByJoIdxAndOfState(id, 1, pageable2);

        List<OfferDto> offerDtoList = offerList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(offerDtoList, pageable2, offerList.getTotalElements());
    }

    private OfferDto convertToDto(Offer offer) {
        return OfferDto.fromEntity(offer);
    }

}
