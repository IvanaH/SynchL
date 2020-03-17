package rework;

import java.time.LocalDateTime;

public class VipRelated {
	private String mobile;
	private Boolean isSvip;
	private LocalDateTime effectiveTime;
	private String unsubscribe_offer_id;
	private Boolean isPreferential;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getIsSvip() {
		return isSvip;
	}
	public void setIsSvip(Boolean isSvip) {
		this.isSvip = isSvip;
	}
	public LocalDateTime getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(LocalDateTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public String getUnsubscribe_offer_id() {
		return unsubscribe_offer_id;
	}
	public void setUnsubscribe_offer_id(String unsubscribe_offer_id) {
		this.unsubscribe_offer_id = unsubscribe_offer_id;
	}
	public Boolean getIsPreferential() {
		return isPreferential;
	}
	public void setIsPreferential(Boolean isPreferential) {
		this.isPreferential = isPreferential;
	}
	
	
}
