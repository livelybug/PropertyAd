package com.kor.prpt.domain;

public class Customer extends User{

	private int customerID;
	
	public Customer() {
		super();
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

}
