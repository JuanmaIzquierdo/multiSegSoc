package objetosDB;

public class Movements {
	
	int user_id;
	String operation;
	String date;
	
	public Movements(int user_id, String operation, String date){
		this.user_id = user_id;
		this.operation = operation;
		this.date = date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
