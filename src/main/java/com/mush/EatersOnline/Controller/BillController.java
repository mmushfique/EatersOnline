package com.mush.EatersOnline.Controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.mush.EatersOnline.Email.EmailSenderService;
import com.mush.EatersOnline.Model.Bill;
import com.mush.EatersOnline.Model.Food;
import com.mush.EatersOnline.Model.Shop;
import com.mush.EatersOnline.Repository.BillRepo;
import com.mush.EatersOnline.Repository.ShopRepo;
import com.mush.EatersOnline.Model.Customer;
import com.mush.EatersOnline.Security.CustomerUserDetails;
import com.mush.EatersOnline.Security.ShopUserDetails;
import com.mush.EatersOnline.Service.BillService;
import com.mush.EatersOnline.Service.FoodService;
import com.mush.EatersOnline.Service.ShopService;
import com.mush.EatersOnline.Service.CustomerService;

@Controller
@RequestMapping("/Bill")
public class BillController {

	@Autowired
	private BillService service;
	
	@Autowired
	private FoodService foodService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private EmailSenderService senderService;

	
	   @Autowired
   	private ShopRepo shopRepo;
	
	String toEmail;
	String emailMsg;

	//To read all data to the page
		 @GetMapping("/")
		    public String viewBill(Model model) {
		    	List<Bill> listBill = service.listAll();
		        model.addAttribute("listBill", listBill);
		        return "bill";
		    }
		 
		 //from shop
		 @GetMapping("/myOrders")
		    public String preOrder(@AuthenticationPrincipal ShopUserDetails loggedShop,Model model) {
			 List<Bill> listBill = service.listAllMy(loggedShop.getUsername());
		        model.addAttribute("listBill", listBill);
		        return "preOrder";
		    }
		 
		 @GetMapping("/allOrders")
		    public String allOrder(@AuthenticationPrincipal ShopUserDetails loggedShop,Model model) {
			 List<Bill> listBill = service.listAllMy(loggedShop.getUsername());
		        model.addAttribute("listBill", listBill);
		        return "allOrder";
		    }
		 
		 //from shop accept order
		 @RequestMapping("/accept/{id}")
		    public String accept(@PathVariable(name = "id") int id) {
			 	Bill bill=service.get(id);
			 	bill.setPending(true);
			 	service.save(bill);
			 	//System.out.println(bill.getId()+" "+bill.isPending());
			 	toEmail=bill.getOrderedUser();
			 	
			 	Shop shop=shopService.get(bill.getShopusername());
			 	String invoice="********************\n   INVOICE   \n********************\n"
		        		+ shop.getShopname()+"\n"+shop.getLocation()+"\n"+shop.getPhone()+"\n"
		        				+ "\n\nYou pre-ordered\n----------------------------"
		        		+bill.getFoodname()+"\nQuantity    " +bill.getQty()+"\n"
		        				+ "Your bill    "+ bill.getTotal()+"\n\n"
		        						+ "You will receive an email from the\n"
		        						+ "restaurant with invoice and estimated time\n"
		        						+ "if the order is accepted\n"
		        						+ "reference id "+bill.getId();
			 	
			 	emailMsg="Welcome to " +shop.getShopname()+"\nYour pre-order placed for "+bill.getFoodname()+ " in " +shop.getShopname()
			 			+ " is accepted.\n Here is your invoice with reference id "+ bill.getId()+"\n\n"+invoice+"\n\n";
			 
			    
			 	return "enterMin";
			}
					 
		@RequestMapping("/time")
			public String doPost(HttpServletRequest req) {
				String min=req.getParameter("time").toString();
				String subject="Eaters online";
				String body=emailMsg+"Your order will be prepared in "+min+"minitues\n"
						+ "Thank you"; 
				senderService.sendEmail(toEmail, subject,body);
				return "redirect:/Bill/myOrders";
				}//return to a file , so everything will be ok
		 
		 //from shop reject order
		 @RequestMapping("/reject/{id}")
		    public String reject(@PathVariable(name = "id") int id) {
			 	Bill bill=service.get(id);
			 	bill.setPending(true);
			 	service.save(bill);
			 	//System.out.println(bill.getId()+" "+bill.isPending());
			 	String to=bill.getOrderedUser();
			 	String subject="Eaters online";
			 	String body="Welcome to " +bill.getShopusername()+"\nYour pre-order placed for "+bill.getFoodname()+ " in " +bill.getShopusername()+ " cannot be prepared, Sorry for the inconvenience caused.\n Thank you";
			 	senderService.sendEmail(to, subject,body);
			 	
		        return "redirect:/Bill/myOrders";
		    }
		 
		 @RequestMapping("/buy/{id}")
		    public String addBill(@PathVariable(name = "id") Long id,Model model) {
			 	Food food =foodService.get(id);
			    model.addAttribute("food",food);
			 	Shop shop=shopService.get(food.getShopusername());
			 	model.addAttribute("shop", shop);
		        model.addAttribute("bill", new Bill());
		        return "orderFood";
		    }

		    @RequestMapping(value = "/save", method = RequestMethod.POST)
		    public String saveBill(@ModelAttribute("bill") Bill bill,Food food,@AuthenticationPrincipal CustomerUserDetails loggedCustomer,HttpSession session) {
		    	
				
		    	Customer customer=customerService.get(loggedCustomer.getUsername());
		        long foodid=food.getId();//get the selected id of food
		        food =foodService.get(foodid);  // select and get all values of selected food
		        
		        bill.setFoodcode(food.getFoodcode());
		        bill.setFoodname(food.getFoodname());
		        bill.setShopusername(food.getShopusername());
		        bill.setOrderedUser(customer.getEmail());
		        
		        Shop shop=shopService.get(food.getShopusername());
//		        long no=repo.max();
//		        if(no==0) {
//		        	no=0000000001;
//		        }else {
//		        	no=no+1;
//		        }
//		        
		       // bill.setBillno(3);
		      
		        double total=food.calBill(bill.getQty());//get the calculated bill to variable  total passing the qty
		        bill.setTotal(total);
		        
		        //prepare the invoice
		        String invoice="***************************************\n   INVOICE   \n******************************************\n"
		        		+ shop.getShopname()+"                                    \n   Location:"+shop.getLocation()+"\n                 Phone:        "+shop.getPhone()+"                    \n"
		        				+ "\n\nYou pre-ordered\n------------------------------"
		        		+bill.getFoodname()+"\nQuantity               " +bill.getQty()+"\n                           "
		        				+ "Your bill    "+ bill.getTotal()+"\n\n"
		        						+ "You will receive an email from the\n"
		        						+ "restaurant with invoice and estimated time\n"
		        						+ "if the order is accepted\n"
		        						+ "reference id "+bill.getId();
		        		
		        
		        
		        
		      //check whether shop is online
		        Boolean st=shopRepo.onlinestatus(food.getShopusername());
		        if(st) {    
				        //check total with deposited amount
				        if(customer.checkDeposit(total)) {
				        	session.setAttribute("invoice",invoice);  
				        	service.save(bill);
				        	return "redirect:/";
				        }else {
				        	session.setAttribute("test",2);  
				        	return "redirect:/";
				        }
		        }else {
		        	session.setAttribute("test",1);  
		        	return "redirect:/";
		        }
		        
		        
		    }
		    
		    
		    @RequestMapping("/deleteSession")
		    public String deleteSess(HttpSession session) {
		    	session.setAttribute("test",0);
		    	session.setAttribute("invoice",null); 
		    	return "redirect:/";
		    }
		    
}
