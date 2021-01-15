package controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.apache.commons.net.ftp.FTPFile;
import Database.User;
import Models.DataRequestResponse;
import Models.FtpResponse;
import Models.Message;
import Models.MovementRequest;
import Models.RecieveEmailRequest;
import Models.SendEmailRequest;
import views.Utilities;

public class MenuController {
	
	Socket socket;
	ObjectOutputStream objectOS;
	public ObjectInputStream objectIS;
	FtpController ftp;
	User user;
	
	public MenuController(Socket socket, 
			ObjectOutputStream objectOS, ObjectInputStream objectIS) {
		this.socket = socket;
		this.objectOS = objectOS;
		this.objectIS = objectIS;
		getUserData();
		this.ftp = new FtpController(user.getName(), user.getPassword());
		connectFTP();
		loginFTP();
	}
	
	//Email
	
	public void sendEmail(SendEmailRequest emailRequest) {
		DataRequestResponse message = new DataRequestResponse();
		emailRequest.setFrom(user.getEmail());
		emailRequest.setPassword(user.getPassword());
		message.setAction("0006");
		message.addData(emailRequest);
		try {
			objectOS.writeObject(message);
		} catch (IOException e) {
			System.out.println("Error in sendEmail " + e.getMessage());
		}
	}

	public void changeStateOfRecievingEmails(boolean getAllEmails) {
		DataRequestResponse message = new DataRequestResponse();
		RecieveEmailRequest emailRequest = new RecieveEmailRequest(getAllEmails, true);
		message.setAction("0008");
		message.addData(emailRequest);
		try {
			objectOS.writeObject(message);
		} catch (IOException e) {
			System.out.println("Error during sending request to server " + e.getMessage());
		}	
	}

	public void flagAsAdded(Message email) {
		DataRequestResponse message = new DataRequestResponse();
		message.setAction("0009");
		message.addData(email);
		try {
			objectOS.writeObject(message);
		} catch (IOException e) {
			System.out.println("Error during sending request to server " + e.getMessage());
		}	
	}


	//FTP Connection
	
	public void getUserData() {
		DataRequestResponse message = new DataRequestResponse();
		message.setAction("0004");
		try {
			objectOS.writeObject(message);
			this.user = (User)((DataRequestResponse) objectIS.readObject()).getData().get(0);
		} catch (IOException | ClassNotFoundException e) {
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
	
	//FTP methods
	
	public void uploadFile(File file) {
		if(ftp.uploadFile(file.getAbsolutePath(), file.getName())) {
			java.util.Date date = new Date();	
			registerMovement("subida de fichero", date.toString());
			System.out.println("subido");
			Utilities.showMessage("Fichero subido", false);
		}else {
			System.out.println("no");
			Utilities.showMessage("Error al subir fichero", true);
		}
	} 
	
	public void downloadFile(String path, String localPath, String FileName) {
		if(ftp.downloadFile(path, localPath, FileName)) {
			java.util.Date date = new Date();		
			registerMovement("bajada de fichero", date.toString());
			System.out.println("descargado");
			Utilities.showMessage("Fichero descargado", false);
		}else {
			System.out.println("no");
			Utilities.showMessage("Error al descargar fichero", true);
		}
	}
	
	public void deleteFile(String path) {
		java.util.Date date = new Date();
		FtpResponse ftpResponse = ftp.deleteFile(path); 
		if(ftpResponse.getErrorId() == 0) {			
			registerMovement("borrado de fichero", date.toString());
			System.out.println("borrado");
			Utilities.showMessage("Fichero eliminado", false);
		}else if(ftpResponse.getErrorId() == 1){
			//si no se ha borrado porque es un directorio que no esta vacio le pregunta al usuario si quiere hacerlo y responde si, eliminara la carpetaa
			//y todo su contenido
			boolean userResponse = Utilities.askUserMessage(ftpResponse.getMessage());
			if(userResponse) {
				try {
					if(ftp.deleteDirectory(path, "")) {
						Utilities.showMessage("Fichero eliminado", false);
						registerMovement("borrado de fichero", date.toString());
					}
				} catch (IOException e) {
					Utilities.showMessage("Error durante eliminación", true);
				}
			}
		}
	}
	
	public void renameFile(String path, String newName) {
		if(ftp.renameFile(path, newName)) {
			java.util.Date date = new Date();		
			registerMovement("renombrado de fichero", date.toString());
			System.out.println("renombrado");
			Utilities.showMessage("Fichero renombrado", false);
		}else {
			System.out.println("no");
			Utilities.showMessage("Error al renombrar fichero", true);
		}
	}
	
	public void createDirectory(String path) {
        if(ftp.createDirectory(path)) {
            java.util.Date date = new Date();   
            registerMovement("Creacion de Directorio", date.toString());
            System.out.println("Creado");
            Utilities.showMessage("Directorio Creado", false);
        }else {
            System.out.println("no");
            Utilities.showMessage("Error al Crear Directorio", true);
        }
    }
	
	public void registerMovement(String movement, String date) {
		//metodo que recibe un el movimiento y la hora a la que se haya hecho y le manda un mensaje al servidor para que registre el movimiento
		//en la base de datos
		DataRequestResponse message = new DataRequestResponse();
		message.setAction("0005");
		MovementRequest movementData = new MovementRequest(movement, date);
		message.addData(movementData);
		try {
			objectOS.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createDirectoryTree(DefaultTreeModel model, String path, DefaultMutableTreeNode parent) {
		FTPFile[] files = ftp.getDirectoryFiles(path);
		
		for(int i = 0; i < files.length; i++) {
			if(!files[i].getName().equals(".") && !files[i].getName().equals("..")) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(files[i].getName());
				if(!files[i].isDirectory()) {
					model.insertNodeInto(node, parent, i);
				}else {
					model.insertNodeInto(node, parent, i);
					createDirectoryTree(model, path + files[i].getName()+"/", node);
				}
				}
		}
	}
	
	public String getHomeDirectory() {
		return ftp.getHomeDirectory();
	}

	public String getTreePath(TreePath selectionPath, int ignoreComponents) {
		String path = "/";
		for(int i = 1;i < selectionPath.getPath().length -  ignoreComponents; i++) {
			path +=  selectionPath.getPathComponent(i)+"/";
		}
		return path;
	}
	
}