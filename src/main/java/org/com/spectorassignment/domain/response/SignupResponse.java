package org.com.spectorassignment.domain.response;

import lombok.Builder;

@Builder
public record SignupResponse(Long id, String name, String email) {
}
