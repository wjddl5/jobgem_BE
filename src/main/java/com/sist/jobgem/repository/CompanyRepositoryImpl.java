package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.QBlock;
import com.sist.jobgem.entity.QCompany;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public CompanyRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Company> findByTypeAndValueContaining(Map<String, Object> params) {
        QCompany company = QCompany.company;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(company.user.usType.ne(0));
        int pageNumber = 0;
        int pageSize = 10;
        if (params.get("page") != null) {
            pageNumber = Integer.parseInt(params.get("page").toString());
        }
        if (params.get("size") != null) {
            pageSize = Integer.parseInt(params.get("size").toString());
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (params.get("searchType") != null && params.get("searchValue") != null) {
            String type = params.get("searchType").toString();
            String value = params.get("searchValue").toString();
            switch (type) {
                case "name":
                    builder.and(company.coName.contains(value));
                    break;
                case "tel":
                    builder.and(company.coTel.contains(value));
                    break;
                case "address":
                    builder.and(company.coAddress.contains(value));
                    break;
                case "number":
                    builder.and(company.coNumber.contains(value));
                    break;
                case "type":
                    builder.and(company.coType.contains(value));
                    break;
                case "open":
                    builder.and(company.coOpen.stringValue().contains(value));
                    break;
                case "employee":
                    builder.and(company.coEmployee.stringValue().contains(value));
                    break;
                case "sales":
                    builder.and(company.coSales.stringValue().contains(value));
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
        List<Company> companies = queryFactory.selectFrom(company)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(company)
                .where(builder)
                .fetchCount();
        return new PageImpl<>(companies, pageable, total);
    }

    @Override
    public List<Company> findCompanysNotInBlock(Map<String, Object> params) {
        QCompany company = QCompany.company;
        QBlock block = QBlock.block;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(block.coIdx.isNull());
        builder.and(company.user.usType.ne(0));
        if (params.get("searchType") != null && params.get("searchValue") != null) {
            String type = params.get("searchType").toString();
            String value = params.get("searchValue").toString();
            switch (type) {
                case "name":
                    builder.and(company.coName.contains(value));
                    break;
                case "tel":
                    builder.and(company.coTel.contains(value));
                    break;
                case "address":
                    builder.and(company.coAddress.contains(value));
                    break;
                case "number":
                    builder.and(company.coNumber.contains(value));
                    break;
                case "type":
                    builder.and(company.coType.contains(value));
                    break;
                case "open":
                    builder.and(company.coOpen.stringValue().contains(value));
                    break;
                case "employee":
                    builder.and(company.coEmployee.stringValue().contains(value));
                    break;
                case "sales":
                    builder.and(company.coSales.stringValue().contains(value));
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
        return queryFactory
                .selectFrom(company)
                .leftJoin(block).on(company.id.eq(block.coIdx))
                .where(builder)
                .fetch();
    }
}
