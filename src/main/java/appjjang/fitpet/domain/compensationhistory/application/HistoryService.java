package appjjang.fitpet.domain.compensationhistory.application;

import appjjang.fitpet.domain.admin.dto.request.HistoryUpdateRequest;
import appjjang.fitpet.domain.compensation.dao.CompensationRepository;
import appjjang.fitpet.domain.compensation.domain.Compensation;
import appjjang.fitpet.domain.compensation.domain.Progress;
import appjjang.fitpet.domain.compensationhistory.dao.HistoryRepository;
import appjjang.fitpet.domain.compensationhistory.domain.History;
import appjjang.fitpet.global.error.exception.CustomException;
import appjjang.fitpet.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final CompensationRepository compensationRepository;

    public void updateCompensation(Long compensationId, HistoryUpdateRequest request) {
        Compensation compensation = compensationRepository.findById(compensationId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMPENSATION_NOT_FOUND));
        Progress previousProcess = compensation.getProgress();
        Progress nextProcess = compensation.updateProgress(previousProcess, request.getChargePerson(), request.getInsuranceFee());
        historyRepository.save(History.createHistory(previousProcess, nextProcess, compensation));
    }
}
