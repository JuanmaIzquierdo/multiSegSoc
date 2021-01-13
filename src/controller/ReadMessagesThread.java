package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Models.DataRequestResponse;
import views.Menu;
import views.Utilities;

public class ReadMessagesThread extends Thread{
	
	private ObjectInputStream objectIS;
	private Menu menu;
	
	public ReadMessagesThread(ObjectInputStream objectIS, Menu menu) {
		this.objectIS = objectIS;
		this.menu = menu;
	}
	
	public void run() {
		DataRequestResponse message;
		while(true) {
			try {
				message = (DataRequestResponse) objectIS.readObject();
				switch(message.getAction()) {
				case "1111":
					System.out.println(message.getData().size());
					if(menu.getEmailPanel() != null) {
						if(menu.getEmailPanel().isVisible()) {
							menu.menuReciboDeCorreo((ArrayList<Models.Message>)message.getData().get(0));
						}
					}
					break;
				case "0006":
					if(!message.getError().equalsIgnoreCase("")) {
						Utilities.showMessage(message.getErrorMessage(), true);
					}else {
						System.out.println("enviado");
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
