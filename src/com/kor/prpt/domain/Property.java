package com.kor.prpt.domain;

public class Property {

	private String name;
	private String address;
	private String rent_sale;
	private String image;
	private String comment;
	private PropertyAgent pAgt;
	
	public Property() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRent_sale() {
		return rent_sale;
	}

	public void setRent_sale(String rent_sale) {
		this.rent_sale = rent_sale;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PropertyAgent getpAgt() {
		return pAgt;
	}

	public void setpAgt(PropertyAgent pAgt) {
		this.pAgt = pAgt;
	}
	
}
