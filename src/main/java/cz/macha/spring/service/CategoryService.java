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

    @Transactional(readOnly = true)
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategory(int id){
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Category getCategoryByName(String name){
        return categoryRepository.getCategoryByName(name);
    }

    @Transactional
    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Integer id, Category category){
        Objects.requireNonNull(categoryRepository.findById(id).orElse(null)).setName(category.getName());

        categoryRepository.save(categoryRepository.findById(id).orElse(null));
    }

    @Transactional
    public void deleteCategory(Integer id){
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void addEvent(Category category, Event event) {
        Objects.requireNonNull(category);
        Objects.requireNonNull(event);
        event.addCategory(category);
        eventRepository.save(event);
    }

    @Transactional
    public void removeEvent(Category category, Event event) {
        Objects.requireNonNull(category);
        Objects.requireNonNull(event);
        event.removeCategory(category);
        eventRepository.save(event);
    }
}
