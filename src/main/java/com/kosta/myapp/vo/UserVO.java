package com.kosta.myapp.vo;

public class UserVO {
	String userid;
	String userpass;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", userpass=" + userpass + "]";
	}
	
}
