package objetosDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserCreate extends Conexion {
	
	public boolean registrar(User user){
		
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		Sgtring sql = "INSERT INTO user (";
		return false;
		
	}

}
