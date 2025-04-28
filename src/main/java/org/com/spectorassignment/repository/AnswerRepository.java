package org.com.spectorassignment.repository;

import org.com.spectorassignment.domain.response.AnswerResponse;
import org.com.spectorassignment.entity.Answer;
import org.com.spectorassignment.entity.AnswerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, AnswerId> {
    List<Answer> findAllByAnswerIdIn(Collection<AnswerId> answerIds);
    @Query("""
        SELECT new org.com.spectorassignment.domain.response.AnswerResponse(
            a.answerId.memberId,
            q.question,
            a.answer
        )
        FROM Answer a
        JOIN a.question q
        WHERE a.answerId.memberId = :memberId
    """)
    List<AnswerResponse> findAnswersByMemberId(@Param("memberId") Long memberId);

    List<Answer> findAllByAnswerId_QuestionId(Long questionId);
}
