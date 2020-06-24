package com.aditya.authentication.dto;

public class Greeting {

	String message;

	public String getGreeting() {
		return message;
	}

	public void setGreeting(String greeting) {
		this.message = greeting;
	}

	public Greeting(String greeting) {
		super();
		this.message = greeting;
	}

	@Override
	public String toString() {
		return "Greeting [greeting=" + message + "]";
	}

}
