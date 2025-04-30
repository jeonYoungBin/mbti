package org.com.mbti.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AnswerResponse(
        @Schema(description = "회원 ID", example = "1")
        @NotNull
        Long memberId,

        @Schema(description = "질문", example = "당신은 외향형 인간입니까?")
        @NotNull
        String question,

        @Schema(description = "답변", example = "YES")
        @NotNull
        String answer) {
}
