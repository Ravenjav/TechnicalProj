package com.softarex.technical_proj.services;

import com.softarex.technical_proj.entities.Question;
import com.softarex.technical_proj.entities.QuestionType;
import com.softarex.technical_proj.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public QuestionType setType(String type){
        switch (type){
            case ("SINGLE") : {
                return QuestionType.SINGLE;
            }
            case ("MULTILINE") : {
                return QuestionType.MULTILINE;
            }
            case ("CHECKBOX") : {
                return QuestionType.CHECKBOX;
            }
            case ("COMBOBOX") : {
                return QuestionType.COMBOBOX;
            }
            case ("DATE") : {
                return QuestionType.DATE;
            }
            default:
                return QuestionType.RADIO;
        }
    }

    public void createQuestion(Question question) {
        question.setType(setType(question.getViewType()));
        repository.save(question);
    }

    public void editQuestion(Question question, String type){
        question.setType(setType(type));
        question.setAnswer(null);
        repository.save(question);
    }

    public void editAnswer(Question question, String type, String questionText, Long id){
        question.setId(id);
        question.setQuestionText(questionText);
        question.setType(setType(type));
        repository.save(question);
    }

    public void deleteUserQuestions(String email){
        List <Question> questions = repository.findBySenderEmail(email);
        repository.deleteAll(questions);
        questions = repository.findByResponsibleEmail(email);
        repository.deleteAll(questions);
    }

    public Question findById(Long id){
        Optional<Question> question = repository.findById(id);
        return question.orElse(null);
    }
}
