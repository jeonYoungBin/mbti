package org.com.spectorassignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerId implements Serializable {
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "question_id")
    private Long questionId;
}
