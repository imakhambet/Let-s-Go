package cz.macha.spring.rest;

import cz.macha.spring.model.Organizer;
import cz.macha.spring.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @RequestMapping("/organizers")
    public List<Organizer> getAllUsers() {
        return organizerService.getAllOrganizer();
    }

    @RequestMapping("/organizers/{id}")
    public Organizer getUser(@PathVariable Integer id){
        return organizerService.getOrganizer(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/organizers")
    public void addUser(@RequestBody Organizer organizer){
        organizerService.addOrganizer(organizer);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/organizers/{id}")
    public void updateUser(@RequestBody Organizer organizer, @PathVariable Integer id){
        organizerService.updateOrganizer(id, organizer);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/organizers/{id}")
    public void deleteUser(@PathVariable Integer id){
        organizerService.deleteOrganizer(id);
    }
}
