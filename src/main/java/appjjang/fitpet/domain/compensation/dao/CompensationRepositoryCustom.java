package appjjang.fitpet.domain.compensation.dao;

import appjjang.fitpet.domain.compensation.domain.Compensation;

import java.util.List;

public interface CompensationRepositoryCustom {
    List<Compensation> findCompensationsByPetId(Long petId);
}
