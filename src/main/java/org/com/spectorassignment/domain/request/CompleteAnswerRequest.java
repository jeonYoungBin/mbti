package org.com.spectorassignment.domain.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CompleteAnswerRequest(
        @Valid
        List<AnswerRequest> answers
) { }
