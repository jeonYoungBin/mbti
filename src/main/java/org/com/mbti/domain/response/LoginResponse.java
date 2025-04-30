package org.com.mbti.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LoginResponse(
        @Schema(description = "토큰", requiredMode = Schema.RequiredMode.REQUIRED)
        String token) {
}
