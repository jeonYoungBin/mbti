package org.com.spectorassignment.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.com.spectorassignment.entity.Role;

public record LoginRequest(
        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull
        String email,

        @NotBlank(message = "빈값은 입력할수 없습니다.")
        @NotNull
        String password) {
}
