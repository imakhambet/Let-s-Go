package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.model.Place;
import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.UserService;
import cz.macha.spring.validation.AddCategoryValidation;
import cz.macha.spring.validation.CategoryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    UserService adminService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    EventService eventService;

    @RequestMapping("/categories")
    public ModelAndView getAllCategories(Authentication authentication, Map<String, Object> model) {
        List<Category> categories = categoryService.getAllCategories();

        if (authentication != null) {
            model.put("name", "<li><a href=\"#\" id=\"authName\">" + authentication.getName() + "</a></li>");
            model.put("logout", "<li id=\"logout\"><a href=\"/logout\">Logout</a></li>");

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ORGANIZER"))) {
                model.put("link", "<li id=\"link\"><a href=\"/createevent\">Add new event</a></li>");
            }
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                model.put("link", "<li id=\"link\"><a href=\"/admin\">Admin page </a></li>");
            }
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                model.put("link", "<li id=\"link\"><a href=\"/myorders\">My orders</a></li>");
            }
        } else {
            model.put("login", "<li id=\"login\"><a href=\"/login\">Login</a></li>");
            model.put("registration", "<li id=\"registration\"><a href=\"/registration\">Registration</a></li>");
        }
        model.put("categories", categories);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");
        return modelAndView;

    }


    @PostMapping("/addcategory")
    public ModelAndView addCategory(@RequestParam String name,
                                    Authentication auth, Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView();

        CategoryValidation validation = new CategoryValidation(categoryService);
        validation.validation(name);
        List<String> errors = validation.getErrors();

        if (errors.size() == 0) {
            Category category = new Category(name, adminService.getUserByLogin(auth.getName()));
            categoryService.addCategory(category);
            modelAndView.setViewName("redirect:/admin");
            return modelAndView;
        }

        StringBuilder categoryErrors = new StringBuilder();
        for (String error : errors) {
            categoryErrors.append("<p class=\"error\">").append(error).append("</p>");
        }
        model.put("errors", categoryErrors.toString());
        modelAndView.setViewName("admin/createcategory");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/categories/{id}")
    public void updateCategory(@RequestBody Category category, @PathVariable Integer id) {
        categoryService.updateCategory(id, category);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/categories/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }

    @PostMapping( "/addcategoryev/{eventid}")
    public ModelAndView addEventToCategory(@PathVariable int eventid, @RequestParam String name,
                                           Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView();
        AddCategoryValidation validation = new AddCategoryValidation(categoryService, eventService);
        validation.validation(name, eventid);
        List<String> errors = validation.getErrors();

        if (errors.size() == 0){
            Event event = eventService.getEvent(eventid);
            Category category = categoryService.getCategoryByName(name);
            categoryService.addEvent(category, event);
            modelAndView.setViewName("redirect:/event/" + eventid);
            return modelAndView;
        }

        StringBuilder errorsS = new StringBuilder();
        for (String error : errors) {
            errorsS.append("<p class=\"error\">").append(error).append("</p>");
        }
        model.put("errors", errorsS.toString());
        modelAndView.setViewName("organizer/addcategory");
        return modelAndView;
    }

    @DeleteMapping(value = "/categories/{cid}/events/{id}")
    public void removeProductFromCategory(@PathVariable int cid,
                                          @PathVariable Integer id) {
        final Category category = categoryService.getCategory(cid);
        final Event toRemove = eventService.getEvent(id);
        categoryService.removeEvent(category, toRemove);
    }
}
