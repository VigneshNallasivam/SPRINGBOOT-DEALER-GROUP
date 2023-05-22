package com.java.basics.response;

public class Message 
{
	private String message;
	private Object object;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	@Override
	public String toString() {
		return "Message [message=" + message + ", object=" + object + "]";
	}
	public Message(String message, Object object) {
		this.message = message;
		this.object = object;
	}
	public Message() {
		super();
	}
	
	
}
