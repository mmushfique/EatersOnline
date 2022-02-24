package com.mush.EatersOnline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mush.EatersOnline.Model.Customer;
import com.mush.EatersOnline.Repository.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepo repo;
	
	public List<Customer> listAll() {
        return  repo.findAll();
    }
     
    public void save(Customer customer) {
        repo.save(customer);
    }
   
    public Customer get(long id) {
        return repo.findById(id).get();
    }
    
    public Customer get(String email) {
        return repo.getUserByEmail(email);
    }
    
    public void delete(long id) {
        repo.deleteById(id);
    }
     
}
