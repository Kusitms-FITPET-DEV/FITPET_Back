package appjjang.fitpet.domain.coverage.application;

import appjjang.fitpet.domain.coverage.dao.CoverageRepository;
import appjjang.fitpet.domain.coverage.domain.Coverage;
import appjjang.fitpet.domain.coverage.dto.request.CoverageCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CoverageService {
    private final CoverageRepository coverageRepository;

    public void saveCoverage(CoverageCreateRequest request) {
        coverageRepository.save(Coverage.createCoverage(request.getDailyTreatLimit(),
                request.getDailySurgeryCostLimit(),
                request.getYearReward(),
                request.getHipJointLimit(),
                request.getSkinDisease(),
                request.getSkinEtc(),
                request.getOralCoverage(),
                request.getDentalCoverage(),
                request.getInspectionCoverage(),
                request.getForeignObjectRemoval(),
                request.getSelfBurden(),
                request.getCompensationLiability(),
                request.getFuneralAid(),
                request.getInsuranceCompany(),
                request.getPositiveList(),
                request.getDiscountList()
        ));
    }
}
