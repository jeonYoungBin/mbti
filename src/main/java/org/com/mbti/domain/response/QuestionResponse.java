package org.com.mbti.domain.response;

import lombok.Builder;
import org.com.mbti.entity.Question;

@Builder
public record QuestionResponse(Long id, String question, String type, String answerType) {
    public static QuestionResponse from(Question q, String answerType) {
        return new QuestionResponse(q.getId(), q.getQuestion(), q.getType().name(), answerType);
    }
}
