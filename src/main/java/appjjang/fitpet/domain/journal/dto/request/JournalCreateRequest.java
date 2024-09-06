package appjjang.fitpet.domain.journal.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class JournalCreateRequest {
    private String insuranceCompany;
    private String insuranceName;
    private String profileUrl;
    private String nickname;
    private String content;
    private LocalDate date;
    private List<MultipartFile> imageFiles;
}
