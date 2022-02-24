package com.mush.EatersOnline.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mush.EatersOnline.Repository.CustomerRepo;
import com.mush.EatersOnline.Repository.FoodRepo;

public class UniqueFoodValidator implements ConstraintValidator<UniqueFood, String>{

	@Autowired
	private FoodRepo repo;
	
	@Override
	public void initialize(UniqueFood constraintAnnotation) {	
	}
	
	@Override
	public boolean isValid(String code, ConstraintValidatorContext context) {
		if(repo==null) {
			return true;
		}
		
		return repo.getFoodByFoodCode(code)==null;
	}
}
