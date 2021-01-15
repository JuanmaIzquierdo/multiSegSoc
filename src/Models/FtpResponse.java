package Models;
/*
 * Clase DataRequestResponse
 * 
 * Modelo que se utiliza para identificar un error que ha ocurrido en el ftp
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
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
