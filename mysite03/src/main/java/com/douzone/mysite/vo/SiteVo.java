package com.douzone.mysite.vo;

public class SiteVo {
	private Long no;
	private String welcomeMessage;
	private String profileURL;
	private String description;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getProfileURL() {
		return profileURL;
	}
	public void setProfileURL(String profileURL) {
		this.profileURL = profileURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", welcomeMessage=" + welcomeMessage + ", profileURL=" + profileURL
				+ ", description=" + description + "]";
	}
}
