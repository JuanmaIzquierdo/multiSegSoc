package controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpController {
	
	String username;
	String password;
	FTPClient client;
	
	public FtpController(String username, String password) {
		this.username = username;
		this.password = password;
		this.client  = new FTPClient();
	}
	
	public boolean connect() {
		try {
			client.connect("127.0.0.1");
			return true;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean loginFTP() {
		 try {
			if(client.login(username, password)){
                // Entrando a modo pasivo
                client.enterLocalPassiveMode();
                // Activar recibir/enviar cualquier tipo de archivo
                client.setFileType(FTP.BINARY_FILE_TYPE);

                // Obtener respuesta del servidor y acceder.
                int respuesta = client.getReplyCode();
                if (FTPReply.isPositiveCompletion(respuesta) == true) {
                	System.out.println("Sesion ftp iniciada");
                    return true;
                }else{
                    return false;
                }
            }else{
            	System.out.println("no");
                return false;
            }
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean uploadFile(String path, String name) {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
			client.storeFile(name, in);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteFile(String path) {
		try {
			client.deleteFile(path);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean renameFile(String path, String newName) {
		try {
			client.rename(path, newName);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public FTPFile[] getCurrentDirectoryFiles() {
		try {
			FTPFile[] files = client.listFiles();
			return files;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}