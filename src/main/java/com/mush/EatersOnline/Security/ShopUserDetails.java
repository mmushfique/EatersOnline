package com.mush.EatersOnline.Security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mush.EatersOnline.Model.Shop;

public class ShopUserDetails implements UserDetails {

	private Shop shop;
	
	public ShopUserDetails(Shop shop) {
		this.shop=shop;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority =new SimpleGrantedAuthority(shop.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return shop.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return shop.getShopusername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
//		if(shop.isStatus()) {
//			return true;
//		}
//		else {
//			return false;
//		}
		return true;
	}

}
