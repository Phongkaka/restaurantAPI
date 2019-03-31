package com.restaurant.food;

import com.restaurant.common.exception.FoodNotFoundException;
import com.restaurant.common.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    /**
     * url
     */
    private static final String FIND_ALL_FOOD_URL = "/foods";
    private static final String CREATE_FOOD_URL = "/foods";
    private static final String FIND_FOOD_BY_ID_URL = "/food/{id}";
    private static final String UPDATE_FOOD_BY_ID_URL = "/food/{id}";
    private static final String DELETE_FOOD_BY_ID_URL = "/food/{id}";
    private static final String FIND_FOOD_URL = "/food/search";

    private static final String MSG_FOOD_NOT_FOUND = "Not found food by id: ";

    /**
     * Find all food.
     *
     * @return
     */
    @GetMapping(FIND_ALL_FOOD_URL)
    public List<Food> findAll() {
        return foodService.findAll();
    }

    /**
     * Find food by id
     *
     * @param id
     * @return
     */
    @GetMapping(FIND_FOOD_BY_ID_URL)
    public Food findFoodById(@PathVariable(value = "id") int id) {

        Optional<Food> food = foodService.findOne(id);
        if (!food.isPresent()) {
            throw new FoodNotFoundException(MSG_FOOD_NOT_FOUND + id);
        }
        return food.get();
    }

    /**
     * Find food by name or categoryId or price
     *
     * @param name name
     * @param price price
     * @param categoryId categoryId
     * @return
     */
    @GetMapping(FIND_FOOD_URL)
    public List<Food> findFood(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "price", required = false) Double price) {

        return foodService.findFood(name, categoryId, price);
    }

    /**
     *
     * Created new food
     *
     * @param food
     * @return
     */
    @PostMapping(CREATE_FOOD_URL)
    public Food createFood(@Valid @RequestBody Food food) {
        return foodService.save(food);
    }

    /**
     * Update food by id
     *
     * @param id
     * @param foodDetail
     * @return
     */
    @PutMapping(UPDATE_FOOD_BY_ID_URL)
    public Food updateFood(@PathVariable(value = "id") int id,
                           @Valid @RequestBody Food foodDetail) {

        Optional<Food> foodOptional = foodService.findOne(id);
        if (!foodOptional.isPresent()) {
            throw new FoodNotFoundException(MSG_FOOD_NOT_FOUND + id);
        } else {
            Food food = foodOptional.get();
            food.setName(foodDetail.getName());
            food.setQuantity(foodDetail.getQuantity());
            food.setPrice(foodDetail.getPrice());
            food.setCategoryId(foodDetail.getCategoryId());
            food.setCategory(foodDetail.getCategory());

            Food updateFood = foodService.save(food);
            return updateFood;
        }
    }

    /**
     * Delete food by id
     *
     * @param id
     * @return
     */
    @DeleteMapping(DELETE_FOOD_BY_ID_URL)
    public Food deleteFood(@PathVariable(value = "id") int id) {

        Optional<Food> food = foodService.findOne(id);
        if (!food.isPresent()) {
            throw new FoodNotFoundException(MSG_FOOD_NOT_FOUND + id);
        } else {
            foodService.delete(food);
            return food.get();
        }
    }

}
