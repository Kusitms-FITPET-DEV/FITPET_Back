package appjjang.fitpet.domain.banner.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;

    private String bannerUrl;

    @Builder
    private Banner(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public static Banner createBanner(String bannerUrl) {
        return Banner.builder()
                .bannerUrl(bannerUrl)
                .build();
    }
}
