package appjjang.fitpet.domain.question.dto.response;

import appjjang.fitpet.domain.question.FaqDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FaqQueryResponse {
    private long typeCount;
    private List<String> typeList;
    private int questionCount;
    private List<FaqDto> questionList;
}
