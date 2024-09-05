package appjjang.fitpet.domain.coverage.dao;

import appjjang.fitpet.domain.coverage.domain.Coverage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import static appjjang.fitpet.domain.coverage.domain.QCoverage.coverage;

@Repository
@RequiredArgsConstructor
@Primary
public class CoverageRepositoryImpl implements CoverageRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Coverage findLatestByInsuranceCompany(String insuranceCompany) {
        return queryFactory.selectFrom(coverage)
                .where(coverage.insuranceCompany.eq(insuranceCompany))
                .orderBy(coverage.updatedAt.desc())
                .fetchFirst();
    }
}
