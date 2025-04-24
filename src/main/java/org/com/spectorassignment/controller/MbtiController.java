package org.com.spectorassignment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.com.spectorassignment.domain.request.CompleteAnswerRequest;
import org.com.spectorassignment.domain.response.QuestionResponse;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.service.AnswerService;
import org.com.spectorassignment.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mbtis")
public class MbtiController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/questions")
    public ResponseEntity<?> findQuestions() throws CustomException {
        List<QuestionResponse> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/complete")
    public ResponseEntity<?> saveMbtiQuestion(Authentication authentication,
                                              @Valid @RequestBody CompleteAnswerRequest answers) throws CustomException {
        String email = authentication.getName();
        answerService.saveMbtiAnswers(email, answers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> findMbtiAnswers(@PathVariable(name = "userId") Long memberId) {
        return ResponseEntity.ok(answerService.findByAllAnswer(memberId));
    }
}
