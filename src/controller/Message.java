package controller;

import java.util.ArrayList;

public class Message {
	
	String code;
	ArrayList<String> values;
	
	public Message(String code) {
		this.code = code;
		this.values = new ArrayList<String>();
	}
	
	public void addValue(String value) {
		this.values.add(value);
	}
	
	public String getMessage() {
		String msg = this.code;
		for(int i = 0;i < this.values.size(); i++) {
			msg += "*" + this.values.get(i);
		}
		System.out.println(msg);
		return msg;
	}

}
