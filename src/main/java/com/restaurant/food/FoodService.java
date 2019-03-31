package com.restaurant.food;

import com.restaurant.common.model.Food;
import com.restaurant.common.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    /**
     * Get all Food.
     *
     * @return
     */
    public List<Food> findAll() {
       return foodRepository.findAll();
    }

    /**
     * Get food by id
     *
     * @param id
     * @return
     */
    public Optional<Food> findOne(int id) {
        return foodRepository.findById(id);
    }

    /**
     * Get food by name or categoryId or price
     *
     * @param name
     * @param categoryId
     * @param price
     * @return
     */
    public List<Food> findFood(String name, Integer categoryId, Double price) {
        return foodRepository.findFood(name, categoryId, price);
    }


    /**
     * Create new Food
     *
     * @param food
     * @return
     */
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    /**
     * Delete a food by id
     *
     * @param food
     */
    public void delete(Optional<Food> food) {
        foodRepository.deleteById(food.get().getId());
    }
}
