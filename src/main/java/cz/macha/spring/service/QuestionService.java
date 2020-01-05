package cz.macha.spring.service;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Question;
import cz.macha.spring.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public void addCategory(Question question){
        questionRepository.save(question);
    }

    public void updateCategory(Integer id, Question question){
        Objects.requireNonNull(questionRepository.findById(id).orElse(null)).
                setQuestion(question.getQuestion());

        questionRepository.save(questionRepository.findById(id).orElse(null));
    }

    public void deleteCategory(Integer id){
        questionRepository.deleteById(id);
    }

}
