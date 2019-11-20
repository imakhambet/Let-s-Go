package cz.macha.spring.service;

import cz.macha.spring.dao.CategoryDao;
import cz.macha.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAllCategories(){
        return categoryDao.findAll();
    }

    public Category getCategoryByName(String name){
        return categoryDao.getCategoryByName(name);
    }

    public void addCategory(Category category){
        categoryDao.save(category);
    }

    public void updateCategory(Integer id, Category category){
        categoryDao.findOne(id).setName(category.getName());

        categoryDao.save(categoryDao.findOne(id));
    }

    public void deleteCategory(Integer id){
        categoryDao.delete(id);
    }
}
