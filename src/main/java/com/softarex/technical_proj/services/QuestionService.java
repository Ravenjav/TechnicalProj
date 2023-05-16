package com.softarex.technical_proj.services;

import com.softarex.technical_proj.entities.Question;
import com.softarex.technical_proj.entities.User;
import com.softarex.technical_proj.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public final class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> findSendQuestionsByUsername(String username){
        return repository.findBySenderEmail(username);
    }

    public List<Question> findAll(){
        List<Question> questions = repository.findAll();
        if (questions == null)
            return new ArrayList<>();
        else return questions;
    }
}
