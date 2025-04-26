package org.com.spectorassignment.domain.request;

import jakarta.validation.constraints.NotNull;

public record UpdateQuestionRequest(
        @NotNull
        String question
) {
}
