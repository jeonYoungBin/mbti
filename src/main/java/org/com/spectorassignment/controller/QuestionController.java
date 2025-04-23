package org.com.spectorassignment.controller;

import lombok.RequiredArgsConstructor;
import org.com.spectorassignment.domain.response.QuestionResponse;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.exception.enumtype.ServiceExceptionCode;
import org.com.spectorassignment.service.QuestionService;
import org.com.spectorassignment.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mbtis")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<?> findQuestions() throws CustomException {
        if(!SecurityUtil.hasRole("USER")) throw new CustomException(ServiceExceptionCode.ACCESS_DENIED);
        List<QuestionResponse> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }


}
