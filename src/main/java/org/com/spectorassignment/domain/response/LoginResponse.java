package org.com.spectorassignment.domain.response;

import lombok.Builder;

@Builder
public record LoginResponse(String token) {
}
