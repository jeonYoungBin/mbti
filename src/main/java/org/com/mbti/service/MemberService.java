package org.com.mbti.service;

import lombok.RequiredArgsConstructor;
import org.com.mbti.domain.request.LoginRequest;
import org.com.mbti.domain.request.SignupRequest;
import org.com.mbti.entity.Member;
import org.com.mbti.exception.CustomException;
import org.com.mbti.exception.enumtype.ServiceExceptionCode;
import org.com.mbti.repository.MemberRepository;
import org.com.mbti.util.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Transactional
    public Long signUp(SignupRequest request) throws CustomException {
        if(memberRepository.existsByEmail(request.email())) {
            throw new CustomException(ServiceExceptionCode.ALREADY_EXIST_MEMBER, request.email());
        }

        Member saveMember = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .userName(request.userName())
                .phoneNumber(request.phoneNum())
                .role(request.role())
                .build();

        return memberRepository.save(saveMember).getId();
    }

    public String login(LoginRequest request) throws CustomException {
        Member findMember = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ServiceExceptionCode.NOT_REGISTRATION_MEMBER, request.email()));

        if (!passwordEncoder.matches(request.password(), findMember.getPassword())) {
            throw new CustomException(ServiceExceptionCode.NOT_MATCH_PASSWORD);
        }

        return jwtTokenUtil.generateToken(request.email(), findMember.getRole().name());
    }

}
