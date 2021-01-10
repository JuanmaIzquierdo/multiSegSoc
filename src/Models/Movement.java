package Models;

public class Movement {
	
	String movement;
	String date;

	public Movement(String movement, String date) {
		this.movement = movement;
		this.date = date;
	}
	
	public String getMovement() {
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}