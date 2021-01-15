package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import Models.FtpResponse;

public class FtpController {
	
	String username;
	String password;
	FTPClient client;
	String homeDirectory;
	
	public FtpController(String username, String password) {
		this.username = username;
		this.password = password;
		this.client  = new FTPClient();
	}
	
	public boolean connect() {
		try {
			client.connect("localhost");
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
                	this.homeDirectory = client.printWorkingDirectory();
                	System.out.println(homeDirectory);
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

	public boolean downloadFile(String path, String localDirectory, String fileName) {
		BufferedOutputStream out;
		try {
			out = new BufferedOutputStream(new FileOutputStream(
					localDirectory + "\\" + fileName));
			return client.retrieveFile(path, out);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public FtpResponse deleteFile(String path) {
		try {
			if(!client.deleteFile(path)) {
				if(!client.removeDirectory(path)) {
					return new FtpResponse(1, "¿Directorio no esta vacio, quieres eliminarlo?");
				}
			}
			return new FtpResponse(0, "");
		} catch (IOException e) {
			e.printStackTrace();
			return new FtpResponse(2, "Error durante la eliminacion (FTP)");
		}
	}
	
		public boolean deleteDirectory(String parentDir, String currentDir) throws IOException {
			boolean result = false;
			String dirToList = parentDir;

	        if (!currentDir.equals("")) {

	            dirToList += "/" + currentDir;

	        }

	 

	        FTPFile[] subFiles = client.listFiles(dirToList);

	 

	        if (subFiles != null && subFiles.length > 0) {

	            for (FTPFile aFile : subFiles) {

	                String currentFileName = aFile.getName();

	                if (currentFileName.equals(".") || currentFileName.equals("..")) {

	                    // skip parent directory and the directory itself

	                    continue;

	                }

	                String filePath = parentDir + "/" + currentDir + "/"

	                        + currentFileName;

	                if (currentDir.equals("")) {

	                    filePath = parentDir + "/" + currentFileName;

	                }

	 

	                if (aFile.isDirectory()) {

	                    // remove the sub directory

	                    deleteDirectory(dirToList, currentFileName);

	                } else {

	                    // delete the file

	                    boolean deleted = client.deleteFile(filePath);

	                    if (deleted) {

	                        System.out.println("DELETED the file: " + filePath);

	                    } else {

	                        System.out.println("CANNOT delete the file: "

	                                + filePath);
	                    }

	                }

	            }


	            // finally, remove the directory itself

	            boolean removed = client.removeDirectory(dirToList);
	            result = removed;
	        }
	   	 return result;
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
	
	public boolean createDirectory(String path) {
        try {
            client.makeDirectory(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public String getHomeDirectory() {
		return this.homeDirectory;
	}
	
	public FTPFile[] getDirectoryFiles(String folder) {
		try {
			String directory = this.homeDirectory+folder;
			client.changeWorkingDirectory(directory);
			FTPFile[] files = client.listFiles();
			return files;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}