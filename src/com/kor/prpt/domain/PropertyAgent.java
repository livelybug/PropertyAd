package com.kor.prpt.domain;

public class PropertyAgent extends User implements Cloneable{

	private String gender;
	private String licence;
	private int agentID;
	
	public PropertyAgent() {
		super();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public int getAgentID() {
		return agentID;
	}

	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}

	 public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	}
}
