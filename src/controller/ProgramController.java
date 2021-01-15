package controller;
/*
 * Clase ConnectionThread
 * 
 * Metodo que gestiona las peticiones de un Cliente determinado
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import views.Menu;
import views.Splash;

public class ProgramController {
	public ProgramController() {
	}
	
	public static void executeProgram(Socket socket, ObjectOutputStream objectOS, ObjectInputStream objectIS) {
		/*
		 * mostrar ventana de carga y iniciar menucontroller que establecera la conexion con el servidor ftp.
		 * despues se mostrara durante 3 segundos mas la pantalla de carga y se iniciara la aplicacion
		 */
		Runnable ejecutable = new Runnable() {
			@Override
			public void run() {
				Splash splash = new Splash();
				splash.setVisible(true);
				try {
					MenuController menuController = new MenuController(socket, objectOS, objectIS);
					Thread.sleep(3 * 1000);
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
