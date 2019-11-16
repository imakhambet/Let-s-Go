package cz.cvut.kbss.ear.eshop.dao;

import cz.cvut.kbss.ear.eshop.model.EventCategory;
import org.springframework.stereotype.Repository;

@Repository
public class EventCategoryDao extends BaseDao<EventCategory> {

    public EventCategoryDao() {
        super(EventCategory.class);
    }
}
