package org.com.mbti.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateQuestionRequest(
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "변경할 질문은 필수입니다.")
        String question
) {
}
