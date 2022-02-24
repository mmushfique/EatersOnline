package com.mush.EatersOnline.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mush.EatersOnline.Model.Shop;
import com.mush.EatersOnline.Repository.ShopRepo;

public class ShopUserServiceImpl implements UserDetailsService {

	@Autowired
	private ShopRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Shop shop=repo.getShopByShopUsername(username);
		if(shop==null) {
			throw new UsernameNotFoundException("Could not find user");		
		}
		return new ShopUserDetails(shop);
	}

}
