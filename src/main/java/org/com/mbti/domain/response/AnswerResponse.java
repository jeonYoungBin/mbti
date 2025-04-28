package org.com.mbti.domain.response;

import lombok.Builder;

@Builder
public record AnswerResponse(Long memberId, String question, String answer) {
}
