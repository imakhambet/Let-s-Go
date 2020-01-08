package cz.macha.spring.validation;

import cz.macha.spring.service.CategoryService;
import cz.macha.spring.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddCategoryValidation {

    private final CategoryService categoryService;
    private final EventService eventService;
    private List<String> errors = new ArrayList<>();


    @Autowired
    public AddCategoryValidation(CategoryService categoryService , EventService eventService) {
        this.categoryService = categoryService;
        this.eventService = eventService;
    }

    public void validation(String name, int id){

        add(name, id);
    }

    private void add(String name, int id){
        if (name.equals("Select category")){
            errors.add("CATEGORY NAME IS REQUIRED");
        }else if(categoryService.getCategoryByName(name) == null){
            errors.add("CATEGORY IS NOT EXIST");
        }

    }

    public List<String> getErrors() {
        return errors;
    }
}
