package org.com.mbti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.com.mbti.domain.request.LoginRequest;
import org.com.mbti.domain.request.SignupRequest;
import org.com.mbti.domain.response.LoginResponse;
import org.com.mbti.domain.response.SignupResponse;
import org.com.mbti.exception.CustomException;
import org.com.mbti.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specter")
@Tag(name = "[회원 가입/로그인]")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 가입", description = "회원 가입 하는 API 입니다.")
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> addMember(@RequestBody @Valid SignupRequest request) throws CustomException {

        SignupResponse signupResponse = SignupResponse.builder()
                .id(memberService.signUp(request))
                .email(request.email())
                .name(request.userName())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponse);
    }

    @Operation(summary = "로그인", description = "로그인 하는 API 입니다.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws CustomException {;
        LoginResponse loginResponse = LoginResponse.builder().token(memberService.login(request)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
