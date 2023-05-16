package com.softarex.technical_proj.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "questions")
@TypeDef(
        name = "pgsql_enum",
        typeClass = EnumTypePostgreSQL.class
)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "question")
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private User responsible;

    @Column(name = "options")
    private ArrayList<String> option;

    @Column(name = "answer")
    private String answer;

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
}
