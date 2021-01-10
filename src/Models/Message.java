package Models;

import java.util.ArrayList;

public class Message {
	
	private String code;
	private Boolean error;
	private String errorMsg;
	private ArrayList<Object> data;
	
	public Message(String code) {
		this.code = code;
		this.data = new ArrayList<Object>();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ArrayList<Object> getData() {
		return data;
	}

	public void addData(Object data) {
		this.data.add(data);
	}
}