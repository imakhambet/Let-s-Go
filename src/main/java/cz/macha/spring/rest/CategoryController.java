package cz.macha.spring.rest;

import cz.macha.spring.model.Category;
import cz.macha.spring.service.AdminService;
import cz.macha.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    AdminService adminService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admins/{id}/categories")
    public void addCategory(@RequestBody Category category, @PathVariable Integer id){
        category.setCreator(adminService.getAdmin(id));
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
}
