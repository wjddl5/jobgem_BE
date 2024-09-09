package com.sist.jobgem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sist.jobgem.repository.PostRepository;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostListDto {
    private List<PostCountApplyDto> postList;
    private int progress;
    private int all;
    private int complete;
    private int deadline;
}
