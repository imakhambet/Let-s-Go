package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EVENT")
public class Event extends AbstractEntity {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date date;

    @Column
    private boolean active;

    @OneToOne
    @OrderBy("name")
    private List<EventCategory> category;

    @OneToOne
    private Organizer organizer;

    @OneToOne
    private Place place;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<EventCategory> getCategory() {
        return category;
    }

    public void setCategory(List<EventCategory> category) {
        this.category = category;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
