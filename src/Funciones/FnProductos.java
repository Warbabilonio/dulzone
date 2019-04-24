package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Datos.Producto;

public class FnProductos {
	
	private static Connection conn = null;
	private static String sql = "";
	private static String columna = "";
	private static String fila = "";
	private static String tabla = "productos";
	

	public static boolean regProducto(Producto p) {
		
		conn = Conexion.conectar();
		sql = "insert into "
				+ tabla
				+ "(id_producto,nombre,precio,precio_pvp,caducidad,unidades_caja) values(?,?,?,?,?,?);";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getId());
			pst.setString(2, p.getNombre());
			pst.setDouble(3, p.getPrecio());
			pst.setDouble(4, p.getPrecioPVP());
			pst.setDate(5, p.getCaducidad());
			pst.setInt(6, p.getUnidadesCaja());
			
			int n = pst.executeUpdate();
			
			conn.close();
			if(n!=0)
				return true;
			else
				return false;
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().contains("Duplicate entry"))
				JOptionPane.showMessageDialog(null, "ESA ENTRADA YA ESTÁ REGISTRADA","ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			else
				JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO (Funciones.RegUser)" ,"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			return false;
		}
	}
	
	public static Producto srcProducto(Producto p) {
	
		columna = "id_producto";
		fila = p.getId();
		conn = Conexion.conectar();
		Producto encontrado = null;
				
				
		sql = "select * from "
				+ tabla
				+ " where "
				+ columna
				+ "=?;";
		
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, fila);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				encontrado = new Producto();
				encontrado.setId(rs.getString("id_producto"));
				encontrado.setNombre(rs.getString("nombre"));
				encontrado.setPrecio(rs.getDouble("precio"));
				encontrado.setPrecioPVP(rs.getDouble("precio_pvp"));
				encontrado.setCaducidad(rs.getDate("caducidad"));
				encontrado.setUnidadesCaja(rs.getInt("unidades_caja"));
			}
			columna="";
			fila="";
			conn.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ENTRADA NO REGISTRADA (Funciones.srcUser) " +e.getMessage());
			e.printStackTrace();
		
			
		}
		return encontrado;
	}
	
	
	
	public static boolean delProducto(Producto p) {
		
		columna = "id_producto";
		fila = p.getId();
		conn = Conexion.conectar();
		
		
		sql = "delete from "
				+ tabla
				+ " where "
				+ columna 
				+ "=?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, fila);
			
			int n = pst.executeUpdate();

			columna = "";
			fila = "";
			conn.close();
			if(n!=0)
				return true;
			else
				return false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "FALLO AL ELIMNAR LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public static boolean modProducto(Producto p){
		
		conn=Conexion.conectar();
		sql="UPDATE "
				+ tabla
				+ " SET nombre=?, precio=?, precio_pvp=?, caducidad=?, unidades_caja=? WHERE id_producto=?;";
		
		try{

			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setString(1, p.getNombre());
			pst.setDouble(2, p.getPrecio());
			pst.setDouble(3, p.getPrecioPVP());
			pst.setDate(4, p.getCaducidad());
			pst.setInt(5, p.getUnidadesCaja());
			pst.setString(6, p.getId());
			
	
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
	
	
	public static ArrayList<Producto> list(){
		
		conn = Conexion.conectar();
		Statement enunciado;
		ResultSet resultados;
		
		ArrayList<Producto> lista = new ArrayList<>();
		sql = "select * from "
				+ tabla
				+ " order by precio;";
		
		try {
			enunciado = conn.createStatement();
			resultados = enunciado.executeQuery(sql);
			
			while(resultados.next()) {
				Producto aux = new Producto();
				aux.setId(resultados.getString(1));
				aux.setNombre(resultados.getString(2));
				aux.setPrecio(resultados.getDouble(3));
				aux.setPrecioPVP(resultados.getDouble(4));
				aux.setCaducidad(resultados.getDate(5));
				aux.setUnidadesCaja(resultados.getInt(6));
				
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

