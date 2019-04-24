package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Datos.Entrada;
import Datos.Pago;
import Datos.Proveedor;

public class FnPagos {
	
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	
	private static String sql = "";
	private static String columna = "";
	private static String columna1 = "importe";
	private static String columna2 = "fecha";
	private static String columna3 = "pagado";
	private static String columna4 = "proveedor";
	private static String columna5 = "descripcion";
	
	private static Double fila1 = 0.00;
	private static java.sql.Date fila2 = null;
	private static boolean fila3 = false;
	private static String fila4 = "";
	private static String fila5 = "";
	
	private static String tabla = "pagos";
	
	public static boolean regPago(Pago p) {
		
		conn = Conexion.conectar();
		filas(p);
		sql = "insert into "
				+ tabla
				+ "("
				+ columna1
				+ ","
				+ columna2
				+ ","
				+ columna3
				+ ","
				+ columna4
				+ ","
				+ columna5
				+ ") values(?,?,?,?,?);";
		try {
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, fila1);
			pst.setDate(2, fila2);
			pst.setBoolean(3, fila3);
			pst.setString(4, fila4);
			pst.setString(5, fila5);
			
			int n = pst.executeUpdate();
			
			conn.close();
			pst = null;
			
			if( n != 0 )
				return true;
			else
				return false;
			
		}catch(SQLException e) {
			if(e.getMessage().contains("a foreign key constraint fails"))
				JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO: ESE PROVEEDOR NO ESTA REGISTRADO! - CodigoError: "+ e.getErrorCode() ,"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			else
				JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO (FnPagos.RegPago) - codigoError: " + e.getErrorCode(),"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			//System.out.println(e.getMessage());
			return false;
		}
		
		
	}
	
	public static ArrayList<Pago> srcPago(Pago p, String col){
		
		conn = Conexion.conectar();
		
		columna = col;
		ArrayList<Pago> encontrados = new ArrayList<Pago>();
		Pago aux = null;
		Proveedor auxProv = new Proveedor();
		
		sql = "select * from "
				+ tabla
				+ " where "
				+ columna
				+ "=?;";
		
		try {
			pst = conn.prepareStatement(sql);
			
			switch(columna) {
				case "importe":
					pst.setDouble(1, p.getImporte());
					break;
					
				case "fecha":
					pst.setDate(1, p.getFecha());
					break;
					
				case "pagado":
					pst.setBoolean(1, p.isPagado());
					break;
					
				case "proveedor":
					pst.setString(1, p.getProveedor().getCodigo());
					break;
			}
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				aux = new Pago();
				aux.setImporte(rs.getDouble("importe"));
				aux.setFecha(rs.getDate("fecha"));
				aux.setPagado(rs.getBoolean("pagado"));
				auxProv.setCodigo(rs.getString("proveedor"));
				aux.setProveedor(FnProveedores.srcProveedor(auxProv));// comprobar
				aux.setDescripcion(rs.getString("descripcion"));
				encontrados.add(aux);
			}
			
			sql = "";
			pst = null;
			rs = null;
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR SQL (FnPagos.srcPago) - errorCode: " +e.getErrorCode());
			e.printStackTrace();
		}
		
		return encontrados;
	}

	public static boolean delPago(Pago p) {
		
		conn = Conexion.conectar();
		filas(p);
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
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, fila1);
			pst.setDate(2, fila2);
			pst.setBoolean(3, fila3);
			pst.setString(4, fila4);
			
			int n = pst.executeUpdate();
			
			columna = "";
			sql = "";
			pst = null;
			conn.close();
			
			if( n != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "FALLO AL ELIMNAR LOS DATOS - errorCode  :" + e.getErrorCode(),"ERROR",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean modPago(Pago p, String col) {
		
		columna = col;
		conn = Conexion.conectar();
		sql = "update "
				+ tabla
				+ " set "
				+ columna
				+ "=? where importe=? and fecha=? and proveedor=?;";
				
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setDouble(2, p.getImporte());
			pst.setDate(3, p.getFecha());
			pst.setString(4, p.getProveedor().getCodigo());
			
			switch(columna) {
				
			case "pagado":
				pst.setBoolean(1, p.isPagado());
				break;
				
			case "descripcion":
				pst.setString(1, p.getDescripcion());
				break;
		}
		
		int n = pst.executeUpdate();
		columna = "";
		sql = "";
		conn.close();
		
		if( n != 0)
			return true;
		else
			return false;
		
	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "FALLO AL ACTUALIZAR LOS DATOS - errorCode: " +e.getErrorCode(),"ERROR",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		
	
	}
	
public static ArrayList<Pago> list(){
		
		conn = Conexion.conectar();
		Statement enunciado;
		ResultSet resultados;
		Proveedor proveedor = new Proveedor();
		ArrayList<Pago> lista = new ArrayList<>();
		sql = "select * from "
				+ tabla
				+ " order by fecha;";
		
		try {
			enunciado = conn.createStatement();
			resultados = enunciado.executeQuery(sql);
			
			while(resultados.next()) {
				Pago aux = new Pago();
				aux.setImporte(resultados.getDouble(1));
				aux.setFecha(resultados.getDate(2));
				aux.setPagado(resultados.getBoolean(3));
				proveedor.setCodigo(resultados.getString(4));
				aux.setProveedor(FnProveedores.srcProveedor(proveedor));
				aux.setDescripcion(resultados.getString(5));
				
				lista.add(aux);
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, e);
			
		}
		return lista;
	}

	private static void filas(Pago p) {
		// TODO Auto-generated method stub
		fila1 = p.getImporte();
		fila2 = p.getFecha();
		fila3 = p.isPagado();
		fila4 = p.getProveedor().getCodigo();
		fila5 = p.getDescripcion();
	}
	
	

}
