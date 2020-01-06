package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.model.Event;
import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import cz.macha.spring.service.UserService;
import cz.macha.spring.validation.CategoryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @PostMapping("/addcategory")
    public ModelAndView addCategory(@RequestParam String name,
                                    Authentication auth, Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView();

        CategoryValidation validation = new CategoryValidation(categoryService);
        validation.validation(name);
        List<String> errors = validation.getErrors();

        if (errors.size() == 0) {
            name = name.toLowerCase();
            Category category = new Category(name.toLowerCase(), adminService.getUserByLogin(auth.getName()));
            categoryService.addCategory(category);
            modelAndView.setViewName("redirect:/admin");
            return modelAndView;
        }

        StringBuilder categoryErrors = new StringBuilder();
        for (String error : errors) {
            categoryErrors.append("<p>").append(error).append("</p>");
            System.out.println(error);
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
