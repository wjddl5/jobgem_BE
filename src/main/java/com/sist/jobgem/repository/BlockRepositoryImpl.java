package com.sist.jobgem.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sist.jobgem.entity.Block;
import com.sist.jobgem.entity.QBlock;
import com.sist.jobgem.entity.QJobseeker;
import com.sist.jobgem.entity.QUser;
import com.sist.jobgem.entity.QCompany;
import com.querydsl.core.types.dsl.Expressions;
import java.time.LocalDate;
import java.time.ZoneOffset;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BlockRepositoryImpl implements BlockRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Block> findByJobseeker(Map<String, Object> params) {
        int pageNumber = 0;
        int pageSize = 10;
        if (params.get("page") != null) {
            pageNumber = Integer.parseInt(params.get("page").toString());
        }
        if (params.get("size") != null) {
            pageSize = Integer.parseInt(params.get("size").toString());
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        QBlock block = QBlock.block;
        QJobseeker jobseeker = QJobseeker.jobseeker;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(block.joIdx.isNotNull());
        builder.and(block.coIdx.isNull());
        if (params.get("blockStartDate") != null) {
            builder.and(block.blDate.goe(LocalDate.parse(params.get("blockStartDate").toString())));
        }
        if (params.get("blockEndDate") != null) {
            builder.and(block.blDate.loe(LocalDate.parse(params.get("blockEndDate").toString())));
        }
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
            switch (type) {
                case "blcontent":
                    builder.and(block.blContent.contains(value));
                    break;
                case "name":
                    builder.and(jobseeker.joName.contains(value));
                    break;
                case "tel":
                    builder.and(jobseeker.joTel.contains(value));
                    break;
                case "address":
                    builder.and(jobseeker.joAddress.contains(value));
                    break;
                case "edu":
                    builder.and(jobseeker.joEdu.contains(value));
                case "gender":
                    builder.and(jobseeker.joGender.contains(value));
                    break;
                default:
                    break;
            }
        }

        List<Block> blocks = queryFactory.selectFrom(block)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory.selectFrom(block)
                .where(builder)
                .fetchCount();
        return new PageImpl<>(blocks, pageable, total);
    }

    @Override
    public Page<Block> findByCompany(Map<String, Object> params) {
        int pageNumber = 0;
        int pageSize = 10;
        if (params.get("page") != null) {
            pageNumber = Integer.parseInt(params.get("page").toString());
        }
        if (params.get("size") != null) {
            pageSize = Integer.parseInt(params.get("size").toString());
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        QBlock block = QBlock.block;
        QCompany company = QCompany.company;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(block.coIdx.isNotNull());
        builder.and(block.joIdx.isNull());
        if (params.get("blockStartDate") != null) {
            builder.and(block.blDate.goe(LocalDate.parse(params.get("blockStartDate").toString())));
        }
        if (params.get("blockEndDate") != null) {
            builder.and(block.blDate.loe(LocalDate.parse(params.get("blockEndDate").toString())));
        }
        if (params.get("openStartDate") != null) {
            builder.and(company.coOpen.goe(LocalDate.parse(params.get("openStartDate").toString())));
        }
        if (params.get("openEndDate") != null) {
            builder.and(company.coOpen.loe(LocalDate.parse(params.get("openEndDate").toString())));
        }
        if (params.get("minSales") != null) {
            builder.and(
                    Expressions.numberTemplate(Integer.class, "CAST({0} AS int)", company.coSales)
                            .goe(Integer.parseInt(params.get("minSales").toString())));
        }
        if (params.get("maxSales") != null) {
            builder.and(Expressions.numberTemplate(Integer.class, "CAST({0} AS int)", company.coSales)
                    .loe(Integer.parseInt(params.get("maxSales").toString())));
        }

        if (params.get("searchType") != null && params.get("searchValue") != null) {
            String type = params.get("searchType").toString();
            String value = params.get("searchValue").toString();
            switch (type) {
                case "name":
                    builder.and(company.coName.contains(value));
                    break;
                case "number":
                    builder.and(company.coNumber.contains(value));
                    break;
                case "tel":
                    builder.and(company.coTel.contains(value));
                    break;
                case "address":
                    builder.and(company.coAddress.contains(value));
                    break;
                case "type":
                    builder.and(company.coType.contains(value));
                    break;
                case "blcontent":
                    builder.and(block.blContent.contains(value));
                    break;
                case "employee":
                    builder.and(company.coEmployee.stringValue().contains(value));
                    break;
                case "score":
                    builder.and(company.coScore.stringValue().contains(value));
                    break;
                case "managerName":
                    builder.and(company.coManagerName.contains(value));
                    break;
                case "managerTel":
                    builder.and(company.coManagerTel.contains(value));
                    break;
                default:
                    break;
            }
        }

        List<Block> result = queryFactory.selectFrom(block)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory.selectFrom(block)
                .where(builder)
                .fetchCount();
        return new PageImpl<>(result, pageable, total);
    }

}
