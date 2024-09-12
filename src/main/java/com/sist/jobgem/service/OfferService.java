package com.sist.jobgem.service;

import com.sist.jobgem.dto.ChatDto;
import com.sist.jobgem.dto.ChatroomDto;
import com.sist.jobgem.dto.OfferResponseDto;
import com.sist.jobgem.entity.*;
import com.sist.jobgem.mapper.ChatMapper;
import com.sist.jobgem.mapper.ChatroomMapper;
import com.sist.jobgem.mapper.OfferMapper;
import com.sist.jobgem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import com.sist.jobgem.dto.OfferDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;

    @Autowired
    ChatroomRepository chatroomRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobseekerRepository jobseekerRepository;

    public Page<OfferDto> getOfferList(int id, Pageable pageable) {

        Page<Offer> offerList = offerRepository.findByJoIdxAndOfState(id, 1, pageable);

        List<OfferDto> offerDtoList = offerList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(offerDtoList, pageable, offerList.getTotalElements());
    }

    private OfferDto convertToDto(Offer offer) {
        return OfferDto.fromEntity(offer);
    }

    public OfferResponseDto addOffer(OfferDto offerDto) {
        Company company = companyRepository.findById(offerDto.getCoIdx()).orElseThrow();
        int openUser = company.getUser().getId();

        Jobseeker jobseeker = jobseekerRepository.findById(offerDto.getJoIdx()).orElseThrow();
        int joinUser = jobseeker.getUser().getId();


        ChatroomDto chatroomDto = ChatroomDto.builder()
                .opIdx(openUser)
                .jnIdx(joinUser)
                .build();

        Chatroom chatroom = chatroomRepository.save(ChatroomMapper.INSTANCE.toEntity(chatroomDto));

        ChatDto chat = ChatDto.builder()
                .cmIdx(chatroom.getId())
                .usIdx(joinUser)
                .chContent(offerDto.getOfContent())
                .build();

        OfferResponseDto offerResponseDto = OfferResponseDto.builder()
                .offer(offerRepository.save(OfferMapper.INSTANCE.toEntity(offerDto)))
                .chatroom(chatroom)
                .chat(chatRepository.save(ChatMapper.INSTANCE.toEntity(chat)))
                .build();

        return offerResponseDto;
    }

}
