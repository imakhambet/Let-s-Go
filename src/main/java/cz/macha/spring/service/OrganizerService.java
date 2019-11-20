package cz.macha.spring.service;

import cz.macha.spring.dao.OrganizerDao;
import cz.macha.spring.model.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerDao organizerDao;

    public List<Organizer> getAllOrganizer(){
        return organizerDao.findAll();
    }

    public Organizer getOrganizer(Integer id){
        return organizerDao.findOne(id);
    }

    public void addOrganizer(Organizer organizer){
        organizerDao.save(organizer);
    }

    public void updateOrganizer(Integer id, Organizer organizer){

        organizerDao.findOne(id).setEmail(organizer.getEmail());
        organizerDao.findOne(id).setLogin(organizer.getLogin());
        organizerDao.findOne(id).setPassword(organizer.getPassword());
        organizerDao.findOne(id).setPhone(organizer.getPhone());

        organizerDao.save(organizerDao.findOne(id));
    }

    public void deleteOrganizer(Integer id){
        organizerDao.delete(id);
    }
}
