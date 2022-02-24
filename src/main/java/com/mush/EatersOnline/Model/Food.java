 package com.mush.EatersOnline.Model;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mush.EatersOnline.Annotation.UniqueFood;

@Entity
public class Food {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	//@NotEmpty(message="Cannot be empty!")
	//@UniqueFood(message="You have a food with this code")
	private String foodcode;//specific code generatedby system depending on shop
	//@NotEmpty(message="Cannot be empty!")
	private String foodname;
	private String shopusername;
	//@NotNull(message="Enter a valid price!")
	private double price;
	private String image;
	
	public Food() {
		
	}


	public Food(long id, String foodcode, String foodname, String shopusername, double price,
			String image) {
		super();
		this.id = id;
		this.foodcode = foodcode;
		this.foodname = foodname;
		this.shopusername = shopusername;
		this.price = price;
		this.image = image;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFoodcode() {
		return foodcode;
	}

	public void setFoodcode(String foodcode) {
		this.foodcode = foodcode;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	
	
	public String getShopusername() {
		return shopusername;
	}

	public void setShopusername(String shopusername) {
		this.shopusername = shopusername;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	@Transient
	public String getImagePath() {
		if(image == null || id == 0) return null;
		
		return "/food-images/" + id + "/" + image;
	}

	public double calBill(int qty) {
		double total=this.price*qty;
		return total;
	}
	
}
