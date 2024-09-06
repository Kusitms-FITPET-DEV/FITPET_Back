package appjjang.fitpet.domain.compensation.dto.response;

import appjjang.fitpet.domain.compensation.dto.CompensationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CompensationListResponse {
    private String petName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CompensationDto> compensationlist;
}
