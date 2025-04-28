package org.com.mbti.service;

import org.com.mbti.domain.request.LoginRequest;
import org.com.mbti.domain.request.SignupRequest;
import org.com.mbti.entity.Member;
import org.com.mbti.entity.Role;
import org.com.mbti.exception.CustomException;
import org.com.mbti.repository.MemberRepository;
import org.com.mbti.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private MemberService memberService;

    private final String email = "yy@hanmail.net";
    private final String password = "1234";
    private final String encodedPassword = "encode1234";

    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        member = Member.builder()
                .id(1L)
                .email(email)
                .password(encodedPassword)
                .userName("홍길동")
                .phoneNumber("010-1234-5678")
                .role(Role.USER)
                .build();
    }

    @Test
    void 회원가입_이메일중복_예외() {
        // given
        when(memberRepository.existsByEmail(email)).thenReturn(true);

        SignupRequest request = new SignupRequest(email, password, "홍길동", "010-1234-5678", Role.USER);

        // when & then
        assertThrows(CustomException.class, () -> memberService.signUp(request));
    }

    @Test
    void 회원가입_성공() {
        // given
        when(memberRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        SignupRequest request = new SignupRequest(email, password, "홍길동", "010-1234-5678", Role.USER);

        // when
        Long savedId = memberService.signUp(request);

        // then
        assertThat(savedId).isEqualTo(1L);
    }

    @Test
    void 로그인_미등록이메일_예외() {
        // given
        when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());

        LoginRequest request = new LoginRequest(email, password);

        // when & then
        assertThrows(CustomException.class, () -> memberService.login(request));
    }

    @Test
    void 로그인_비밀번호불일치_예외() {
        // given
        when(memberRepository.findByEmail(email)).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        LoginRequest request = new LoginRequest(email, password);

        // when & then
        assertThrows(CustomException.class, () -> memberService.login(request));
    }

    @Test
    void 로그인_성공() {
        // given
        when(memberRepository.findByEmail(email)).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(jwtTokenUtil.generateToken(email, member.getRole().name())).thenReturn("abcdefghijklmnop");

        LoginRequest request = new LoginRequest(email, password);

        // when
        String token = memberService.login(request);

        // then
        assertThat(token).isEqualTo("abcdefghijklmnop");
    }
}