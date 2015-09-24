package com.kor.prpt.vldt;

import com.kor.prpt.domain.PropertyAgent;

public class PropertyAgentVldt extends UserVldt {

	private PropertyAgent agent;
	
	public PropertyAgentVldt(PropertyAgent agent) {
		super(agent);
		this.agent = agent;
	}
	
	public boolean validateUser() {
		
		if(super.validateUser() == false)
			return false;
		
		if(agent.getAddress() == null || agent.getAddress().equals("")){
			setVldtErr("address error");
			return false;
		}
		
		if(agent.getLicence() == null || agent.getLicence().equals("")){
			setVldtErr("licence error");
			return false;
		}

		if(agent.getGender() == null || agent.getGender().equals("")){
			setVldtErr("gender error");
			return false;
		}

		return true;
	}
}
