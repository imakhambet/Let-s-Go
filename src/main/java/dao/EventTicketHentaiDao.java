package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EventTicketHentaiDao {
    public int id;
    public int event;
    public String name;
    public float price;
    public int dropQuantity;
    public int soldQuantity;
}
