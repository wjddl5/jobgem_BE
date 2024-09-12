package com.sist.jobgem.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.entity.QApplyment;
import com.sist.jobgem.entity.QPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostCountApplyDto> findByFilterWithApplyCount(Map<String, Object> map, Pageable pageable) {
        QPost post = QPost.post;
        QApplyment applyment = QApplyment.applyment;
        BooleanBuilder builder = new BooleanBuilder();
        OrderSpecifier<?> sort = post.poDate.desc();
        if (map.get("coIdx") != null) {
            builder.and(post.coIdx.eq(Integer.parseInt(map.get("coIdx").toString())));
        }
        if (map.get("deadline") != null) {
            builder.and(post.poDeadline.eq(LocalDate.now()));
        }
        if (map.get("state") != null) {
            System.err.println("poState: " + map.get("state"));
            builder.and(post.poState.eq(Integer.parseInt(map.get("state").toString())));
        }
        if (map.get("searchType") != null) {
            if(map.get("searchType").toString().equals("title")){
                System.err.println("search: " + map.get("search"));
                builder.and(post.poTitle.contains(map.get("search").toString()));
            }else if(map.get("searchType").toString().equals("content")){
                builder.and(post.poContent.contains(map.get("search").toString()));
            }
        }
        if (map.get("sort") != null) {
            if(map.get("sort").toString().equals("poDateDesc")){
                sort = post.poDate.desc();
            }else if(map.get("sort").toString().equals("poDeadlineAsc")){
                sort = post.poDeadline.asc();
            }else if(map.get("sort").toString().equals("applyCountDesc")){
                sort = applyment.count().desc();
            }else if(map.get("sort").toString().equals("poDateAsc")){
                sort = post.poDate.asc();
            }
        }
        List<PostCountApplyDto> content = queryFactory
            .select(Projections.constructor(PostCountApplyDto.class,
                post.id, post.coIdx, post.poTitle, post.poContent, post.poDate, post.poDeadline,
                post.poImgurl, post.poSal, post.poWorkhour, post.poSubType, post.poAddr,
                post.poEmail, post.poFax, post.poState,
                applyment.count().intValue().as("applyCount")))
            .from(post)
            .leftJoin(applyment).on(post.id.eq(applyment.poIdx))
            
            .where(builder)
            .groupBy(post.id, post.coIdx, post.poTitle, post.poContent, post.poDate, post.poDeadline,
                post.poImgurl, post.poSal, post.poWorkhour, post.poSubType, post.poAddr,
                post.poEmail, post.poFax, post.poState)
            .orderBy(sort)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = queryFactory
            .select(post.count())
            .from(post)
            .where(builder)
            .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
