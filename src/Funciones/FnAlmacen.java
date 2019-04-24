package Funciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Datos.Almacen;
import Datos.Producto;


public class FnAlmacen {
	
	private static Connection conn = null;
	private static String sql = "";
	private static String columna = "";
	private static String fila = "";
	private static String tabla = "almacen";
	private static PreparedStatement pst = null;

	
	public static boolean regEntrada(Almacen a) {
	
		conn = Conexion.conectar();
		sql = "insert into "
				+ tabla
				+ "(producto,stock,fecha_entrada,fecha_modificacion,descripcion) values(?,?,?,?,?);";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, a.getProducto().getId());
			pst.setInt(2, a.getStock());
			pst.setDate(3, a.getFechaEntrada());
			pst.setDate(4, a.getFechaSalida());
			pst.setString(5, a.getDescripcion());
			
			int n = pst.executeUpdate();
			
			if( n!=0 )
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			
			if(e.getMessage().contains("a foreign key constraint fails"))
				JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO: ESE PROVEEDOR NO ESTA REGISTRADO! - CodigoError: "+ e.getErrorCode() ,"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			if(e.getMessage().contains("Duplicate entry"))
				JOptionPane.showMessageDialog(null, "ESA ENTRADA YA ESTÁ REGISTRADA","ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			
			JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO (Funciones.RegAlmacen) - errorCode: " +e.getErrorCode() ,"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			//e.printStackTrace();
			return false;
			
		}
		
		
		
	}
	
	public static ArrayList<Almacen> srcAlmacen(Almacen a, String col) {
		
		conn = Conexion.conectar();
		columna = col;
				
		ArrayList<Almacen> encontrados = new ArrayList<Almacen>();
		Almacen aux = null;
		Producto producto = null;
					
		sql = "select * from "
				+ tabla
				+ " where "
				+ columna
				+ "=?;";
		
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			
			switch(columna) {
				case "producto":
					pst.setString(1, a.getProducto().getId());
					break;
				case "stock":
					pst.setInt(1, a.getStock());
					break;
				case "fecha_entrada":
					pst.setDate(1, a.getFechaEntrada());
					break;
				case "fecha_modificacion":

						pst.setDate(1, a.getFechaSalida());
					break;
			}
			
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				aux = new Almacen();
				producto = new Producto();
				
				aux.setDescripcion(rs.getString("descripcion"));
				producto.setId(rs.getString("producto"));
				
				aux.setProducto(FnProductos.srcProducto(producto));
				aux.setStock(rs.getInt("stock"));
				aux.setFechaEntrada(rs.getDate("fecha_entrada"));
				aux.setFechaSalida(rs.getDate("fecha_modificacion"));
				
				encontrados.add(aux);
			}
			columna="";
			fila="";
			conn.close();
			
			
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ENTRADA NO REGISTRADA (Funciones.srcUser) " +ex.getMessage());
		}
		
		return encontrados;
	
	}
	
	public static boolean delAlmacen(Almacen a) {
		
		conn = Conexion.conectar();
		sql = "DELETE FROM "
				+ tabla
				+ " WHERE producto=?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, a.getProducto().getId());
			
			int n = pst.executeUpdate();
			conn.close();
			sql="";
			
			if(n!= 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "FALLO AL ELIMNAR LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
			return false;
		}
				
		
		
	}
	
	public static boolean ModAlmacen(Almacen a) {
		conn=Conexion.conectar();
		sql="UPDATE "
				+ tabla
				+ " SET stock=?,fecha_modificacion=?, descripcion=? WHERE producto=?;";
		
		try{

			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setInt(1, a.getStock());
			pst.setDate(2, a.getFechaSalida());
			pst.setString(3, a.getDescripcion());
			pst.setString(4, a.getProducto().getId());
			
	
			int n = pst.executeUpdate();
			
			conn.close();
			
			if(n!=0){
				return true;
			}else{
				return false;
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "FALLO AL ACTUALIZAR LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public static ArrayList<Almacen> list(){
		
		conn = Conexion.conectar();
		Statement enunciado;
		ResultSet resultados;
		
		ArrayList<Almacen> lista = new ArrayList<>();
		Almacen aux = null;
		Producto producto = null;
		sql = "select * from "
				+ tabla
				+ " order by fecha_entrada;";
		
		try {
			enunciado = conn.createStatement();
			resultados = enunciado.executeQuery(sql);
			
			while(resultados.next()) {
				aux = new Almacen();
				producto = new Producto();
				producto.setId(resultados.getString(1));
				
				producto = FnProductos.srcProducto(producto);
				
				aux.setProducto(producto);
				aux.setStock(resultados.getInt(2));
				aux.setFechaEntrada(resultados.getDate(3));
				aux.setFechaSalida(resultados.getDate(4));
				aux.setDescripcion(resultados.getString(5));
				
				lista.add(aux);
			}
			
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, e);
			return null;
		}
	}
	
}
