package appjjang.fitpet.domain.member.application;

import appjjang.fitpet.domain.member.domain.Member;
import appjjang.fitpet.domain.member.dto.response.MemberInfoResponse;
import appjjang.fitpet.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberUtil memberUtil;

    @Transactional(readOnly = true)
    public MemberInfoResponse getMember() {
        Member currentMember = memberUtil.getCurrentMember();
        return new MemberInfoResponse(currentMember);
    }
}
