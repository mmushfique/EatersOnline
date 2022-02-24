package com.mush.EatersOnline.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mush.EatersOnline.Model.Customer;
import com.mush.EatersOnline.Repository.CustomerRepo;

public class CustomerUserServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer=repo.getUserByEmail(username);
		if(customer==null) {
			throw new UsernameNotFoundException("Could not find user");		
		}
		return new CustomerUserDetails(customer);
	}

}
