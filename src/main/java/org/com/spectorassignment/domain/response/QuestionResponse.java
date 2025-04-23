package org.com.spectorassignment.domain.response;

import lombok.Builder;
import org.com.spectorassignment.entity.Question;

@Builder
public record QuestionResponse(Long id, String question, String type) {
    public static QuestionResponse from(Question q) {
        return new QuestionResponse(q.getId(), q.getQuestion(), q.getType().name());
    }
}
