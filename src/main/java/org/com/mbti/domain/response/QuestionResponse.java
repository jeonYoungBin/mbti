package org.com.mbti.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.com.mbti.entity.Question;

@Builder
public record QuestionResponse(
        @Schema(description = "질문 ID", example = "1")
        @NotBlank
        @NotNull
        Long id,

        @Schema(description = "질문 내용", example = "당신은 아침형 인간인가요?")
        @NotBlank
        @NotNull
        String question,

        @Schema(description = "질문 타입", example = "RADIO")
        @NotBlank
        @NotNull
        String type,

        @Schema(description = "응답 타입", example = "YES/NO")
        @NotBlank
        @NotNull
        String answerType

) {
    public static QuestionResponse from(Question q, String answerType) {
        return new QuestionResponse(q.getId(), q.getQuestion(), q.getType().name(), answerType);
    }
}
