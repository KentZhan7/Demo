package com.demo.model;

public class ServerResponseModel {
	
	public ServerResponseModel(String msg) {
		this.responseMessage = msg;
	}
	
	private String responseMessage;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
