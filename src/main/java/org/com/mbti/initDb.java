package org.com.mbti;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.com.mbti.entity.Question;
import org.com.mbti.entity.QuestionType;
import org.com.mbti.repository.QuestionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class initDb {
    private final QuestionRepository questionRepository;

    @PostConstruct
    public void initQuestions() {
        if (questionRepository.count() > 0) return; // 이미 존재하면 중복 저장 방지

        List<Question> questions = List.of(
                Question.builder().question("낯선 이들과 대화하는 것이 편안하신가요?").type(QuestionType.RADIO).build(),
                Question.builder().question("체계적으로 일정을 관리하는 편인가요?").type(QuestionType.RADIO).build(),
                Question.builder().question("타인의 마음을 읽는 것이 능숙하신가요?").type(QuestionType.RADIO).build(),
                Question.builder().question("복잡한 과제를 해결하는 것을 선호하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("독립적인 활동을 즐기시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("어려운 상황에서도 침착함을 유지하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("창의적인 발상이 자주 떠오르나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("팀 활동에 적극적으로 참여하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("상황에 민감하게 반응하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("이성적 판단을 중시하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("꼼꼼하게 일처리를 하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("봉사활동에 참여하는 것을 좋아하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("모험을 시도하는 것이 즐거우신가요?").type(QuestionType.RADIO).build(),
                Question.builder().question("솔직하게 마음을 드러내시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("다양한 관점을 수용하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("원하는 바를 이루기 위해 끈기 있게 도전하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("평화로운 관계 유지를 중요하게 생각하시나요?").type(QuestionType.RADIO).build(),
                Question.builder().question("감정 조절이 가능한 편이신가요?").type(QuestionType.RADIO).build(),
                Question.builder().question("자신의 인간관계에서 가장 중요하게 생각하는 가치는 무엇인가요?").type(QuestionType.TEXTAREA).build(),
                Question.builder().question("직관적 판단이 도움이 되었던 경험을 설명해주세요.").type(QuestionType.TEXTAREA).build()
        );

        questionRepository.saveAll(questions);
    }
}
