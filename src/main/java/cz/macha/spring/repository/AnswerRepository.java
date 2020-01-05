package cz.macha.spring.repository;

import cz.macha.spring.model.Answer;
import cz.macha.spring.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Answer findAnswerByQuestion(Question question);
}
