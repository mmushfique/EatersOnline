package com.mush.EatersOnline.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mush.EatersOnline.Repository.CustomerRepo;
import com.mush.EatersOnline.Repository.ShopRepo;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueShopUsername, String>{

	@Autowired
	private ShopRepo repo;
	
	@Override
	public void initialize(UniqueShopUsername constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(repo==null) {
			return true;
		}
		return repo.getShopByShopUsername(username)==null;
	}

}
