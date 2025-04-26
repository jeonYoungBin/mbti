package org.com.spectorassignment.service;

import lombok.RequiredArgsConstructor;
import org.com.spectorassignment.domain.request.UpdateQuestionRequest;
import org.com.spectorassignment.domain.response.QuestionResponse;
import org.com.spectorassignment.entity.Question;
import org.com.spectorassignment.entity.QuestionType;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.exception.enumtype.ServiceExceptionCode;
import org.com.spectorassignment.repository.QuestionRepository;
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
