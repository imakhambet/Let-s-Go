package cz.macha.spring.service;

import cz.macha.spring.model.User;
import cz.macha.spring.model.UserDetailsImpl;
import cz.macha.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findCustomerByLogin(username);
        UserDetailsImpl userDetails = null;
        if (user != null) {
            userDetails = new UserDetailsImpl(user);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        System.out.println(userDetails.getAuthorities());
        return userDetails;

    }
}
