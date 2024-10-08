package appjjang.fitpet.domain.home.dto.response;

import appjjang.fitpet.domain.home.dto.JournalListDto;
import appjjang.fitpet.domain.home.dto.QuizDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeResponse {
    private List<JournalListDto> journalList;
    private QuizDto quiz;
    private List<String> questionList;
}
