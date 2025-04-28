package org.com.mbti.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "이메일은 필수 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "올바른 이메일 형식이 아닙니다."
        )
        String email,

        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "패스워드는 필수 입니다.")
        String password) {
}
