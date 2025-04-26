package org.com.spectorassignment.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.com.spectorassignment.entity.Role;

public record SignupRequest (
        @NotNull
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "올바른 이메일 형식이 아닙니다."
        )
        String email,

        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "패스워드를 입력해주세요")
        String password,

        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "이름을 입력해주세요")
        String userName,

        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull(message = "핸드폰 번호를 입력해주세요")
        @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "000-0000-0000 형식이어야 합니다.")
        String phoneNum,

        @NotNull
        Role role) {
}
