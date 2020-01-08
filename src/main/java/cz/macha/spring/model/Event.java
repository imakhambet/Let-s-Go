package cz.macha.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "events")
public class Event {

    public Event() {
    }

    public Event(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @ManyToMany
    @JoinTable(name = "event_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @OrderBy("name asc")
    private Set<Category> category;

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

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    @JsonIgnore
    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    @JsonIgnore
    public Place getPlace() {
        return place;
    }

    public String getPlaceName() {
        if (place != null)
            return place.getName();
        return "-";
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

    public void addCategory(Category category1) {
        Objects.requireNonNull(category1);
        if (category == null) {
            this.category = new HashSet<>();
        }
        category.add(category1);
    }

    public void removeCategory(Category category1) {
        Objects.requireNonNull(category1);
        if (category == null) {
            return;
        }
        category.removeIf(c -> Objects.equals(c.getId(), category1.getId()));
    }
}
