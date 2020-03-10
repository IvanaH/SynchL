package rework;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {
	private String userName;
	private String Mobile;
	private Map<String, Object> vipRelated;
	
	public Map<String, Object> getUserInfo() {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("mobile",Mobile);
		userInfo.put("userName", userName);
		userInfo.put("vipInfo", vipRelated);
		return userInfo;		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public Map<String, Object> getVipRelated() {
		return vipRelated;
	}
	public void setVipRelated(Map<String, Object> vipRelated) {
		this.vipRelated = vipRelated;
	}
	

}
