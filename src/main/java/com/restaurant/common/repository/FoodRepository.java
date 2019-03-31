package com.restaurant.common.repository;

import com.restaurant.common.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("SELECT f FROM Food f " +
            "WHERE (f.name LIKE CONCAT('%',:name,'%') OR :name = '')" +
            " AND (f.categoryId LIKE CONCAT('%',:categoryId,'%') OR :categoryId IS null)" +
            " AND (f.price <= :price OR :price IS null)"
    )
    List<Food> findFood(@Param("name") String name,
                        @Param("categoryId") Integer categoryId,
                        @Param("price") Double price
    );
}
