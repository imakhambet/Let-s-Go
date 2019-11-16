package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_CATEGORY")
public class EventCategory extends AbstractEntity {

    @Column
    private String name;

    @OneToOne
    private Admin creator;
}
