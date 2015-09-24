package com.kor.prpt.vldt;

import com.kor.prpt.domain.User;

public abstract class UserVldt {

	private User user;
	private String vldtErr;
	
	public UserVldt(User user) {
		this.user = user;
	}
	
	public String getVldtErr() {
		return vldtErr;
	}

	public void setVldtErr(String vldtErr) {
		this.vldtErr = vldtErr;
	}

	public boolean validateUser() {
		
		if(user.getEmail() == null || user.getEmail().equals("")){
			vldtErr="email error";
			return false;
		}
		
		if(user.getMobile() == null || user.getMobile().equals("")){
			vldtErr="mobile error";
			return false;
		}
		
		if(user.getPassword() == null || user.getPassword().equals("")){
			vldtErr="password error";
			return false;
		}
		
		if(user.getUsername() == null || user.getUsername().equals("")){
			vldtErr="username error";
			return false;
		}
		
		return true;
	}

}
