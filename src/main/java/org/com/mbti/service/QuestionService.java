package org.com.mbti.service;

import lombok.RequiredArgsConstructor;
import org.com.mbti.domain.request.UpdateQuestionRequest;
import org.com.mbti.domain.response.QuestionResponse;
import org.com.mbti.entity.Question;
import org.com.mbti.entity.QuestionType;
import org.com.mbti.exception.CustomException;
import org.com.mbti.exception.enumtype.ServiceExceptionCode;
import org.com.mbti.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final static String RADIO_ANSWER_TYPE = "YES/NO";
    private final static String TEXT_ANSWER_TYPE = "TEXT";

    public List<QuestionResponse> findAllQuestions() {
        return questionRepository.findAll().stream()
                .map(q -> q.getType() == QuestionType.RADIO ?
                        QuestionResponse.from(q, RADIO_ANSWER_TYPE) : QuestionResponse.from(q, TEXT_ANSWER_TYPE))
                .toList();
    }

    @Transactional
    public void updateQuestion(Long questionId, UpdateQuestionRequest request) {
        Question findQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(ServiceExceptionCode.QUESTION_NOT_FOUND));

        findQuestion.updateQuestion(request.question());

    }

}
