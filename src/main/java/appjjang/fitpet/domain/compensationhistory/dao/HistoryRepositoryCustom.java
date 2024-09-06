package appjjang.fitpet.domain.compensationhistory.dao;

import appjjang.fitpet.domain.compensationhistory.domain.History;

import java.util.List;

public interface HistoryRepositoryCustom {
    List<History> findHistoryListByMemberId(Long memberId);
}
