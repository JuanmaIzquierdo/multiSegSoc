package objetosDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Clase para la consulta de si un email esta repetido en la bd

public class UserQuery extends Conexion{
	
	private PreparedStatement ps = null;
	private Connection con = getConexion();
	
	public boolean checkEmail(User user){
		String sql = "IF EXISTS (SELECT * from user where Email = '" + user.getEmail() + "')";
		try {
			ps = con.prepareStatement(sql);
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
