package org.com.mbti.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequest(
        @NotNull(message = "질문 ID는 필수입니다.")
        Long questionId,


        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "응답은 필수 입니다.")
        String answer
) { }
