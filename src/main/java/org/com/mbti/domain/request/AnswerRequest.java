package org.com.mbti.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequest(
        @NotNull(message = "질문 ID는 필수입니다.")
        Long questionId,

        @NotBlank(message = "응답은 비워둘 수 없습니다.")
        String answer
) { }
