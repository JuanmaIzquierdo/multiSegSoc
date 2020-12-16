package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import objetosDB.User;

public class MenuController {
	
	Socket socket;
	DataOutputStream dataOS;
	DataInputStream dataIS;
	FtpController ftp;
	User user;
	
	public MenuController(Socket socket, DataOutputStream dataOS, DataInputStream dataIS) {
		System.out.println("a");
		this.socket = socket;
		this.dataOS = dataOS;
		this.dataIS = dataIS;
		System.out.println("e");
		getUserData();
		System.out.println("i");
		this.ftp = new FtpController(user.getName(), user.getPassword());
		connectFTP();
		loginFTP();
	}
	
	public void getUserData() {
		Message msg = new Message("0004");
		try {
			System.out.println("ee");
			System.out.println("u");
			dataOS.writeUTF(msg.getMessage());
			String txt;
			txt = dataIS.readUTF();
			String[] data = txt.split("\\*");
			this.user = new User(Integer.valueOf(data[0]),data[1], data[2],data[3],data[4],data[5]);
		} catch (IOException e) {
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
	
	public void uploadFile(File file) {
		if(ftp.uploadFile(file.getAbsolutePath(), file.getName())) {
			java.util.Date date = new Date();			
			Message msg = new Message("0005");
			msg.addValue("subida de fichero");
			msg.addValue(date.toString());
			System.out.println(msg.getMessage());
			try {
				dataOS.writeUTF(msg.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("subido");
		}else {
			System.out.println("no");
		}
	}
	
	
	 
}
