package objetosDB;

public class Movements {
	
	int User_id;
	String Operation;
	String Date;
	
	public Movements(int User_id, String Operation, String Date){
		this.User_id = User_id;
		this.Operation = Operation;
		this.Date = Date;
	}

	public int getUser_id() {
		return User_id;
	}

	public void setUser_id(int user_id) {
		this.User_id = user_id;
	}

	public String getOperation() {
		return Operation;
	}

	public void setOperation(String operation) {
		this.Operation = operation;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		this.Date = date;
	}

}
