package appjjang.fitpet.domain.journalimage.domain;

import appjjang.fitpet.domain.journal.domain.Journal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JournalImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_image_id")
    private Long id;

    private String imgaeUrl;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;


}
