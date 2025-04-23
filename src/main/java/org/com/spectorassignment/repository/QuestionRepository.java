package org.com.spectorassignment.repository;

import org.com.spectorassignment.entity.Member;
import org.com.spectorassignment.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
