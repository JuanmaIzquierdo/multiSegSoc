package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Models.DataRequestResponse;
import Models.Message;
import views.Menu;
import views.Utilities;

public class ReadMessagesThread extends Thread{
	
	private ObjectInputStream objectIS;
	private Menu menu;
	private ArrayList<Message> emails = new ArrayList<Message>();
	
	public ReadMessagesThread(ObjectInputStream objectIS, Menu menu) {
		this.objectIS = objectIS;
		this.menu = menu;
		
		menu.getMntmRecibirCorreo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.menuReciboDeCorreo(emails);
			}
		});
	}
	
	public void run() {
		DataRequestResponse message;
		while(true) {
			try {
//				message = (DataRequestResponse) objectIS.readObject();
				Object obj = objectIS.readObject();
				if (obj instanceof DataRequestResponse) {
					message = (DataRequestResponse) obj;
				} else {
					break;
				}
				switch(message.getAction()) {
				case "1111":
					if(!message.getError().equalsIgnoreCase("")) {
						Utilities.showMessage(message.getErrorMessage(), true);
					}else {
						System.out.println(message.getData().size());
						ArrayList<Message> emailsResponse = (ArrayList<Models.Message>) message.getData().get(0);
							emails = emailsResponse;

							if(menu.getEmailPanel() != null) {
								if(menu.getEmailPanel().isVisible()) {
									menu.updateEmailIndex(emails);
								}
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
