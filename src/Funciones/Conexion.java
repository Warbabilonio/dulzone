package Funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static String url = "//localhost/dulzone";
	private static String user = "root";
	private static String pass = "";
	private static Connection conn = null;
	
	public static Connection conectar() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:"+url,user,pass);
			System.out.println("Conexion existosa!");//borrar
			
			
			
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage() +" - Error ClassNotFoundException Conexion.conectar");
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage() +" - Error SQLEXception Conexion.conectar");
			
			
		}
		
		return conn;
	}
}
	