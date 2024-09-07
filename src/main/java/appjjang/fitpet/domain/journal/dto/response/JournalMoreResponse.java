package appjjang.fitpet.domain.journal.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Getter
@Builder
public class JournalMoreResponse {
    private String insuranceCompany;
    private String insuranceName;
    private String nickname;
    private String content;
    private List<String> imageUrls;
}
