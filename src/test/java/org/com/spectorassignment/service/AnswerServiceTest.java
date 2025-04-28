package org.com.spectorassignment.service;

import org.com.spectorassignment.domain.request.AnswerRequest;
import org.com.spectorassignment.domain.request.CompleteAnswerRequest;
import org.com.spectorassignment.domain.response.AnswerResponse;
import org.com.spectorassignment.entity.*;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.repository.AnswerRepository;
import org.com.spectorassignment.repository.MemberRepository;
import org.com.spectorassignment.repository.QuestionRepository;
import org.com.spectorassignment.util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class AnswerServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;

    private Member member;
    private Question question;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        member = Member.builder()
                .id(1L)
                .email("user@example.com")
                .build();

        question = Question.builder()
                .id(1L)
                .question("당신은 외향적입니까?")
                .type(QuestionType.RADIO)
                .build();
    }

    @Test
    void MBTI_정상저장() {
        // given
        when(memberRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(member));

        when(questionRepository.findAllById(anySet()))
                .thenReturn(List.of(question));

        when(answerRepository.findAllByAnswerIdIn(anyList()))
                .thenReturn(List.of()); // 중복 답변 없음

        CompleteAnswerRequest request = new CompleteAnswerRequest(
                List.of(new AnswerRequest(1L, "YES"))
        );

        // when
        answerService.saveMbtiAnswers("user@example.com", request);

        // then
        verify(answerRepository, times(1)).saveAll(anyList());
    }

    @Test
    void MBTI_중복답변_예외() {
        // given
        when(memberRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(member));

        when(questionRepository.findAllById(anySet()))
                .thenReturn(List.of(question));

        when(answerRepository.findAllByAnswerIdIn(anyList()))
                .thenReturn(List.of(Answer.builder()
                        .answerId(new AnswerId(1L, 1L))
                        .build()));

        CompleteAnswerRequest request = new CompleteAnswerRequest(
                List.of(new AnswerRequest(1L, "YES"))
        );

        // when & then
        assertThrows(CustomException.class, () ->
                answerService.saveMbtiAnswers("user@example.com", request)
        );
    }

    @Test
    void MBTI_없는질문ID_예외() {
        // given
        when(memberRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(member));

        when(questionRepository.findAllById(anySet()))
                .thenReturn(List.of()); // 질문이 아예 없음

        CompleteAnswerRequest request = new CompleteAnswerRequest(
                List.of(new AnswerRequest(999L, "YES"))
        );

        // when & then
        assertThrows(CustomException.class, () ->
                answerService.saveMbtiAnswers("user@example.com", request)
        );
    }

    @Test
    void MBTI_잘못된응답형식_예외() {
        // given
        when(memberRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(member));

        when(questionRepository.findAllById(anySet()))
                .thenReturn(List.of(question));

        when(answerRepository.findAllByAnswerIdIn(anyList()))
                .thenReturn(List.of());

        CompleteAnswerRequest request = new CompleteAnswerRequest(
                List.of(new AnswerRequest(1L, "MAYBE")) // 잘못된 응답
        );

        // when & then
        assertThrows(CustomException.class, () ->
                answerService.saveMbtiAnswers("user@example.com", request)
        );
    }

    @Test
    void MBTI_응답_정상조회() {
        // given
        when(answerRepository.findAnswersByMemberId(1L))
                .thenReturn(List.of(
                        new AnswerResponse(1L, "당신은 외향적입니까?", EncryptionUtil.encrypt("YES"))
                ));

        // when
        List<AnswerResponse> responses = answerService.findByAllAnswer(1L);

        // then
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).answer()).isEqualTo("YES");
    }
}