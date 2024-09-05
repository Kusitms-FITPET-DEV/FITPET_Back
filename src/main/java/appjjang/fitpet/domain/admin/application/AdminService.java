package appjjang.fitpet.domain.admin.application;

import appjjang.fitpet.domain.admin.dto.request.AdminInsuranceRequest;
import appjjang.fitpet.domain.coverage.dao.CoverageRepository;
import appjjang.fitpet.domain.coverage.domain.Coverage;
import appjjang.fitpet.domain.insurance.dao.InsuranceRepository;
import appjjang.fitpet.domain.insurance.domain.Insurance;
import appjjang.fitpet.domain.pet.dao.PetRepository;
import appjjang.fitpet.domain.pet.domain.Pet;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final PetRepository petRepository;
    private final InsuranceRepository insuranceRepository;
    private final CoverageRepository coverageRepository;

    public Insurance joinInsurance(AdminInsuranceRequest request){
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new CustomException(ErrorCode.PET_NOT_FOUND));

        Coverage coverage = coverageRepository.findLatestByInsuranceCompany(request.getInsuranceCompany());

        Insurance insurance = Insurance.createInsurance(
                request.getContractor(),
                pet.getName(),
                request.getStartDate(),
                request.getEndDate(),
                request.getUpdateCycle(),
                request.getInsuranceFee(),
                request.getPriceRate(),
                request.getPayWay(),
                request.getBank(),
                request.getBankAccount(),
                request.getPayCycle(),
                coverage,
                pet
        );

        Insurance savedInsurance = insuranceRepository.save(insurance);

        pet.updateIsInsurance(insurance);
        // Step 5: 저장된 객체 반환
        return savedInsurance;
    }
}
