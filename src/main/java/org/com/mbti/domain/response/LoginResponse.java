package org.com.mbti.domain.response;

import lombok.Builder;

@Builder
public record LoginResponse(String token) {
}
