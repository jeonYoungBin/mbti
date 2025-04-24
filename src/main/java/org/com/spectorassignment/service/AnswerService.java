package org.com.spectorassignment.service;

import lombok.RequiredArgsConstructor;
import org.com.spectorassignment.domain.request.AnswerRequest;
import org.com.spectorassignment.domain.request.CompleteAnswerRequest;
import org.com.spectorassignment.domain.response.AnswerResponse;
import org.com.spectorassignment.entity.*;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.exception.enumtype.ServiceExceptionCode;
import org.com.spectorassignment.repository.AnswerRepository;
import org.com.spectorassignment.repository.MemberRepository;
import org.com.spectorassignment.repository.QuestionRepository;
import org.com.spectorassignment.util.EncryptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public void saveMbtiAnswers(String email, CompleteAnswerRequest request) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ServiceExceptionCode.ACCESS_DENIED));

        Map<Long, String> questionIdToAnswer = request.answers().stream()
                .collect(Collectors.toMap(AnswerRequest::questionId, AnswerRequest::answer));

        List<Question> questions = questionRepository.findAllById(questionIdToAnswer.keySet());
        List<AnswerId> answerIds = questions.stream()
                .map(q -> AnswerId.builder()
                        .memberId(findMember.getId())
                        .questionId(q.getId())
                        .build())
                .toList();

        List<Answer> alreadyExists = answerRepository.findAllByAnswerIdIn(answerIds);
        if (!CollectionUtils.isEmpty(alreadyExists)) {
            List<AnswerId> alreadyExistIds = alreadyExists.stream().map(Answer::getAnswerId).toList();
            throw new CustomException(ServiceExceptionCode.ANSWER_ALREADY_EXISTS, alreadyExistIds);
        }

        if (questions.size() != questionIdToAnswer.size()) {
            throw new CustomException(ServiceExceptionCode.QUESTION_NOT_FOUND);
        }

        List<Answer> answers = questions.stream()
                .map(question -> createEncryptedAnswer(findMember, question, questionIdToAnswer.get(question.getId())))
                .toList();

        answerRepository.saveAll(answers);
    }

    public List<AnswerResponse> findByAllAnswer(Long memberId) {
        return answerRepository.findAllByAnswerId_MemberId(memberId)
                .stream().map(AnswerResponse::from).collect(Collectors.toList());
    }

    private Answer createEncryptedAnswer(Member member, Question question, String answer) {
        validateAnswerFormat(question, answer);
        return Answer.builder()
                .answerId(new AnswerId(member.getId(), question.getId()))
                .answer(EncryptionUtil.encrypt(answer))
                .build();
    }

    private void validateAnswerFormat(Question question, String answer) {
        if (question.getType() == QuestionType.RADIO) {
            if (!"YES".equals(answer) && !"NO".equals(answer)) {
                throw new CustomException(ServiceExceptionCode.ANSWER_YES_NO);
            }
        }
    }
}
