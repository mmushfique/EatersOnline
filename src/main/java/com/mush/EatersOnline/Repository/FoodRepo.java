package com.mush.EatersOnline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mush.EatersOnline.Model.Customer;
import com.mush.EatersOnline.Model.Food;
import com.mush.EatersOnline.Model.Shop;

@Repository
public interface FoodRepo extends JpaRepository<Food,Long> {
	
	@Query("SELECT u FROM Food u WHERE u.shopusername = :shopusername")
	public List<Food> getMyFood(@Param("shopusername") String shopusername);
	
	@Query("SELECT f FROM Food f WHERE f.foodcode = :foodcode")
	public Shop getFoodByFoodCode(@Param("foodcode") String foodcode);
}
