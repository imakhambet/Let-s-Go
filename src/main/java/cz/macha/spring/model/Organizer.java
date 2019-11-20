package cz.macha.spring.model;

//import cz.macha.spring.model.Event;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organizers")
public class Organizer {

    public Organizer() {
    }


    public Organizer(String login, String password, String email, String phone) {
        super();
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @OneToMany(mappedBy = "organizer", cascade = {
            CascadeType.PERSIST
    })
    private List<Event> events;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
