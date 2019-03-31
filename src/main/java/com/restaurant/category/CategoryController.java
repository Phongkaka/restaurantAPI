package com.restaurant.category;

import com.restaurant.common.exception.CategoryNotFoundException;
import com.restaurant.common.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * url
     */
    private static final String FIND_ALL_CATEGORY_URL = "/categories";
    private static final String CREATE_CATEGORY_URL = "/categories";
    private static final String FIND_CATEGORY_BY_ID_URL = "/category/{id}";
    private static final String UPDATE_CATEGORY_BY_ID_URL = "/category/{id}";
    private static final String DELETE_CATEGORY_BY_ID_URL = "/category/{id}";
    private static final String FIND_CATEGORY_URL = "/category/search";

    private static final String MSG_CATEGORY_NOT_FOUND = "Not found category by id: ";

    /**
     * Find all category
     *
     * @return
     */
    @GetMapping(FIND_ALL_CATEGORY_URL)
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    /**
     * Find category by id.
     *
     * @param id
     * @return
     */
    @GetMapping(FIND_CATEGORY_BY_ID_URL)
    public Category findCategoryById(@PathVariable(value = "id") int id) {

        Optional<Category> category = categoryService.findOne(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException(MSG_CATEGORY_NOT_FOUND + id);
        }
        return category.get();
    }

    /**
     * Find category by name
     *
     * @param name
     * @return
     */
    @GetMapping(FIND_CATEGORY_URL)
    public List<Category> findCategory(
            @RequestParam(value = "name", required = false) String name) {
        return categoryService.findCategory(name);
    }

    /**
     * Create a new category
     *
     * @param category
     * @return
     */
    @PostMapping(CREATE_CATEGORY_URL)
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    /**
     * Update category by id
     *
     * @param id
     * @param categoryDetail
     * @return
     */
    @PutMapping(UPDATE_CATEGORY_BY_ID_URL)
    public Category updateCategory(@PathVariable(value = "id") int id,
                                   @Valid @RequestBody Category categoryDetail) {
        Optional<Category> categoryOptional = categoryService.findOne(id);
        if (!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException(MSG_CATEGORY_NOT_FOUND + id);
        } else {
            Category category = categoryOptional.get();
            category.setName(categoryDetail.getName());

            Category updateCategory = categoryService.save(category);
            return updateCategory;
        }
    }

    /**
     * Delete category by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(DELETE_CATEGORY_BY_ID_URL)
    public Category deleteCategory(@PathVariable(value = "id") int id) {
        Optional<Category> category = categoryService.findOne(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException(MSG_CATEGORY_NOT_FOUND + id);
        } else {
            categoryService.delete(category);
            return category.get();
        }
    }
}
