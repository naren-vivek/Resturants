package com.ordermgmt.model.bean;

import java.io.Serializable;

public class StringResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String response;

	public StringResponse() {
		super();
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
