package cz.macha.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "eventTicket_id")
    private EventTicket eventTicket;

    @Column
    private int quantity;

    @Column
    private String date;

    public Order() {
    }

    public Order(Customer customer, EventTicket eventTicket, String name, int quantity, String date) {
        this.customer = customer;
        this.eventTicket = eventTicket;
        this.quantity = quantity;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public EventTicket getEventTicket() {
        return eventTicket;
    }

    public void setEventTicket(EventTicket eventTicket) {
        this.eventTicket = eventTicket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
