package com.mush.EatersOnline.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mush.EatersOnline.Model.Food;
import com.mush.EatersOnline.Service.FoodService;

@Controller
public class IndexController {

	@Autowired
	private FoodService service;
	
	//To read all data to the page
	@GetMapping("/")
	public String viewFoods(Model model) {
		List<Food> listFood = service.listAll();
		model.addAttribute("listFood", listFood);
		return "index";
	}
	
	@RequestMapping("/order/{id}")
    public ModelAndView viewFood(@PathVariable(name = "id") int id) { 
    	ModelAndView mav = new ModelAndView("viewFood");
        Food food = service.get(id);
        mav.addObject("food", food);
        return mav;
	}

}
