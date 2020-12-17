package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import org.apache.commons.net.ftp.FTPFile;

import objetosDB.User;
import views.Utilities;

public class MenuController {
	
	Socket socket;
	DataOutputStream dataOS;
	DataInputStream dataIS;
	FtpController ftp;
	User user;
	
	public MenuController(Socket socket, DataOutputStream dataOS, DataInputStream dataIS) {
		this.socket = socket;
		this.dataOS = dataOS;
		this.dataIS = dataIS;
		getUserData();
		this.ftp = new FtpController(user.getName(), user.getPassword());
		connectFTP();
		loginFTP();
	}
	
	public void getUserData() {
		Message msg = new Message("0004");
		try {
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
			Utilities.showMessage("Fichero subido", false);
		}else {
			System.out.println("no");
		}
	} 

	public String[] getFilesNames() {
		FTPFile[] files = ftp.getCurrentDirectoryFiles();
		String[] filesNames = new String [files.length];
		for(int i = 0; i < files.length; i++) {
			if(!files[i].getName().equals(".") && !files[i].getName().equals("..")) {
				String name = files[i].getName();
				if(files[i].isDirectory()) {
					name = "(DIR) " + name;
				}
				filesNames[i] = name;
			}
		}
		return filesNames;
	}
	
	
}