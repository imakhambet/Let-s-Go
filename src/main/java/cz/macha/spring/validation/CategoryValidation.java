package cz.macha.spring.validation;

import cz.macha.spring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidation {

    private final CategoryService categoryService;
    private List<String> errors = new ArrayList<>();


    @Autowired
    public CategoryValidation(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void validation(String name){
        name(name);
    }

    private void name(String name){
        if(name.isEmpty()){
            errors.add("CATEGORY NAME IS REQUIRED");
        }
        else if(categoryService.getCategoryByName(name) != null){
            errors.add("CATEGORY ALREADY EXISTS");
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
