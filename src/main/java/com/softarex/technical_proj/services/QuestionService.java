package com.softarex.technical_proj.services;

import com.softarex.technical_proj.entities.Question;
import com.softarex.technical_proj.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }
}
