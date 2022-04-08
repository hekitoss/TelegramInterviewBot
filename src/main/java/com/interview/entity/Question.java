package com.interview.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Accessors(chain = true)
@Getter
@Setter
@RequiredArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    @SequenceGenerator(name = "question_seq", allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rate_id")
    private Rate rate;

    @OneToOne
    @JoinColumn(name = "creator_id")
    private User owner;

    private String questionName;
    private String text;
    private String answer;
    private boolean isDeleted;
    private LocalDateTime creationTime;
    private LocalDateTime deletingTime;


    public Question(String text, String answer, String questionName) {
        this.text = text;
        this.answer = answer;
        this.questionName = questionName;
        creationTime = LocalDateTime.now();
        this.isDeleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Question question = (Question) o;
        return id != null && Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionName=" + questionName +
                ", text='" + text + '\'' +
                ", answer='" + answer + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
