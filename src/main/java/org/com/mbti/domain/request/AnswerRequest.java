package org.com.mbti.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequest(
        @Schema(description = "질문ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "질문 ID는 필수입니다.")
        Long questionId,

        @Schema(description = "응답 내용", example = "YES", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "응답은 필수 입니다.")
        String answer
) { }
