package cz.macha.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    public Event() {
    }

    public Event(Organizer organizer, Category category, Place place, String name, String description, String date) {
        this.organizer = organizer;
        this.category = category;
        this.place = place;
        this.name = name;
        this.description = description;
        this.date = date;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "event", cascade = {
            CascadeType.PERSIST
    })
    private List<EventTicket> eventTickets;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String date;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonIgnore
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<EventTicket> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(List<EventTicket> eventTickets) {
        this.eventTickets = eventTickets;
    }
}
