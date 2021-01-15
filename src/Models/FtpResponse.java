package Models;

public class FtpResponse {
	private int errorId;
	private String message;
	public FtpResponse(int errorId, String message) {
		super();
		this.errorId = errorId;
		this.message = message;
	}
	public FtpResponse() {
		super();
	}
	public int getErrorId() {
		return errorId;
	}
	public String getMessage() {
		return message;
	}
	
	
}
