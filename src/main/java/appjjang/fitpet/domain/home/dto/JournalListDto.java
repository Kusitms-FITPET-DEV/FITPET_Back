package appjjang.fitpet.domain.home.dto;

import appjjang.fitpet.domain.journal.domain.Journal;
import lombok.Getter;

@Getter
public class JournalListDto {
    private String imageUrl;
    private String insuranceCompany;
    private String shortContent;

    public JournalListDto(Journal journal) {
        this.imageUrl = journal.getJournalImages().get(0).getImageUrl();
        this.insuranceCompany = journal.getInsuranceCompany();
        this.shortContent = journal.getContent();
    }

    public void updateJournalContent(String content) {
        this.shortContent = content;
    }
}
