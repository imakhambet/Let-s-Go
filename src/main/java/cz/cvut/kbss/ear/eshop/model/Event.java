package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

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
    private EventCategory category;

    @OneToOne
    private Organizer organizer;

    @OneToOne
    private Place place;


}
