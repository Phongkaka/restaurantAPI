package com.restaurant.category;

import com.restaurant.common.model.Category;
import com.restaurant.common.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Get all category
     *
     * @return
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Find by id
     *
     * @param id
     * @return
     */
    public Optional<Category> findOne(int id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findCategory(String name) {
        return categoryRepository.findCategory(name);
    }

    /**
     * Create a new category
     *
     * @param category
     * @return
     */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Delete category by id
     *
     * @param category
     */
    public void delete(Optional<Category> category) {
        categoryRepository.deleteById(category.get().getId());
    }
}
