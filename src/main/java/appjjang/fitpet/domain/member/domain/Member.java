package appjjang.fitpet.domain.member.domain;

import appjjang.fitpet.domain.journal.domain.Journal;
import appjjang.fitpet.domain.heart.domain.Heart;
import appjjang.fitpet.domain.insurance.domain.Insurance;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static appjjang.fitpet.domain.member.domain.MemberRole.USER;
import static appjjang.fitpet.domain.member.domain.MemberStatus.NORMAL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded private OauthInfo oauthInfo;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    private String phone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Insurance> insurances = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Journal> journals = new ArrayList<>();

    @Builder
    private Member(
            OauthInfo oauthInfo,
            MemberRole role,
            MemberStatus status) {
        this.oauthInfo = oauthInfo;
        this.role = role;
        this.status = status;
    }

    public static Member createMember(OauthInfo oauthInfo) {
        return Member.builder()
                .role(USER)
                .status(NORMAL)
                .oauthInfo(oauthInfo)
                .build();
    }
}
