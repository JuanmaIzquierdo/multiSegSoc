package objetosDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase para establecer la conexion con la base de datos

public class Conexion {
	
	private final String bd = "multipresencial";
	private final String user = "root";
	private final String password = "";
	private final String url = "jdbc:mysql://localhost/multipresencial";
	private Connection con = null;
	
	public Connection getConexion(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}

}
