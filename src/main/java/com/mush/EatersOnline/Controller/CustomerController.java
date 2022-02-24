package com.mush.EatersOnline.Controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mush.EatersOnline.Model.Customer;
import com.mush.EatersOnline.Repository.CustomerRepo;
import com.mush.EatersOnline.Repository.ShopRepo;
import com.mush.EatersOnline.Security.CustomerUserDetails;
import com.mush.EatersOnline.Security.CustomerUserDetails;
import com.mush.EatersOnline.Service.CustomerService;

@Controller
@RequestMapping("/Customer")
public class CustomerController {
	
	@Autowired
	  private CustomerService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomerRepo cusRepo;
	
	@Autowired
	private ShopRepo shopRepo;
	
	//To read all data to the page
	 @GetMapping("/")
	    public String viewCustomers(Model model,HttpSession session) {
	    	List<Customer> listCustomer = service.listAll();
	        model.addAttribute("listCustomer", listCustomer);
	        session.setAttribute("cus",cusRepo.getCus());
	        session.setAttribute("shop",shopRepo.getShop());
	        return "customer";
	    }
	 
	 @GetMapping("/signupCustomer")
	    public String addCustomer(Model model) {
	    	List<Customer> listCustomer = service.listAll();
	        model.addAttribute("listCustomer", listCustomer);
	        model.addAttribute("customer", new Customer());
	        return "signupCustomer";
	    }
	 
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,BindingResult result) {
	    	if (result.hasErrors()) {
			    return "signupCustomer";
			  }
	    	customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
	        service.save(customer);
	        return "redirect:/";
	    }

	    @RequestMapping(value = "/saveDeposit")
	    public String saveCustomer(Customer customer) {
	    	Double deposit=customer.getDeposit();
	    	customer=service.get(customer.getEmail());
	    	customer.setDeposit(deposit);
	        service.save(customer);
	        return "redirect:/Customer/";
	    }
	    
	    @RequestMapping("/edit/{email}")
	    public ModelAndView editCustomer(@PathVariable(name = "email") String email) {
	    	ModelAndView mav = new ModelAndView("adminEditCus");
	    	Customer customer = service.get(email);
	        mav.addObject("customer", customer);
	        return mav;
	    }
	    @RequestMapping("/delete/{id}")
	    public String deleteCustomer(@PathVariable(name = "id") int id) {
	        service.delete(id);
	        return "redirect:/Customer/";
	    }
	    
	   
	    @RequestMapping("/account")
	    public String viewAccount(@AuthenticationPrincipal CustomerUserDetails loggedCustomer,Model model) {
	    	Customer customer=service.get(loggedCustomer.getUsername());
	    	customer.setPassword("");
	        model.addAttribute("customer", customer);
	        return "editCustomerProfile";
	    }
	    
	 
	    @RequestMapping(value = "/account/update")
	    public String updateAccount(Customer customer,@AuthenticationPrincipal CustomerUserDetails loggedCustomer) {
	    	int phone=customer.getPhone();
	    	String pw=customer.getPassword();
	    	customer=service.get(loggedCustomer.getUsername());
	    	customer.setPhone(phone);
	    	if(pw!="") {
	    		customer.setPassword(bCryptPasswordEncoder.encode(pw));
	    	}
	    	service.save(customer);	
	    	return "redirect:/Customer/account";
	    }
	  	    
	    
//	  //My custome login...................	
//		@RequestMapping("/login")
//		public String showLoginPage() {
//			org.springframework.security.core.Authentication auth =SecurityContextHolder.getContext().getAuthentication();
//			if(auth==null  || auth instanceof AnonymousAuthenticationToken) {
//				return "/login";
//			}
//			return"redirect:/";
//		}
}
