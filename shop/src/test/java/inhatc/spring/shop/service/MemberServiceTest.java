package inhatc.spring.shop.service;

import inhatc.spring.shop.dto.MemberFormDto;
import inhatc.spring.shop.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member createMember() {
        MemberFormDto memberFormDto = MemberFormDto.builder()
                .name("홍길동")
                .address("인천 미추홀구")
                .email("test@test.com")
                .password("1111")
                .build();

        Member member = Member.createMember(memberFormDto, passwordEncoder);
        System.out.println("member = " + member);
        return member;
    }


    @Test
    @Transactional
    void saveMemberTest() {
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);
        System.out.println("savedMember = " + savedMember);

        assertEquals(member.getName(), savedMember.getName());

    }

    @Test
    @Transactional
    @DisplayName("중복가입 테스트")
    void saveMemberTest2() {
        Member member1 = createMember();
        Member savedMember1 = memberService.saveMember(member1);
        Member member2 = createMember();

        Throwable e = assertThrows(IllegalStateException.class,
                () -> {
                    Member savedMember2 = memberService.saveMember(member2);
                    System.out.println("savedMember2 = " + savedMember2);
                });

        assertEquals("이미 존재하는 회원입니다.", e.getMessage());


    }
}