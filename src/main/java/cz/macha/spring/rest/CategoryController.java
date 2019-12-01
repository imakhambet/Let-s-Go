package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    UserService adminService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    EventService eventService;

    @RequestMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admins/{id}/categories")
    public void addCategory(@RequestBody Category category, @PathVariable Integer id){
        category.setCreator(adminService.getUser(id));
        categoryService.addCategory(category);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/categories/{id}")
    public void updateCategory(@RequestBody Category category, @PathVariable Integer id){
        categoryService.updateCategory(id, category);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/categories/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/categories/{name}/events/{id}")
    public void addEventToCategory(@PathVariable String name, @PathVariable Integer id) {
        Category category = categoryService.getCategoryByName(name);
        Event event = eventService.getEvent(id);
        categoryService.addEvent(category, event);
    }

    @DeleteMapping(value = "/categories/{name}/events/{id}")
    public void removeProductFromCategory(@PathVariable String name,
                                          @PathVariable Integer id) {
        final Category category = categoryService.getCategoryByName(name);
        final Event toRemove = eventService.getEvent(id);
        categoryService.removeEvent(category, toRemove);
    }
}
