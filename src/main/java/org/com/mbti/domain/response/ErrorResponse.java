package org.com.mbti.domain.response;

import lombok.Builder;

@Builder
public record ErrorResponse(String code, String message) {
}
