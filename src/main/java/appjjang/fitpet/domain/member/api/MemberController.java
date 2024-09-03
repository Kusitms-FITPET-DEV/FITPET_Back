package appjjang.fitpet.domain.member.api;

import appjjang.fitpet.domain.member.application.MemberService;
import appjjang.fitpet.domain.member.dto.response.MemberInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 API", description = "사용자 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "사용자 정보 조회", description = "마이페이지에서 사용자 정보를 조회합니다.")
    @GetMapping
    public MemberInfoResponse getMemberInfo() {
        return memberService.getMember();
    }
}
