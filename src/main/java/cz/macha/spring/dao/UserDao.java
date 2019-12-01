package cz.macha.spring.dao;

import cz.macha.spring.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserDao extends BaseDao {
    protected UserDao() {
        super(User.class);
    }


    public User findByUsername(String username){
        try{
            Query q = em.createNamedQuery("User.findByUsername");
            q.setParameter("username", username);
            return (User) q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
