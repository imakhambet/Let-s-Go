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
}
