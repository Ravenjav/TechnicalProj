package com.softarex.technical_proj.repository;

import com.softarex.technical_proj.entities.Question;
import com.softarex.technical_proj.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    //Page<Question> findAll(Pageable pageable);

    //List<Question> findBySenderEmail(String sender, Pageable pageable);

    List<Question> findBySenderEmail(String sender);

    List<Question> findByResponsibleEmail(String responsible);

    //Page<Question> findByResponsible(String responsible, Pageable pageable);
}
