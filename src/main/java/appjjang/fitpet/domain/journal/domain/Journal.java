package appjjang.fitpet.domain.journal.domain;

import appjjang.fitpet.domain.journalimage.domain.JournalImage;
import appjjang.fitpet.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Long id;

    private String insuranceCompany;
    private String insuranceName;
    private String profileUrl;
    private String nickname;

    @Column(length = 2000)
    private String content;
    private LocalDate date;

    @Builder
    private Journal(String insuranceCompany, String insuranceName, String profileUrl,
                    String nickname, String content, LocalDate date){
        this.insuranceCompany = insuranceCompany;
        this.insuranceName = insuranceName;
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.content = content;
        this.date = date;
    }

    public static Journal createJournal(String insuranceCompany, String insuranceName, String profileUrl,
                                        String nickname, String content, LocalDate date){
        return Journal.builder()
                .insuranceCompany(insuranceCompany)
                .insuranceName(insuranceName)
                .profileUrl(profileUrl)
                .nickname(nickname)
                .content(content)
                .date(date)
                .build();
    }

    public void setJournalImages(List<JournalImage> journalImages) {
        this.journalImages = journalImages;
    }

    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalImage> journalImages = new ArrayList<>();

}
