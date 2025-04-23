package org.com.spectorassignment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.com.spectorassignment.domain.request.LoginRequest;
import org.com.spectorassignment.domain.request.SignupRequest;
import org.com.spectorassignment.domain.response.LoginResponse;
import org.com.spectorassignment.domain.response.SignupResponse;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specter")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> addMember(@RequestBody @Valid SignupRequest request) throws CustomException {

        SignupResponse signupResponse = SignupResponse.builder()
                .id(memberService.signUp(request))
                .email(request.email())
                .name(request.userName())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws CustomException {;
        LoginResponse loginResponse = LoginResponse.builder().token(memberService.login(request)).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
