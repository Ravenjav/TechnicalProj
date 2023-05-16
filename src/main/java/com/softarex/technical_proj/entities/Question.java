package com.softarex.technical_proj.entities;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "questions")
@TypeDef(
        name = "pgsql_enum",
        typeClass = EnumTypePostgreSQL.class
)
@Data
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "question")
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "question_type")
    @Type(type = "pgsql_enum")
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "username")
    private String sender;

    @Column(name = "foruser")
    private String foruser;

    @Column(name = "option")
    private ArrayList<String> option;

    @Column(name = "answer")
    private String answer;
}
