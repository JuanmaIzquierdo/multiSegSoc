package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import Models.DataRequestResponse;

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
				
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
