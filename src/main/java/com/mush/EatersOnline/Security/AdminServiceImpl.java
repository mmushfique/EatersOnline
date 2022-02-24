package com.mush.EatersOnline.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mush.EatersOnline.Model.Admin;
import com.mush.EatersOnline.Repository.AdminRepo;

public class AdminServiceImpl implements UserDetailsService {
	
	@Autowired
	private AdminRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin=repo.getAdminByUsername(username);
		if(admin==null) {
			throw new UsernameNotFoundException("Could not find user");		
		}
		return new AdminDetails(admin);
	}

}
