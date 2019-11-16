package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class QuestionHentaiDao {
    public int id;
    public int owner;
    public int event;
    public String questrion;
    public boolean answered;
}
