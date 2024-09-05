package appjjang.fitpet.domain.compensationhistory.dao;

import appjjang.fitpet.domain.compensationhistory.domain.History;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static appjjang.fitpet.domain.charge.domain.QCharge.charge;
import static appjjang.fitpet.domain.compensation.domain.QCompensation.compensation;
import static appjjang.fitpet.domain.compensationhistory.domain.QHistory.history;
import static appjjang.fitpet.domain.insurance.domain.QInsurance.insurance;
import static appjjang.fitpet.domain.member.domain.QMember.member;
import static appjjang.fitpet.domain.pet.domain.QPet.pet;

@Repository
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<History> findHistoryListByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(history)
                .join(history.compensation, compensation)
                .join(compensation.charge, charge)
                .join(charge.insurance, insurance)
                .join(insurance.pet, pet)
                .join(pet.member, member)
                .where(member.id.eq(memberId))
                .fetch();
    }
}
