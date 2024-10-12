package com.sist.jobgem.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.QBlock;
import com.sist.jobgem.entity.QJobseeker;
import com.sist.jobgem.entity.QUser;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JobseekerRepositoryImpl implements JobseekerRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Jobseeker> findJobseekersNotInBlock(Map<String, Object> params) {
        QJobseeker jobseeker = QJobseeker.jobseeker;
        QBlock block = QBlock.block;
        QUser user = QUser.user;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(block.joIdx.isNull());
        builder.and(user.usState.eq(1));
        builder.and(user.usType.ne(0));
        if (params.get("searchType") != null && params.get("searchValue") != null) {
            String type = params.get("searchType").toString();
            String value = params.get("searchValue").toString();
            switch (type) {
                case "name":
                    builder.and(jobseeker.joName.contains(value));
                    break;
                case "birth":
                    builder.and(jobseeker.joBirth.stringValue().contains(value));
                    break;
                case "tel":
                    builder.and(jobseeker.joTel.contains(value));
                    break;
                case "address":
                    builder.and(jobseeker.joAddress.contains(value));
                    break;
                case "edu":
                    builder.and(jobseeker.joEdu.contains(value));
                    break;
                case "sal":
                    if (!value.isEmpty()) { // 추가: 빈 문자열 체크
                        builder.and(Expressions.numberTemplate(Integer.class, "CAST({0} AS int)", jobseeker.joSal)
                                .eq(Integer.parseInt(value)));
                    }
                    break;
                case "gender":
                    builder.and(jobseeker.joGender.contains(value));
                    break;
                case "joinDate":
                    builder.and(jobseeker.user.usJoinDate.stringValue().contains(value));
                    break;
                case "leaveDate":
                    builder.and(jobseeker.user.usLeaveDate.stringValue().contains(value));
                    break;
                default:
                    break;
            }
        }

        return queryFactory
                .selectFrom(jobseeker)
                .leftJoin(block).on(jobseeker.id.eq(block.joIdx))
                .where(builder)
                .fetch();
    }

    @Override
    public Page<Jobseeker> findByTypeAndValueContaining(Map<String, Object> params) {
        int pageNumber = 0;
        int pageSize = 10;
        if (params.get("page") != null) {
            pageNumber = Integer.parseInt(params.get("page").toString());
        }
        if (params.get("size") != null) {
            pageSize = Integer.parseInt(params.get("size").toString());
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        QJobseeker jobseeker = QJobseeker.jobseeker;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(jobseeker.user.usType.ne(0));
        if (params.get("birthStartDate") != null) {
            builder.and(jobseeker.joBirth.goe(LocalDate.parse(params.get("birthStartDate").toString())));
        }
        if (params.get("birthEndDate") != null) {
            builder.and(jobseeker.joBirth.loe(LocalDate.parse(params.get("birthEndDate").toString())));
        }
        if (params.get("joinStartDate") != null) {
            builder.and(jobseeker.user.usJoinDate.goe(
                    LocalDate.parse(params.get("joinStartDate").toString()).atStartOfDay(ZoneOffset.UTC).toInstant()));
        }
        if (params.get("joinEndDate") != null) {
            builder.and(jobseeker.user.usJoinDate.loe(
                    LocalDate.parse(params.get("joinEndDate").toString()).atStartOfDay(ZoneOffset.UTC).toInstant()));
        }
        if (params.get("leaveStartDate") != null) {
            builder.and(jobseeker.user.usLeaveDate.goe(LocalDate.parse(params.get("leaveStartDate").toString())));
        }
        if (params.get("leaveEndDate") != null) {
            builder.and(jobseeker.user.usLeaveDate.loe(LocalDate.parse(params.get("leaveEndDate").toString())));
        }
        if (params.get("minSal") != null) {
            builder.and(
                    Expressions.numberTemplate(Integer.class, "CAST({0} AS int)", jobseeker.joSal)
                            .goe(Integer.parseInt(params.get("minSal").toString())));
        }
        if (params.get("maxSal") != null) {
            builder.and(
                    Expressions.numberTemplate(Integer.class, "CAST({0} AS int)", jobseeker.joSal)
                            .loe(Integer.parseInt(params.get("maxSal").toString())));
        }
        if (params.get("searchType") != null && params.get("searchValue") != null) {
            String type = params.get("searchType").toString();
            String value = params.get("searchValue").toString();

            if (type.equals("name")) {
                builder.and(jobseeker.joName.contains(value));
            }
            if (type.equals("tel")) {
                builder.and(jobseeker.joTel.contains(value));
            }
            if (type.equals("address")) {
                builder.and(jobseeker.joAddress.contains(value));
            }
            if (type.equals("edu")) {
                builder.and(jobseeker.joEdu.contains(value));
            }
            if (type.equals("gender")) {
                builder.and(jobseeker.joGender.contains(value));
            }
        }
        List<Jobseeker> jobseekers = queryFactory.selectFrom(jobseeker)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(jobseeker)
                .where(builder)
                .fetchCount();
        return new PageImpl<>(jobseekers, pageable, total);
    }
}
