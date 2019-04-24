package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Datos.Entrada;

public class FnEntradas {
	
	private static Connection conn = null;
	private static String sql = "";
	private static String columna = "";
	private static String fila = "";
	private static String tabla="entradas";
	
	
	
	
	public static boolean regEntrada(Entrada e) {
		//if(!existsEntrada(e)) {
			conn = Conexion.conectar();
			sql = "insert into "
					+ tabla
					+ "(importe,fecha,tipo,descripcion) values(?,?,?,?);";
			try {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setDouble(1, e.getImporte());
				pst.setDate(2, e.getFecha());
				pst.setString(3, e.getTipo());
				pst.setString(4, e.getDescripcion());
				
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
	
	public static ArrayList<Entrada> srcEntrada(Entrada e, String col) {
	
		conn = Conexion.conectar();
		columna = col;
				
		ArrayList<Entrada> encontrados = new ArrayList<Entrada>();
		Entrada aux = null;		
					
		sql = "select * from "
				+ tabla
				+ " where "
				+ columna
				+ "=?;";
		
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			
			switch(columna) {
				case "importe":
					pst.setDouble(1, e.getImporte());
					break;
				case "fecha":
					pst.setDate(1, e.getFecha());
					break;
				case "tipo":
					pst.setString(1, e.getTipo());
					break;
			}
			
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				aux = new Entrada();
				aux.setImporte(rs.getDouble("importe"));
				aux.setFecha(rs.getDate("fecha"));
				aux.setTipo(rs.getString("tipo"));
				aux.setDescripcion(rs.getString("descripcion"));
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
	
	
	
	public static boolean delEntrada(Entrada e) {
		
		conn = Conexion.conectar();
		String columna1 = "importe";
		String columna2 = "fecha";
		String columna3 = "tipo";
		String columna4 = "descripcion";
		
		Double fila1 = e.getImporte();
		java.sql.Date fila2 = e.getFecha();
		String fila3 = e.getTipo();
		String fila4 = e.getDescripcion();
		
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
			fila = "";
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
	
	public static boolean modEntrada(Entrada e){
		
		conn = Conexion.conectar();
		sql = "UPDATE "
				+ tabla
				+ " SET descripcion=? WHERE importe=? and fecha=? and tipo=?;";
		
		try{

			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setString(1, e.getDescripcion());
			pst.setDouble(2, e.getImporte());
			pst.setDate(3, e.getFecha());
			pst.setString(4, e.getTipo());
			
	
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
	
	
	public static ArrayList<Entrada> list(){
		
		conn = Conexion.conectar();
		Statement enunciado;
		ResultSet resultados;
		
		ArrayList<Entrada> lista = new ArrayList<>();
		sql = "select * from "
				+ tabla
				+ " order by tipo;";
		
		try {
			enunciado = conn.createStatement();
			resultados = enunciado.executeQuery(sql);
			
			while(resultados.next()) {
				Entrada aux = new Entrada();
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
	
	public static boolean existsEntrada(Entrada e) {
		
		boolean esta = false;
		conn = Conexion.conectar();
		sql = "select * from "
				+ tabla
				+ " where importe=? and fecha=? and tipo=? and descripcion=?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, e.getImporte());
			pst.setDate(2, e.getFecha());
			pst.setString(3, e.getTipo());
			pst.setString(4, e.getDescripcion());
			
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


