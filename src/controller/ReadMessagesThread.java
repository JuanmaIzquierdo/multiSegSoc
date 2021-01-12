package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import Models.DataRequestResponse;
import views.Utilities;

public class ReadMessagesThread extends Thread{
	
	ObjectInputStream objectIS;
	
	public ReadMessagesThread(ObjectInputStream objectIS) {
		this.objectIS = objectIS;
	}
	
	public void run() {
		DataRequestResponse message;
		while(true) {
			try {
				message = (DataRequestResponse) objectIS.readObject();
				switch(message.getAction()) {
				case "1111":
					System.out.println(message.getData().size());
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
