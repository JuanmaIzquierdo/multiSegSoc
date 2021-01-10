package Models;

public class UserLogin {
	
	private String username;
	private String password;
	
	public UserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}