package com.mush.EatersOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mush.EatersOnline.Model.Food;
import com.mush.EatersOnline.Repository.FoodRepo;

@Service
public class FoodService {

	@Autowired
	private FoodRepo repo;
	
	public List<Food> listAll() {
        return repo.findAll();
    }
     
	public List<Food> listAllMy(String shopusername) {
        return repo.getMyFood(shopusername);
    }
	
    public Food save(Food food) {
        repo.save(food);
    return food;
    }
    
    public Food get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
    
    
}
