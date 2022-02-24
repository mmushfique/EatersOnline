package com.mush.EatersOnline.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mush.EatersOnline.Model.Customer;
import com.mush.EatersOnline.Repository.CustomerRepo;

public class UniqueCustomerValidator implements ConstraintValidator<UniqueCustomer, String>{

	@Autowired
	private CustomerRepo repo;
	
	@Override
	public void initialize(UniqueCustomer constraintAnnotation) {	
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(repo==null) {
			return true;
		}
		
		return repo.getUserByEmail(email)==null;
	}
}
