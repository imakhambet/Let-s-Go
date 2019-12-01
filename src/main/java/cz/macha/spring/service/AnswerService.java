package cz.macha.spring.service;

import cz.macha.spring.model.Answer;
import cz.macha.spring.model.Question;
import cz.macha.spring.repository.AnswerRepository;
import cz.macha.spring.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionRepository questionRepository;

    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }

    public Answer getAnswerByQuestion(Question question){
        return answerRepository.findAnswerByQuestion(question);
    }


}
