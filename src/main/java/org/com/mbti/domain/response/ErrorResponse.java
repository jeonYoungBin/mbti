package org.com.mbti.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ErrorResponse(

        @NotNull(message = "에러코드는 필수 입니다.")
        @Schema(description = "에러코드", example = "INTERNAL_SERVER_ERROR", requiredMode = Schema.RequiredMode.REQUIRED)
        String code,
        @NotNull(message = "에러메시지는 필수 입니다.")
        @Schema(description = "에러메시지", example = "서버 에러입니다", requiredMode = Schema.RequiredMode.REQUIRED)
        String message) {
}
