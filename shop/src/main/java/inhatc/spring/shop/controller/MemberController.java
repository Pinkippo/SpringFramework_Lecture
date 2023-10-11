package inhatc.spring.shop.controller;

import inhatc.spring.shop.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("member/new")
    public String MemberForm() {
        return "member/memberForm";
    }
}
