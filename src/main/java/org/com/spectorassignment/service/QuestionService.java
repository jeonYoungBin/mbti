package org.com.spectorassignment.service;

import lombok.RequiredArgsConstructor;
import org.com.spectorassignment.domain.request.LoginRequest;
import org.com.spectorassignment.domain.request.SignupRequest;
import org.com.spectorassignment.domain.response.QuestionResponse;
import org.com.spectorassignment.entity.Member;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.exception.enumtype.ServiceExceptionCode;
import org.com.spectorassignment.repository.MemberRepository;
import org.com.spectorassignment.repository.QuestionRepository;
import org.com.spectorassignment.util.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionResponse> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(QuestionResponse::from)
                .toList();
    }

}
