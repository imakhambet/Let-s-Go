//package cz.macha.spring.service;
//
//import cz.macha.spring.model.Role;
//import cz.macha.spring.model.User;
//import cz.macha.spring.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.beans.Transient;
//import java.util.HashSet;
//import java.util.Set;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findCustomerByLogin(username);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//
//        for (Role role: user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(), user.getPassword(), grantedAuthorities);
//    }
//}
