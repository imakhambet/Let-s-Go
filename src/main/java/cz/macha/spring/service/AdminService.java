package cz.macha.spring.service;

import cz.macha.spring.dao.AdminDao;
import cz.macha.spring.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public List<Admin> getAllAdmins(){
        return adminDao.findAll();
    }

    public Admin getAdmin(Integer id){
        return adminDao.findOne(id);
    }

    public void addAdmin(Admin admin){
        adminDao.save(admin);
    }

    public void updateAdmin(Integer id, Admin admin){
        adminDao.findOne(id).setLogin(admin.getLogin());
        adminDao.findOne(id).setPassword(admin.getPassword());

        adminDao.save(adminDao.findOne(id));
    }

    public void deleteAdmin(Integer id){
        adminDao.delete(id);
    }
}
