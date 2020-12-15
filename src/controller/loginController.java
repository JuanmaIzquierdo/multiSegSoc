package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import views.Login;

public class loginController {
	
	Socket socket;
	DataOutputStream dataOS;
	DataInputStream dataIS;
	
	public loginController(String serverIP, int port) {
		try {
			this.socket = new Socket(serverIP, port);
			 dataOS = new DataOutputStream(socket.getOutputStream());
			 dataIS = new DataInputStream(socket.getInputStream());
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
		Message msg = new Message("0001");
		msg.addValue(username);
		msg.addValue(password);
		try {
			dataOS.writeUTF(msg.getMessage());
			result = dataIS.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			result = 3;
		}
		
		switch(result) {
			case 0: 
				Login.mostrarMensaje("Sesión iniciada", false);
				//siguiente ventana
				break;
			case 1: 
				Login.mostrarMensaje("Contraseña incorrecta", true);
				break;
			case 2: 
				Login.mostrarMensaje("Usuario incorrecto", true);
				break;
			case 3: 
				Login.mostrarMensaje("Error de comunicación con el servidor", true);
			
		}
	}

}
