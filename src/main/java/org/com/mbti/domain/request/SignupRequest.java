package org.com.mbti.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.com.mbti.entity.Role;

public record SignupRequest (
        @Schema(description = "이메일", example = "abc@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "올바른 이메일 형식이 아닙니다. abc@hanmail.net"
        )
        String email,

        @Schema(description = "패스워드", example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "패스워드를 입력해주세요")
        String password,

        @Schema(description = "이름", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "이름을 입력해주세요")
        String userName,

        @Schema(description = "핸드폰 번호", example = "010-1234-5678", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "핸드폰 번호를 입력해주세요")
        @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "000-0000-0000 형식이어야 합니다.")
        String phoneNum,

        @Schema(description = "권한", example = "USER", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Role role) {
}
