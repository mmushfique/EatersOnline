package com.mush.EatersOnline.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mush.EatersOnline.Annotation.UniqueCustomer;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message="Cannot be empty!")
	private String fname;
	@NotEmpty(message="Cannot be empty!")
	private String lname;
	@NotEmpty(message="Cannot be empty!")
	private String address;
	@NotEmpty(message="Cannot be empty!")
	@Email(message="Enter a valid email")
	@UniqueCustomer(message="You already have an account with this email")
	private String email;//unique
	@NotNull
	private int phone;
	@NotEmpty(message="Cannot be empty!")
	@Size(min = 8,message="Must have a minimum of 8 characters")
	private String password;
	private double deposit;//deposit made by the user to activate the account
	private boolean status;//account status
	
	private String role="CUSTOMER"; 
	
	public Customer() {
		
	}

	public Customer(long id, String fname, String lname, String address, String email, int phone, String password,
			double deposit, boolean status,String role) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.deposit = deposit;
		this.status = status;
		this.role =  role;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public boolean checkDeposit(double tot) {
		if(tot<this.deposit) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
