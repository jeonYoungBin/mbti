package org.com.mbti.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @Schema(description = "이메일", example = "abc@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "이메일은 필수 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "올바른 이메일 형식이 아닙니다."
        )
        String email,

        @Schema(description = "패스워드", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "패스워드는 필수 입니다.")
        String password) {
}
