package controller;
/*
 * Clase FtpController
 * 
 * Clase que gestiona el FTP Server
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
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
	
	private String username;
	private String password;
	private FTPClient client;
	private String homeDirectory;
	
	/**
     * Constructor con 4 parametros
     * @param username - type String - nombre del usuario
     * @param password - type String - contraseña del empleado
     */
	public FtpController(String username, String password) {
		this.username = username;
		this.password = password;
		this.client  = new FTPClient();
	}
	
	/**
     * Cenecion al servidor
     * @return
	     *  true: se ha conectado
	     *  false: error durante la conecion<
     */
	public boolean connect() {
		try {
			client.connect("192.168.1.68");
			return true;
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
     * Login in servidor
     * @return
	     *  true: se ha hecho login correctamente
	     *  false: error durante el proceso
     */
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

	/**
     * Subir el archivo
     * @param path - type String - la ruta del archivi
     * @param name - type String - el nombre del fichero
     * @return
	     *  true: se ha subido correctamente
	     *  false: error durante el proceso
     */
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

	/**
     * Bajar in servidor
     * @param path - type String - la ruta del archivi
     * @param fileName - type String - el nombre del fichero
     * @param localDirectory - type String - el nombre del directorio
     * @return
	     *  true: se ha hecho login correctamente
	     *  false: error durante el proceso
     */
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
	
	/**
     * Borar archivo/directorio del servidor
     * @param path - type String - la ruta del archivo/directorio
     * @return FtpResponse - respuesta
     */
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
	
	 /* Borar directorio del servidor (si los directorios no estan vacios)
     * @param path - type String - la ruta del directorio
     * @param currentDir - type String - se uteliza cuando se borra recursivamente para saber en que directorio esta
     * @return
	     *  true: se ha borrado correctamente
	     *  false: error durante el proceso
     */
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
	
		/* Renombrar archivo/directorio del servidor
	     * @param path - type String - la ruta del directorio
	     * @param newName - type String - nuevo nombre
     * @return
	     *  true: se ha renombrado correctamente
	     *  false: error durante el proceso
	     */
	public boolean renameFile(String path, String newName) {
		try {
			client.rename(path, newName);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/* crear directorio del servidor
     * @param path - type String - la ruta del directorio
     * @return
	     *  true: se ha creado correctamente
	     *  false: error durante el proceso
     */
	public boolean createDirectory(String path) {
        try {
            client.makeDirectory(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	/* Coge el directorio raiz (del usuario)
     * @return String - ruta del directorio
     */
	public String getHomeDirectory() {
		return this.homeDirectory;
	}
	
	/* Coge los archivos del directorio
     * @param folder - type String - la ruta del directorio
     * @return FTPFile[] - archivos
     */
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