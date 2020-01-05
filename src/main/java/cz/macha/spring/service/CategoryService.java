package cz.macha.spring.service;

import cz.macha.spring.model.Event;
import cz.macha.spring.repository.CategoryRepository;
import cz.macha.spring.model.Category;
import cz.macha.spring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


    public Category getCategoryByName(String name){
        return categoryRepository.getCategoryByName(name);
    }

//    public Category getCategoryById(Integer id){
//        return categoryRepository.findById(id).orElse(null);
//    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category){
        Objects.requireNonNull(categoryRepository.findById(id).orElse(null)).setName(category.getName());

        categoryRepository.save(categoryRepository.findById(id).orElse(null));
    }

    public void deleteCategory(Integer id){
        categoryRepository.deleteById(id);
    }

    public void addEvent(Category category, Event event) {
        Objects.requireNonNull(category);
        Objects.requireNonNull(event);
        event.addCategory(category);
        eventRepository.save(event);
    }

    public void removeEvent(Category category, Event event) {
        Objects.requireNonNull(category);
        Objects.requireNonNull(event);
        event.removeCategory(category);
        eventRepository.save(event);
    }
}
