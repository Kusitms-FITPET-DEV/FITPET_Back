package appjjang.fitpet.domain.member.dao;

import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.member.domain.OauthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByOauthInfo(OauthInfo oauthInfo);
}
