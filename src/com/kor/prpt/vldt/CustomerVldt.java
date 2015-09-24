package com.kor.prpt.vldt;

import com.kor.prpt.domain.Customer;

public class CustomerVldt extends UserVldt {

	Customer customer;

	public CustomerVldt(Customer customer) {
		super(customer);
		this.customer = customer;
	}

}
