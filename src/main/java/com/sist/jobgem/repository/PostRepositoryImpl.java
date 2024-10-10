package com.sist.jobgem.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.dto.RecruitRequest;
import com.sist.jobgem.entity.Post;
import com.sist.jobgem.entity.QApplyment;
import com.sist.jobgem.entity.QCareersBridge;
import com.sist.jobgem.entity.QCompany;
import com.sist.jobgem.entity.QEducationBridge;
import com.sist.jobgem.entity.QHkBridge;
import com.sist.jobgem.entity.QLocationBridge;
import com.sist.jobgem.entity.QPost;
import com.sist.jobgem.entity.QSkillBridge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

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
        QCompany company = QCompany.company;
        BooleanBuilder builder = new BooleanBuilder();
        OrderSpecifier<?> sort = post.poDate.desc();
        
        builder.and(post.poState.ne(0));
        
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
        if(map.get("minSal") != null){
            builder.and(post.poSal.goe((String)map.get("minSal"))); 
        }
        if(map.get("maxSal") != null){
            builder.and(post.poSal.loe((String)map.get("maxSal")));
        }
        if(map.get("startDate") != null){
            builder.and(post.poDate.goe(LocalDate.parse(map.get("startDate").toString())));
        }
        if(map.get("endDate") != null){
            builder.and(post.poDate.loe(LocalDate.parse(map.get("endDate").toString())));
        }
        List<PostCountApplyDto> content = queryFactory
            .select(Projections.constructor(PostCountApplyDto.class,
                post.id, post.coIdx, post.poTitle, post.poContent, post.poDate, post.poDeadline, post.poSal, post.poSubType, post.poAddr,
                post.poEmail, post.poFax, post.poState,
                applyment.count().intValue().as("applyCount"), company))
            .from(post)
            .leftJoin(applyment).on(post.id.eq(applyment.poIdx))
            .leftJoin(company).on(post.coIdx.eq(company.id))
            .where(builder)
            .groupBy(post.id, post.coIdx, post.poTitle, post.poContent, post.poDate, post.poDeadline,
                post.poSal, post.poSubType, post.poAddr,
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
    
    @Override
    public Slice<Post> findByRecruit(RecruitRequest request, Pageable pageable) {
        QPost qPost = QPost.post;
        QCareersBridge qCareersBridge = QCareersBridge.careersBridge;
        QEducationBridge qEducationBridge = QEducationBridge.educationBridge;
        QHkBridge qHkBridge = QHkBridge.hkBridge;
        QLocationBridge qLocationBridge = QLocationBridge.locationBridge;
        QSkillBridge qSkillBridge = QSkillBridge.skillBridge;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(buildCondition(qSkillBridge.skIdx, request.getSkList()));
        builder.and(buildCondition(qEducationBridge.edIdx, request.getEdList()));
        builder.and(buildCondition(qHkBridge.hkIdx, request.getHkList()));
        builder.and(buildCondition(qLocationBridge.lgIdx, request.getLgList()));
        builder.and(buildCondition(qCareersBridge.crIdx, request.getCrList()));
        builder.and(qPost.poState.eq(1));
        builder.and(qPost.poDeadline.goe(LocalDate.now()));

        JPQLQuery<Post> query = queryFactory.select(qPost)
                .distinct()
                .from(qPost)
                .leftJoin(qCareersBridge).on(qPost.id.eq(qCareersBridge.poIdx))
                .leftJoin(qEducationBridge).on(qPost.id.eq(qEducationBridge.poIdx))
                .leftJoin(qHkBridge).on(qPost.id.eq(qHkBridge.poIdx))
                .leftJoin(qLocationBridge).on(qPost.id.eq(qLocationBridge.poIdx))
                .leftJoin(qSkillBridge).on(qPost.id.eq(qSkillBridge.poIdx)) 
                .where(builder)
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize() + 1);

        List<Post> content = query.fetch();

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

    private BooleanExpression buildCondition(NumberPath<Integer> path, List<Integer> idxList) {
        if (idxList == null || idxList.isEmpty()) {
            return null; // Skip this condition if the list is null or empty
        } else {
            return path.in(idxList); // Add condition if the list has values
        }
    }
}
