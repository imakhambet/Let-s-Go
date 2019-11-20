package cz.macha.spring.rest;


import cz.macha.spring.model.Admin;
import cz.macha.spring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admins")
    public List<Admin> getAllUsers() {
        return adminService.getAllAdmins();
    }

    @RequestMapping("/admins/{id}")
    public Admin getUser(@PathVariable Integer id){
        return adminService.getAdmin(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admins")
    public void addUser(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admins/{id}")
    public void updateUser(@RequestBody Admin admin, @PathVariable Integer id){
        adminService.updateAdmin(id, admin);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admins/{id}")
    public void deleteUser(@PathVariable Integer id){
        adminService.deleteAdmin(id);
    }
}
