package org.com.spectorassignment.service;

import org.com.spectorassignment.domain.request.UpdateQuestionRequest;
import org.com.spectorassignment.domain.response.QuestionResponse;
import org.com.spectorassignment.entity.Question;
import org.com.spectorassignment.entity.QuestionType;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    private Question question;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        question = Question.builder()
                .id(1L)
                .question("당신은 외향적입니까?")
                .type(QuestionType.RADIO)
                .build();
    }

    @Test
    void 질문_전체조회_성공() {
        // given
        when(questionRepository.findAll()).thenReturn(List.of(question));

        // when
        List<QuestionResponse> responses = questionService.findAllQuestions();

        // then
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).question()).isEqualTo("당신은 외향적입니까?");
    }

    @Test
    void 질문_수정_성공() {
        // given
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        UpdateQuestionRequest updateRequest = new UpdateQuestionRequest("당신은 내향적입니까?");

        // when
        questionService.updateQuestion(1L, updateRequest);

        // then
        assertThat(question.getQuestion()).isEqualTo("당신은 내향적입니까?");
    }

    @Test
    void 질문_수정_질문없음_예외() {
        // given
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        UpdateQuestionRequest updateRequest = new UpdateQuestionRequest("abcde?");

        // when & then
        assertThrows(CustomException.class, () ->
                questionService.updateQuestion(1L, updateRequest)
        );
    }
}