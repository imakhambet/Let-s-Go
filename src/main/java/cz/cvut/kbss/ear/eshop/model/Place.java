package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLACE")
public class Place extends AbstractEntity {

    @Column
    private String name;

    @Column
    private String address;

    @OneToOne
    private Admin creator;
}
