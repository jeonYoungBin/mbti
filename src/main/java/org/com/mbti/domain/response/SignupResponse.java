package org.com.mbti.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SignupResponse(
        @Schema(description = "회원 아이디", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,
        @Schema(description = "이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(description = "이메일", example = "abc@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        String email) {
}
