package com.douzone.mysite.vo;

public class SiteVo {
	private String title;
	private String welcomeMessage;
	private String profileUrl;
	private String description;
	public String getTitle() {
		return title;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SiteVo [title=" + title + ", welcomeMessage=" + welcomeMessage + ", profileUrl=" + profileUrl
				+ ", description=" + description + "]";
	}

	
}
