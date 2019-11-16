package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION")
public class Question extends AbstractEntity{
    @Column
    private String question;

    @Column
    private boolean answered;

    @ManyToOne
    private Customer owner;

    @OneToOne
    private Answer answer;

    @OneToOne
    private Event event;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
