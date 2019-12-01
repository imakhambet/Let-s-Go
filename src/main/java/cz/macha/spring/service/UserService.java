package cz.macha.spring.service;

import cz.macha.spring.dao.UserDao;
import cz.macha.spring.model.Category;
import cz.macha.spring.model.User;
import cz.macha.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDao userDao;

//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByLogin(String login) {
        return userDao.findByUsername(login);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User user1 = userRepository.findById(id).orElse(null);
        user1.setEmail(user.getEmail());
        user1.setLogin(user.getLogin());
        user1.setPassword(user.getPassword());

        userRepository.save(user1);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}
