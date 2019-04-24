package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Datos.Proveedor;

public class FnProveedores {
	
	private static Connection conn = null;
	private static String sql = "";
	private static String columna = "";
	private static String fila = "";
	private static String tabla="proveedores";
	
	
	private static void ColumnasFilas(Proveedor p) {
		// TODO Auto-generated method stub
		if(p.getCodigo() != null && p.getNombre() == null && p.getTelefono() == null) {
			columna = "id_proveedor";
			fila = p.getCodigo();
		}
		if(p.getNombre() != null && p.getCodigo() == null && p.getTelefono() == null) {
			columna = "nombre";
			fila = p.getNombre();
		}
		if(p.getTelefono() != null && p.getCodigo() == null && p.getNombre() == null) {
			columna = "telefono";
			fila = p.getTelefono();
		}
		
	}
	
	public static boolean regProveedor(Proveedor p) {
		
		conn = Conexion.conectar();
		sql = "insert into "
				+ tabla
				+ "(id_proveedor,nombre,telefono) values(?,?,?);";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getCodigo());
			pst.setString(2, p.getNombre());
			pst.setString(3, p.getTelefono());
			
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
	
	public static Proveedor srcProveedor(Proveedor p) {
	
		conn = Conexion.conectar();
		Proveedor encontrado = null;
				
		ColumnasFilas(p);
			
		sql = "select * from "
				+ tabla
				+ " where "+columna+"=?;";
		
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, fila);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				encontrado = new Proveedor();
				encontrado.setCodigo(rs.getString("id_proveedor"));
				encontrado.setNombre(rs.getString("nombre"));
				encontrado.setTelefono(rs.getString("telefono"));
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
	
	
	
	public static boolean delProveedor(Proveedor p) {
		conn = Conexion.conectar();
		
		ColumnasFilas(p);
		
		sql = "delete from "
				+ tabla
				+ " where "
				+columna 
				+"=?;";
		
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
	
	public static boolean modProveedor(Proveedor p){
		
		conn=Conexion.conectar();
		sql="UPDATE "
				+ tabla
				+ " SET nombre=?, telefono=? WHERE id_proveedor=?;";
		
		try{

			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getTelefono());
			pst.setString(3, p.getCodigo());
			
	
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
	
	
	public static ArrayList<Proveedor> list(){
		
		conn = Conexion.conectar();
		Statement enunciado;
		ResultSet resultados;
		
		ArrayList<Proveedor> lista = new ArrayList<>();
		sql = "select * from "
				+ tabla
				+ " order by nombre;";
		
		try {
			enunciado = conn.createStatement();
			resultados = enunciado.executeQuery(sql);
			
			while(resultados.next()) {
				Proveedor aux = new Proveedor();
				aux.setCodigo(resultados.getString(1));
				aux.setNombre(resultados.getString(2));
				aux.setTelefono(resultados.getString(3));
				
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
