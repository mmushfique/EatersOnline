package com.mush.EatersOnline.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mush.EatersOnline.Model.Food;
import com.mush.EatersOnline.Model.Shop;
import com.mush.EatersOnline.Security.ShopUserDetails;
import com.mush.EatersOnline.Service.FoodService;
import com.mush.EatersOnline.Service.ShopService;


@Controller
@RequestMapping("/Food")
public class FoodController {
	
	@Autowired
	private FoodService service;
	@Autowired
	private ShopService shopService;
	
//	@GetMapping("/")
//    public String viewFoods(Model model) { 
//	   	List<Food> listFood = service.listAll();
//        model.addAttribute("listFood", listFood);
//        return "food";
//    }
	    
	
	@GetMapping("/newMenu")
    public String addFood(Model model) { 
    	List<Food> listFood = service.listAll();
        model.addAttribute("listFood", listFood);
        model.addAttribute("food", new Food());
        return "newMenu";
    }
	
	@PostMapping("/save")
    public String saveFood(@ModelAttribute(name="food") Food food,@RequestParam("fileimage") MultipartFile multipartFile,@AuthenticationPrincipal ShopUserDetails loggedShop,BindingResult result) throws IOException {
//		if (result.hasErrors()) {
//		    return "newMenu";
//		  }
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        food.setImage(fileName);
        food.setShopusername(loggedShop.getUsername());
        Food savedFood=service.save(food);
        
        String uploadDir ="./food-images/" + savedFood.getId();
        Path uploadPath = Paths.get(uploadDir);
        
        if(!Files.exists(uploadPath)) {
        	Files.createDirectories(uploadPath);
        }
        
        InputStream inputStream = multipartFile.getInputStream();
        try{
        	Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        
        }catch(Exception e) {
        	throw new IOException("Could not save the file" + fileName);
        }
        return "redirect:/Shop/myFood";
    }
	
	
	@RequestMapping("/view/{id}")
    public ModelAndView viewFood(@PathVariable(name = "id") int id,HttpSession session) {
    	ModelAndView mav = new ModelAndView("viewFood");
        Food food = service.get(id);
        Shop shop=shopService.get(food.getShopusername()); 
        mav.addObject("food", food);

        session.setAttribute("sname",shop.getShopname());
        session.setAttribute("sloc",shop.getLocation());
        return mav;    
    }
	
	@RequestMapping("/edit/{id}")
    public ModelAndView editFood(@PathVariable(name = "id") int id) {
    	ModelAndView mav = new ModelAndView("newMenu");
        Food food = service.get(id);
        mav.addObject("food", food);
        return mav;   
    }
    @RequestMapping("/delete/{id}")
    public String deleteFood(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/Shop/myFood";
    }
}
