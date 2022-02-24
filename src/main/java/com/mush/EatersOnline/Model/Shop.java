package com.mush.EatersOnline.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mush.EatersOnline.Annotation.UniqueShopUsername;
@Entity
public class Shop {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@UniqueShopUsername(message="Username already take, please choose another")
	private String shopusername;//unique name given
	@NotEmpty(message="Cannot be empty!")
	private String shopname;
	@NotEmpty(message="Cannot be empty!")
	@Email(message="Enter a valid email")
	private String email;
	@NotNull
	private int phone;
	@NotEmpty(message="Cannot be empty!")
	private String location;
	@NotEmpty(message="Cannot be empty!")
	@Size(min = 8,message="Must have a minimum of 8 characters")
	private String password;
	private double deposit;//deposit made for account creation
	private boolean onlinestatus;//whether he shop is open or closed
	private boolean status;//status of the account 
	
	
	private String role="SHOP";
	
	public Shop() {
		
	}
	
	

	public Shop(long id, String shopusername, String shopname,String email, int phone, String location, String password,
			double deposit, boolean onlinestatus, boolean status, String role) {
		super();
		this.id = id;
		this.shopusername = shopusername;
		this.shopname = shopname;
		this.email = email;
		this.phone = phone;
		this.location = location;
		this.password = password;
		this.deposit = deposit;
		this.onlinestatus = onlinestatus;
		this.status = status;
		this.role = role;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getShopusername() {
		return shopusername;
	}



	public void setShopusername(String shopusername) {
		this.shopusername = shopusername;
	}



	public String getShopname() {
		return shopname;
	}



	public void setShopname(String shopname) {
		this.shopname = shopname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getPhone() {
		return phone;
	}



	public void setPhone(int phone) {
		this.phone = phone;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public double getDeposit() {
		return deposit;
	}



	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}



	public boolean isOnlinestatus() {
		return onlinestatus;
	}



	public void setOnlinestatus(boolean onlinestatus) {
		this.onlinestatus = onlinestatus;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	
}
