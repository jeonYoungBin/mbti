package org.com.spectorassignment.domain.response;

import lombok.Builder;
import org.com.spectorassignment.entity.Answer;
import org.com.spectorassignment.util.EncryptionUtil;

@Builder
public record AnswerResponse(Long memberId, Long questionId, String answer) {
    public static AnswerResponse from(Answer a) {
        return new AnswerResponse(a.getAnswerId().getMemberId(), a.getAnswerId().getQuestionId(), EncryptionUtil.decrypt(a.getAnswer()));
    }
}
