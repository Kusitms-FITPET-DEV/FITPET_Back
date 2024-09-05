package appjjang.fitpet.domain.admin.application;

import appjjang.fitpet.domain.admin.dto.request.AdminInsuranceRequest;
import appjjang.fitpet.domain.coverage.dao.CoverageRepository;
import appjjang.fitpet.domain.coverage.dao.CoverageRepositoryCustom;
import appjjang.fitpet.domain.coverage.domain.Coverage;
import appjjang.fitpet.domain.insurance.dao.InsuranceRepository;
import appjjang.fitpet.domain.insurance.domain.Insurance;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.dao.PetRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    @Qualifier("petRepositoryImpl")
    private final PetRepositoryCustom petRepositoryCustom;
    private final InsuranceRepository insuranceRepository;
    private final CoverageRepository coverageRepository;

    public Insurance joinInsurance(AdminInsuranceRequest request){
        Long petId = request.getPetId();
        String petName = petRepositoryCustom.getPetNameById(petId);
        String insuranceCompany = request.getInsuranceCompany();
        Coverage coverage = coverageRepository.findLatestByInsuranceCompany(insuranceCompany);
        Long coverageId = coverage.getId();

        Insurance insurance = Insurance.createInsurance(
                request.getContractor(),
                petName,
                request.getStartDate(),
                request.getEndDate(),
                request.getUpdateCycle(),
                request.getInsuranceFee(),
                request.getPriceRate(),
                request.getPayWay(),
                request.getBank(),
                request.getBankAccount(),
                request.getPayCycle(),
                coverageId
        );

        Insurance savedInsurance = insuranceRepository.save(insurance);

        // Step 5: 저장된 객체 반환
        return savedInsurance;
    }
}
