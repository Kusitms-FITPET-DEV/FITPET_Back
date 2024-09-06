package appjjang.fitpet.domain.compensation.dao;

import appjjang.fitpet.domain.compensation.domain.Compensation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static appjjang.fitpet.domain.charge.domain.QCharge.charge;
import static appjjang.fitpet.domain.compensation.domain.QCompensation.compensation;
import static appjjang.fitpet.domain.insurance.domain.QInsurance.insurance;
import static appjjang.fitpet.domain.pet.domain.QPet.pet;

@Repository
@RequiredArgsConstructor
public class CompensationRepositoryImpl implements CompensationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Compensation> findCompensationsByPetId(Long petId) {
        return queryFactory
                .selectFrom(compensation)
                .join(compensation.charge, charge)
                .join(charge.insurance, insurance)
                .join(insurance.pet, pet)
                .where(pet.id.eq(petId))
                .fetch();
    }
}
