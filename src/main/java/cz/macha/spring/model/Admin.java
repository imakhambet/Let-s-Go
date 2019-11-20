package cz.macha.spring.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "admins")
public class Admin {
    public Admin() {
    }


    public Admin(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String login;

    @Column
    private String password;

    @OneToMany(mappedBy = "creator", cascade = {
            CascadeType.PERSIST
    })
    private List<Category> categories;

    @OneToMany(mappedBy = "creator", cascade = {
            CascadeType.PERSIST
    })
    private List<Place> places;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
