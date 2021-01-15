package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import views.Menu;
import views.Splash;

public class ProgramController {
	public ProgramController() {
	}
	
	public static void executeProgram(Socket socket, ObjectOutputStream objectOS, ObjectInputStream objectIS) {
		MenuController menuController = new MenuController(socket, objectOS, objectIS);
		Runnable ejecutable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Splash splash = new Splash();
				splash.setVisible(true);
				try {
					Thread.sleep(4 * 1000);
					Menu menu = new Menu(menuController);
					menu.setVisible(true);
					ReadMessagesThread thread = new ReadMessagesThread(menuController.objectIS, menu);
					thread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				splash.dispose();

			}
		};

		Thread tarea = new Thread(ejecutable);
		tarea.start();
	}
}
