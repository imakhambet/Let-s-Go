package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends User {
    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String phone;

    @OneToMany
    private Question question;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
