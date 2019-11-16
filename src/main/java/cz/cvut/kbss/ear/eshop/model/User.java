package cz.cvut.kbss.ear.eshop.model;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "EAR_USER")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})

public class User extends AbstractEntity{

    @Basic(optional = false)
    @Column(nullable = false)
    private String login;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @Enumerated
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void encodePassword(PasswordEncoder encoder){
        this.password=encoder.encode(password);
    }

    public void erasePassword(){
        this.password=null;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



}
