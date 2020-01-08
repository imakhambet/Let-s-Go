package cz.macha.spring.service;

import cz.macha.spring.dao.UserDao;
import cz.macha.spring.model.*;
import cz.macha.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDao userDao;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userDao.findByUsername(login);
    }

    @Transactional
    public void addUser(User user){
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleRepository.getOne(1));
//        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Integer id, User user){
        User user1 = userRepository.findById(id).orElse(null);
        assert user1 != null;
        user1.setEmail(user.getEmail());
        user1.setLogin(user.getLogin());
        user1.setPassword(user.getPassword());

        userRepository.save(user1);
    }

    @Transactional
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void createEvent(Event event){
        eventRepository.save(event);
    }

    @Transactional
//pouze pro role==zakaznik
    public void addQuestion(Integer cId, Integer eId, Question question){
        question.setEvent(eventRepository.findById(eId).orElse(null));
        question.setCustomer(userRepository.findById(cId).orElse(null));
        questionRepository.save(question);
    }

    @Transactional
    //pouze pro role==organizer
    public void addAnswer(Integer oId, Integer qId, Answer answer){
        answer.setOrganizer(userRepository.findById(oId).orElse(null));
        answer.setQuestion(questionRepository.findById(qId).orElse(null));
        answerRepository.save(answer);
    }
}
