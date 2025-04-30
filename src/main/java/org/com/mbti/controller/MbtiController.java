package org.com.mbti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.com.mbti.domain.request.CompleteAnswerRequest;
import org.com.mbti.domain.request.UpdateQuestionRequest;
import org.com.mbti.domain.response.AnswerResponse;
import org.com.mbti.domain.response.QuestionResponse;
import org.com.mbti.exception.CustomException;
import org.com.mbti.service.AnswerService;
import org.com.mbti.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mbtis")
@Tag(name = "[MBTI 조회 등록]")
public class MbtiController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Operation(summary = "MBTI 질문 조회", description = "MBTI 질문 조회 하는 API 입니다. USER만 접근 가능합니다.")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponse>> findQuestions() throws CustomException {
        List<QuestionResponse> questions = questionService.findAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @Operation(summary = "MBTI 질문 변경", description = "MBTI 질문 변경 하는 API 입니다. USER만 접근 가능합니다.")
    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/questions/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId,
                                            @RequestBody @Valid UpdateQuestionRequest request) throws CustomException {
        questionService.updateQuestion(questionId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "MBTI 질문 답변 API", description = "MBTI 질문 답변 API 하는 API 입니다. USER만 접근 가능합니다.")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/complete")
    public ResponseEntity<?> saveMbtiQuestion(Authentication authentication,
                                              @Valid @RequestBody CompleteAnswerRequest answers) throws CustomException {
        String email = authentication.getName();
        answerService.saveMbtiAnswers(email, answers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "회원 MBTI 질문 답변 조회 API", description = "회원 MBTI 질문 답변 조회 하는 API 입니다. MANAGER만 접근 가능합니다.")
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{userId}")
    public ResponseEntity<List<AnswerResponse>> findMbtiAnswers(@PathVariable(name = "userId") Long memberId) {
        return ResponseEntity.ok(answerService.findByAllAnswer(memberId));
    }
}
