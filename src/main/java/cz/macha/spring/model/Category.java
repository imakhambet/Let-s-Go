package cz.macha.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    public Category() {
    }

    public Category(String name, Admin creator) {
        super();
        this.name = name;
        this.creator = creator;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "creator_id")
    private Admin creator;

    @OneToMany(mappedBy = "category", cascade = {
            CascadeType.PERSIST
    })
    private List<Event> events;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Admin getCreator() {
        return creator;
    }

    public void setCreator(Admin creator) {
        this.creator = creator;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
