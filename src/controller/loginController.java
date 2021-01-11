package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Models.DataRequestResponse;
import Models.LoginRequest;
import views.Login;
import views.Menu;
import views.Utilities;

public class loginController {
	
	Socket socket;
	ObjectOutputStream objectOS;
	DataOutputStream dataOS;
	DataInputStream dataIS;
	
	public loginController(String serverIP, int port) {
		try {
			this.socket = new Socket(serverIP, port);
			 dataOS = new DataOutputStream(socket.getOutputStream());
			 dataIS = new DataInputStream(socket.getInputStream());
			 objectOS = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(String username, char[] cs) {
		String password = "";
		int result;
		
		for(int i = 0; i < cs.length; i++) {
			password += cs[i];
		}
		DataRequestResponse message = new DataRequestResponse();
		message.setAction("0001");
		LoginRequest loginData = new LoginRequest(username, password);
		message.addData(loginData);
		try {
			objectOS.writeObject(message);
//			dataOS.writeUTF(msg.getMessage());
			result = dataIS.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			result = 3;
		}
		
		switch(result) {
			case 0: 
				//siguiente ventana
				Login.hacerInvisible();
				MenuController menuController = new MenuController(this.socket, this.dataOS,
						this.dataIS, this.objectOS);
				Menu menu = new Menu(menuController);
				menu.setVisible(true);
				break;
			case 1: 
				Utilities.showMessage("Ya existe una sesión iniciada con esta cuenta", true);
				break;
			case 2: 
				Utilities.showMessage("Usuario o contraseña incorrecto", true);
				break;
			case 3: 
				Utilities.showMessage("Error de comunicación con el servidor", true);
		}
	}
}
