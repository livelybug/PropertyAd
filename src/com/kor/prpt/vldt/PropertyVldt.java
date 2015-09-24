package com.kor.prpt.vldt;

import com.kor.prpt.domain.Property;
import com.kor.prpt.domain.PropertyAgent;
import com.kor.prpt.domain.User;

public class PropertyVldt {

	private String vldtErr;
	private PropertyAgent agent;
	private Property property;

	public PropertyVldt(Property property) {
		this.property = property;
	}

	public PropertyVldt(Property property, PropertyAgent agent) {
		this.property = property;
		this.agent = agent;
	}
	
	public String getVldtErr() {
		return vldtErr;
	}

	public void setVldtErr(String vldtErr) {
		this.vldtErr = vldtErr;
	}

	public boolean validateProperty() {

		if(agent == null){
			vldtErr = "agent error";
			return false;
		}

		if(property.getAddress() == null || property.getAddress().equals("")){
			vldtErr = "address error";
			return false;
		}
		
		if(property.getName() == null || property.getName().equals("")){
			vldtErr = "name error";
			return false;
		}

		if(property.getRent_sale() == null || property.getRent_sale().equals("")){
			vldtErr = "property type error";
			return false;
		}

		return true;
	}

	public boolean vldPrptSch() {

		if(property.getAddress() == null && property.getName() == null && property.getRent_sale() == null){
			vldtErr = "property address, property name, property type all empty!";
			return false;
		}
		
		return true;
	}

}
