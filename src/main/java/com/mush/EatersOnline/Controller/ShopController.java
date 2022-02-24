package com.mush.EatersOnline.Controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mush.EatersOnline.Service.FoodService;
import com.mush.EatersOnline.Service.ShopService;
import com.mush.EatersOnline.Model.Food;
import com.mush.EatersOnline.Model.Shop;
import com.mush.EatersOnline.Repository.ShopRepo;
import com.mush.EatersOnline.Security.CustomerUserDetails;
import com.mush.EatersOnline.Security.ShopUserDetails;

@Controller
@RequestMapping("/Shop")
public class ShopController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private ShopService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ShopRepo shopRepo;
	
	
	
	@GetMapping("/")
	public String viewShops(Model model) {
		List<Shop> listShop = service.listAll();
		model.addAttribute("listShop", listShop);
		return "shop";
	}
	
	@GetMapping("/myFood")
	public String viewMyFood(@AuthenticationPrincipal ShopUserDetails loggedShop,Model model,HttpSession session) {
		        List<Food> listFood = foodService.listAllMy(loggedShop.getUsername());
		        model.addAttribute("listFood", listFood);
		        
		        
		        Boolean st=shopRepo.onlinestatus(loggedShop.getUsername());
		        if(st) {
		        session.setAttribute("status",true);
		        }else {
		        	session.setAttribute("status",false);
		        }
		        
		        session.setAttribute("menu", shopRepo.countFood(loggedShop.getUsername()));
		        session.setAttribute("order", shopRepo.countOrder(loggedShop.getUsername()));
		        session.setAttribute("preorder", shopRepo.countpreOrder(loggedShop.getUsername()));
		        
//		        int fcount=shopRepo.countFood(loggedShop.getUsername());
//		        System.out.println(fcount);
//		        //ra.addFlashAttribute("listFood", fcount);
//		        session.setAttribute("mySessionAttribute", fcount);
//		        
//		        TimerTask timerTask = new TimerTask() {
//		            @Override
//		            public void run() {
//		            	int fcount=shopRepo.countFood(loggedShop.getUsername());
//				        System.out.println(fcount);
//				        session.setAttribute("mySessionAttribute", fcount);
//				        
//		            }
//
//		        };
//
//		        Timer timer = new Timer();
//
//		        //Schedule the specified task to start repeated, fixed delays at a specified time.This is executed every 3 seconds
//		        timer.schedule(timerTask,10,3000);
		        return "shopPanel";
		    }

	
	 
@RequestMapping("/state")
	public String doPost(HttpServletRequest req,HttpSession session,@AuthenticationPrincipal ShopUserDetails loggedShop) {
		String checkbox=req.getParameter("checkbox");
		if(checkbox==null) {
			Shop shop=service.get(loggedShop.getUsername());
			shop.setOnlinestatus(false);
			service.save(shop);
		}
		else {
			Shop shop=service.get(loggedShop.getUsername());
			shop.setOnlinestatus(true);
			service.save(shop);
		}
		return "redirect:/Shop/myFood/";
		}//re

	
	
	@GetMapping("/createShop")
	public String addShop(Model model) {
		List<Shop> listShop = service.listAll();
		model.addAttribute("listShop",listShop);
		model.addAttribute("shop",new Shop());	
		return "createShop";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveShop(@Valid @ModelAttribute("shop") Shop shop,BindingResult result) {
		if (result.hasErrors()) {
		    return "createShop";
		  }
		
    	shop.setPassword(bCryptPasswordEncoder.encode(shop.getPassword()));
		service.save(shop);
		return "redirect:/Shop/myFood/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editShop(@PathVariable(name="id")int id) {
		ModelAndView mav = new ModelAndView("createShop");
		Shop shop = service.get(id);
		mav.addObject("shop",shop);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteShop(@PathVariable(name = "id")int id) {
		service.delete(id);
		return "redirect:/Shop/";
	}
	
	@RequestMapping("/account")
    public String viewAccount(@AuthenticationPrincipal ShopUserDetails loggedShop,Model model) {
    	Shop shop=service.get(loggedShop.getUsername());
    	//shop.setPassword("");
        model.addAttribute("shop", shop);
        return "editShopProfile";
    }
 
    @RequestMapping(value = "/account/update")
    public String updateAccount(Shop shop,@AuthenticationPrincipal ShopUserDetails loggedShop) {
    	String name=shop.getShopname();
    	int phone=shop.getPhone();
    	String loc=shop.getLocation();
    	String pw=shop.getPassword();
    	shop=service.get(loggedShop.getUsername());
    	shop.setShopname(name);
    	shop.setPhone(phone);
    	shop.setLocation(loc);
    	if(pw!="") {
    		shop.setPassword(bCryptPasswordEncoder.encode(pw));
    	}
    	service.save(shop);	
    	return "redirect:/Shop/account";
    }
}
