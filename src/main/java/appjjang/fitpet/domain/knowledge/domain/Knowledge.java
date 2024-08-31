package appjjang.fitpet.domain.knowledge.domain;

import appjjang.fitpet.domain.like.domain.Like;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "knowledge_id")
    private Long id;

    private KnowledgeType type;
    private String title;
    private String content;
    private String imageUrl;

    @OneToMany(mappedBy = "knowledge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();
}
