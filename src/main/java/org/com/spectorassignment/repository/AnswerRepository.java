package org.com.spectorassignment.repository;

import org.com.spectorassignment.entity.Answer;
import org.com.spectorassignment.entity.AnswerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByAnswerId_MemberId(Long memberId);
    boolean existsByAnswerIdIn(Collection<AnswerId> answerIds);
    List<Answer> findAllByAnswerIdIn(Collection<AnswerId> answerIds);
}
