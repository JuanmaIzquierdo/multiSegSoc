package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import objetosDB.User;

public class MenuController {
	
	Socket socket;
	DataOutputStream dataOS;
	DataInputStream dataIS;
	ObjectInputStream objectIS;
	FtpController ftp;
	User user;
	
	public MenuController(Socket socket, DataOutputStream dataOS, DataInputStream dataIS) {
		this.socket = socket;
		this.dataOS = dataOS;
		this.dataIS = dataIS;
		try {
			objectIS = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		getUserData();
		this.ftp = new FtpController(user.getName(), user.getPassword());
		connectFTP();
		loginFTP();
	}
	
	public void getUserData() {
		Message msg = new Message("0004");
		try {
			dataOS.writeUTF(msg.getMessage());
			this.user = (User) objectIS.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}

	public void connectFTP() {
		if(!ftp.connect()) {
			System.out.println("error de conexion ftp");
		}
	}
	
	public void loginFTP() {
		if(!ftp.loginFTP()) {
			System.out.println("error de inicio de sesion ftp");
		}
	}
	
	
	
}
