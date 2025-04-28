package org.com.spectorassignment.domain.request;

import jakarta.validation.constraints.NotNull;

public record UpdateQuestionRequest(
        @NotNull(message = "변경할 질문은 필수입니다.")
        String question
) {
}
