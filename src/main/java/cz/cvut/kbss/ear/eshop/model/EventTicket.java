package cz.cvut.kbss.ear.eshop.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name =  "EVENT_TICKET")
public class EventTicket extends AbstractEntity {

    @Column
    private String name;

    @Column
    private float price;

    @Column
    private int dropQuantity;

    @Column
    private float soldQuantity;

    @OneToOne
    private Event event;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDropQuantity() {
        return dropQuantity;
    }

    public void setDropQuantity(int dropQuantity) {
        this.dropQuantity = dropQuantity;
    }

    public float getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(float soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
