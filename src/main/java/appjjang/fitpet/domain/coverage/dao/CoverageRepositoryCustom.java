package appjjang.fitpet.domain.coverage.dao;

import appjjang.fitpet.domain.coverage.domain.Coverage;

public interface CoverageRepositoryCustom {
    Coverage findLatestByInsuranceCompany(String insuranceCompany);
}
