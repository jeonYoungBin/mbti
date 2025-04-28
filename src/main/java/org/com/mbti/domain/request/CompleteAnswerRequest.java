package org.com.mbti.domain.request;

import jakarta.validation.Valid;

import java.util.List;

public record CompleteAnswerRequest(
        @Valid
        List<AnswerRequest> answers
) { }
