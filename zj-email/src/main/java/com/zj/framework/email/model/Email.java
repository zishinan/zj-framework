package com.zj.framework.email.model;

public class Email {
	private String headName;
	private String sendHtml;
	private String email;
	public Email(String headName,String sendHtml,String email) {
		this.headName = headName;
		this.sendHtml = sendHtml;
		this.email = email;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getSendHtml() {
		return sendHtml;
	}
	public void setSendHtml(String sendHtml) {
		this.sendHtml = sendHtml;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
