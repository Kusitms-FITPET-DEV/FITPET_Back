package appjjang.fitpet.domain.compensationhistory.application;

import appjjang.fitpet.domain.admin.dto.request.HistoryUpdateRequest;
import appjjang.fitpet.domain.compensation.dao.CompensationRepository;
import appjjang.fitpet.domain.compensation.domain.Compensation;
import appjjang.fitpet.domain.compensation.domain.Progress;
import appjjang.fitpet.domain.compensationhistory.dao.HistoryRepository;
import appjjang.fitpet.domain.compensationhistory.domain.History;
import appjjang.fitpet.domain.compensationhistory.dto.response.HistoryResponse;
import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import appjjang.fitpet.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final CompensationRepository compensationRepository;
    private final MemberUtil memberUtil;

    public void updateCompensation(Long compensationId, HistoryUpdateRequest request) {
        Compensation compensation = compensationRepository.findById(compensationId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMPENSATION_NOT_FOUND));
        Progress previousProcess = compensation.getProgress();
        Progress nextProcess = compensation.updateProgress(previousProcess, request.getChargePerson(), request.getInsuranceFee());
        historyRepository.save(History.createHistory(previousProcess, nextProcess, compensation));
    }

    @Transactional(readOnly = true)
    public List<HistoryResponse> getHistoryList() {
        Member currentMember = memberUtil.getCurrentMember();
        return historyRepository.findHistoryListByMemberId(currentMember.getId()).stream()
                .map(HistoryResponse::new)
                .collect(Collectors.toList());
    }

    public void changeHistoryStatus(Long historyId) {
        History history = historyRepository.findById(historyId)
                .orElseThrow(() -> new CustomException(ErrorCode.HISTORY_NOT_FOUND));
        history.updateConfirmed();
    }
}
