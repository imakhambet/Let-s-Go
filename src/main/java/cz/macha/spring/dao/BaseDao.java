package cz.macha.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class BaseDao<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> type;


    protected BaseDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public T find(Integer id) {
        Objects.requireNonNull(id);
        return em.find(type, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
    }

    @Override
    public void persist(T entity) {
        Objects.requireNonNull(entity);
        em.persist(entity);
    }

    @Override
    public void persist(Collection<T> entities) {
        Objects.requireNonNull(entities);
        if (entities.isEmpty()) {
            return;
        }
        entities.forEach(this::persist);
    }

    @Override
    public T update(T entity) {
        Objects.requireNonNull(entity);
        return em.merge(entity);
    }

    @Override
    public void remove(T entity) {
        Objects.requireNonNull(entity);
        final T toRemove = em.merge(entity);
        if (toRemove != null) {
            em.remove(toRemove);
        }
    }

    @Override
    public boolean exists(Integer id) {
        return id != null && em.find(type, id) != null;
    }
}
