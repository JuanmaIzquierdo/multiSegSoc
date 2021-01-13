package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Models.DataRequestResponse;
import Models.LoginRequest;
import Models.RecieveEmailRequest;
import views.Login;
import views.Menu;
import views.Splash;
import views.Utilities;

public class loginController {
	
	Socket socket;
	ObjectOutputStream objectOS;
	ObjectInputStream objectIS;
	DataOutputStream dataOS;
	DataInputStream dataIS;
	
	public loginController(String serverIP, int port) {
		try {
			this.socket = new Socket(serverIP, port);
			 dataOS = new DataOutputStream(socket.getOutputStream());
			 dataIS = new DataInputStream(socket.getInputStream());
			 objectOS = new ObjectOutputStream(socket.getOutputStream());
			 objectIS = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(String username, char[] cs) {
		String password = "";
		DataRequestResponse result;
		
		for(int i = 0; i < cs.length; i++) {
			password += cs[i];
		}
		DataRequestResponse message = new DataRequestResponse();
		message.setAction("0001");
		LoginRequest loginData = new LoginRequest(username, password);
		message.addData(loginData);
		try {
			objectOS.writeObject(message);
			result = (DataRequestResponse) objectIS.readObject();
			switch(result.getError()) {
			case "": 
				//siguiente ventana
				System.out.println("yes");
				Login.hacerInvisible();
				//Splash.showSplash(4);
				MenuController menuController = new MenuController(this.socket, this.dataOS,
						this.dataIS, this.objectOS, this.objectIS);
				Menu menu = new Menu(menuController);
				menu.setVisible(true);

				ReadMessagesThread thread = new ReadMessagesThread(objectIS, menu);
				thread.start();
				reciveMessages(true);
				break;
			case "Error": 
				Utilities.showMessage(result.getErrorMessage(), true);
				break;
//			case 2: 
//				Utilities.showMessage("Usuario o contraseña incorrecto", true);
//				break;
//			case 3: 
//				Utilities.showMessage("Error de comunicación con el servidor", true);
		}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			Utilities.showMessage("Error de comunicación con el servidor", true);
			//result = 3;
		}
	}
	
	private void reciveMessages(boolean getAllEmail) {
		DataRequestResponse message = new DataRequestResponse();
		message.setAction("0007");
		RecieveEmailRequest emailRequest = new RecieveEmailRequest(getAllEmail, true);
		message.addData(emailRequest);
		try {
			objectOS.writeObject(message);
		} catch (IOException e) {
			System.out.println("Error in reciveMessages " + e.getMessage());
		}
	}
}
