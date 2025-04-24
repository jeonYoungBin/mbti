package org.com.spectorassignment.domain.response;

import lombok.Builder;
import org.com.spectorassignment.entity.Answer;
import org.com.spectorassignment.util.EncryptionUtil;

@Builder
public record AnswerResponse(Long memberId, String question, String answer) {
}
