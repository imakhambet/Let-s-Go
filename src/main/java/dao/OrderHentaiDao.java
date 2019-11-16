package dao;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderHentaiDao {
    public int id;
    public int owner;
    public int eventTicket;
    public Date date;
    public int quantity;
    public float totalAmount;

}
