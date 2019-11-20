package cz.macha.spring.dao;

import cz.macha.spring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    Category getCategoryByName(String name);
}
