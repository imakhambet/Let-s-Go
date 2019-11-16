package cz.cvut.kbss.ear.eshop.model;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.util.Date;

public class Order extends AbstractEntity {

    @Column
    private Date date;

    @Column
    private int quantity;

    @Column
    private float totalAmount;

    @OneToOne
    private EventTicket eventTicket;

    @OneToOne
    private Customer owner;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public EventTicket getEventTicket() {
        return eventTicket;
    }

    public void setEventTicket(EventTicket eventTicket) {
        this.eventTicket = eventTicket;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
