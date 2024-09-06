package appjjang.fitpet.domain.journal.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class JournalResponse {
    private Long journalId;
    private String insuranceCompany;
    private String insuranceName;
    private String profileUrl;
    private String nickname;
    private String content;
    private LocalDate date;
    private List<String> imageUrls;

}