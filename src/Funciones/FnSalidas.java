package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Datos.Salida;

public class FnSalidas {
	private static Connection conn = null;
	private static String sql = "";
	private static String columna = "";
	private static String tabla="salidas";
	
	
	
	
	public static boolean regEntrada(Salida s) {
		//if(!existsEntrada(e)) {
			conn = Conexion.conectar();
			sql = "insert into "
					+ tabla
					+ "(importe,fecha,tipo,descripcion) values(?,?,?,?);";
			try {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setDouble(1, s.getImporte());
				pst.setDate(2, s.getFecha());
				pst.setString(3, s.getTipo());
				pst.setString(4, s.getDescripcion());
				
				int n = pst.executeUpdate();
				
				conn.close();
				if(n!=0)
					return true;
				else
					return false;
						
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				if(ex.getMessage().contains("Duplicate entry"))
					JOptionPane.showMessageDialog(null, "ESA ENTRADA YA ESTÁ REGISTRADA","ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
				else
					JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO (Funciones.RegUser)" ,"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
				return false;
			}
		//}else
			//JOptionPane.showMessageDialog(null, "ENTRADA YA REGISTRADA" ,"INFO",JOptionPane.INFORMATION_MESSAGE);//cambiar
			//return false;
	}
	
	public static ArrayList<Salida> srcEntrada(Salida s, String col) {
	
		conn = Conexion.conectar();
		columna = col;
				
		ArrayList<Salida> encontrados = new ArrayList<Salida>();
		Salida aux = null;		
					
		sql = "select * from "
				+ tabla
				+ " where "
				+ columna
				+ "=?;";
		
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			
			switch(columna) {
				case "importe":
					pst.setDouble(1, s.getImporte());
					break;
				case "fecha":
					pst.setDate(1, s.getFecha());
					break;
				case "tipo":
					pst.setString(1, s.getTipo());
					break;
			}
			
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				aux = new Salida();
				aux.setImporte(rs.getDouble("importe"));
				aux.setFecha(rs.getDate("fecha"));
				aux.setTipo(rs.getString("tipo"));
				aux.setDescripcion(rs.getString("descripcion"));
				encontrados.add(aux);
			}
			columna="";
			conn.close();
			
			
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ENTRADA NO REGISTRADA (Funciones.srcUser) " +ex.getMessage());
		}
		
		return encontrados;
	}
	
	
	
	public static boolean delEntrada(Salida s) {
		
		conn = Conexion.conectar();
		String columna1 = "importe";
		String columna2 = "fecha";
		String columna3 = "tipo";
		String columna4 = "descripcion";
		
		Double fila1 = s.getImporte();
		java.sql.Date fila2 = s.getFecha();
		String fila3 = s.getTipo();
		String fila4 = s.getDescripcion();
		
		sql = "delete from "
				+ tabla
				+ " where "
				+ columna1 
				+ "=? and "
				+ columna2
				+ "=? and "
				+ columna3
				+ "=? and "
				+ columna4
				+ "=?;";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, fila1);
			pst.setDate(2, fila2);
			pst.setString(3, fila3);
			pst.setString(4, fila4);
			
			int n = pst.executeUpdate();

			columna = "";
			conn.close();
			
			if(n!=0)
				return true;
			else
				return false;
			
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "FALLO AL ELIMNAR LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public static boolean modEntrada(Salida s){
		
		conn = Conexion.conectar();
		sql = "UPDATE "
				+ tabla
				+ " SET descripcion=? WHERE importe=? and fecha=? and tipo=?;";
		
		try{

			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setString(1, s.getDescripcion());
			pst.setDouble(2, s.getImporte());
			pst.setDate(3, s.getFecha());
			pst.setString(4, s.getTipo());
			
	
			int n = pst.executeUpdate();
			
			conn.close();
			
			if(n!=0){
				return true;
			}else{
				return false;
			}
			
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "FALLO AL ACTUALIZAR LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}
	
	
	public static ArrayList<Salida> list(){
		
		conn = Conexion.conectar();
		Statement enunciado;
		ResultSet resultados;
		
		ArrayList<Salida> lista = new ArrayList<>();
		sql = "select * from "
				+ tabla
				+ " order by tipo;";
		
		try {
			enunciado = conn.createStatement();
			resultados = enunciado.executeQuery(sql);
			
			while(resultados.next()) {
				Salida aux = new Salida();
				aux.setImporte(resultados.getDouble(1));
				aux.setFecha(resultados.getDate(2));
				aux.setTipo(resultados.getString(3));
				aux.setDescripcion(resultados.getString(4));
				
				lista.add(aux);
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, e);
			
		}
		return lista;
	}
	
	public static boolean existsEntrada(Salida s) {
		
		boolean esta = false;
		conn = Conexion.conectar();
		sql = "select * from "
				+ tabla
				+ " where importe=? and fecha=? and tipo=? and descripcion=?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, s.getImporte());
			pst.setDate(2, s.getFecha());
			pst.setString(3, s.getTipo());
			pst.setString(4, s.getDescripcion());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
				esta = true;
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("Error existsEntrada");
			ex.printStackTrace();
			esta = false;
		}
		
		return esta;
	}

}
