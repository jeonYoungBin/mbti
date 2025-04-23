package org.com.spectorassignment.domain.request;

import jakarta.validation.constraints.NotNull;
import org.com.spectorassignment.entity.Role;

public record LoginRequest(
        @NotNull
        String email,

        @NotNull
        String password) {
}
