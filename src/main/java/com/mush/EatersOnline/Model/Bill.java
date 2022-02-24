package com.mush.EatersOnline.Model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private long billno;
	private String ordereduser;
	private String shopusername;
	private String foodcode;
	private String foodname;
	private Date date;
	private int qty;
	private double total;
	private boolean pending;

	
	public Bill() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBillno() {
		return billno;
	}

	public void setBillno(long billno) {
		this.billno = billno;
	}

	public String getOrderedUser() {
		return ordereduser;
	}

	public void setOrderedUser(String orderedUser) {
		this.ordereduser = orderedUser;
	}

	public String getShopusername() {
		return shopusername;
	}

	public void setShopusername(String shopusername) {
		this.shopusername = shopusername;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getOrdereduser() {
		return ordereduser;
	}

	public void setOrdereduser(String ordereduser) {
		this.ordereduser = ordereduser;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}
	
	
	
}
