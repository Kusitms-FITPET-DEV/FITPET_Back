package appjjang.fitpet.domain.journal.api;

import appjjang.fitpet.domain.journal.application.JournalService;
import appjjang.fitpet.domain.journal.domain.Journal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "journal API", description = "병원일지 API 입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/journal")
public class JournalController {
    private final JournalService journalService;

    @Operation(summary = "병원 일지 리스트", description = "사용자들이 병원을 다녀와서 보험을 이용한 후기를 보여줍니다")
    @GetMapping
    public ResponseEntity<List<Journal>> viewJournal() {
        List<Journal> journals = journalService.journalLog();  // Service에서 Journal 데이터를 가져옴
        return ResponseEntity.ok(journals);  // 클라이언트에게 데이터 반환
    }
}
