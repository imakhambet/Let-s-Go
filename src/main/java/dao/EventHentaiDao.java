package dao;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EventHentaiDao {
    public int id;
    public int category;
    public int organizer;
    public String name;
    public String description;
    public Date date;
    public int place;
    public boolean active;
}
