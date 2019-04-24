package Funciones;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Datos.Almacen;
import Datos.Pedido;
import Datos.Producto;
import Datos.Proveedor;

public class FnPedidos {
	
	private static Connection conn = null;
	private static String sql = "";
	private static String columna = "";
	private static String fila = "";
	private static String tabla="pedidos";
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	
	public static boolean regPedido(Pedido p) {
		
		conn = Conexion.conectar();
		sql = "insert into "
				+ tabla
				+ "(fecha_pedido,fecha_entrada,recibido,proveedor,producto,cantidad,importe) values(?,?,?,?,?,?,?);";
		
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setDate(1, p.getFechaPedido());
			pst.setDate(2, p.getFechaEntrada());
			pst.setBoolean(3, p.isRecibido());
			pst.setString(4, p.getProveedor().getCodigo());
			pst.setString(5, p.getProducto().getId());
			pst.setInt(6, p.getCantidad());
			pst.setDouble(7, p.getImporte());
			
			
			
			int n = pst.executeUpdate();
			if(n!=0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().contains("a foreign key constraint fails"))
				JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO: ESE PROVEEDOR NO ESTA REGISTRADO! - CodigoError: "+ e.getErrorCode() ,"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			JOptionPane.showMessageDialog(null, "FALLO AL AÑADIR REGISTRO (FnPedidos.RegPedido) - codigoError: " + e.getErrorCode(),"ERROR",JOptionPane.ERROR_MESSAGE);//cambiar
			
			return false;
		}
		
	}
	
	public static ArrayList<Pedido> srcPedido(Pedido p, String col){
		
		conn = Conexion.conectar();
		
		columna = col;
		ArrayList<Pedido> encontrados = new ArrayList<>();

		
		sql = "select * from "
				+ tabla
				+ " where "
				+ columna
				+ "=?;";
		
		try {
			pst = conn.prepareStatement(sql);
			switch(columna) {
			case "fecha_pedido":
				pst.setDate(1, p.getFechaPedido());
				break;
			case "fecha_entrada":
				if(p.getFechaEntrada() != null)
					pst.setDate(1, p.getFechaEntrada());
				else {
					
					sql = "select * from "
							+ tabla
							+ " where "
							+ columna
							+ " is null;";
					pst = conn.prepareStatement(sql);
					//pst.setNull(1, 0);
				}
				break;
			case "recibido":
				pst.setBoolean(1, p.isRecibido());
				break;
			case "proveedor":
					pst.setString(1, p.getProveedor().getCodigo());
				break;
			case "producto":
				pst.setString(1, p.getProducto().getId());
				break;
			case "cantidad":
				pst.setInt(1, p.getCantidad());
				break;
			case "importe":
				pst.setDouble(1, p.getImporte());
				break;
		}
		
		rs = pst.executeQuery();
		
		
		while(rs.next()) {
			Pedido pedido = new Pedido();
			Proveedor proveedor = new Proveedor();
			Producto producto = new Producto();
			
			pedido.setFechaPedido(rs.getDate(1));
			pedido.setFechaEntrada(rs.getDate(2));
			pedido.setRecibido(rs.getBoolean(3));
			proveedor.setCodigo(rs.getString(4));
			pedido.setProveedor(FnProveedores.srcProveedor(proveedor));
			producto.setId(rs.getString(5));
			pedido.setProducto(FnProductos.srcProducto(producto));
			pedido.setCantidad(rs.getInt(6));
			pedido.setImporte(rs.getDouble(7));
			
			encontrados.add(pedido);
		}
		
		columna = "";
		fila = "";
		rs = null;
		conn.close();
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return encontrados;
	}
	
	public static boolean modPedido(Pedido p) {
		
		conn = Conexion.conectar();
		sql = "UPDATE "
				+ tabla
				+ " SET recibido=?,fecha_entrada=?,importe=?,cantidad=? WHERE fecha_pedido=? AND proveedor=? AND producto=?;";
		
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setBoolean(1, p.isRecibido());
			pst.setDate(2, p.getFechaEntrada());
			pst.setDouble(3, p.getImporte());
			pst.setInt(4, p.getCantidad());
			pst.setDate(5, p.getFechaPedido());
			pst.setString(6, p.getProveedor().getCodigo());
			pst.setString(7, p.getProducto().getId());
			
			int n = pst.executeUpdate();
			
			conn.close();
			sql = "";
			pst = null;
			
			if( n != 0 )
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "FALLO AL ACTUALIZAR LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean delPedido(Pedido p) {
		
		conn = Conexion.conectar();
		sql = "DELETE FROM "
				+ tabla
				+ " WHERE fecha_pedido=? AND proveedor=? AND producto=? AND importe=? AND cantidad=?;";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setDate(1, p.getFechaPedido());
			pst.setString(2, p.getProveedor().getCodigo());
			pst.setString(3, p.getProducto().getId());
			pst.setDouble(4, p.getImporte());
			pst.setInt(5, p.getCantidad());
			
			int n = pst.executeUpdate();
			
			conn.close();
			sql = "";
			pst = null;
			
			if( n!=0 )
				return true;
			else
				return false;
				
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "FALLO AL ELIMNAR LOS DATOS","ERROR",JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
			return false;
		}
		
	
	}
	
	public static ArrayList<Pedido> list(){
		
		conn = Conexion.conectar();
		sql = "SELECT * FROM " +tabla;
		Statement st = null;
		ArrayList<Pedido> lista = new ArrayList<Pedido>();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Producto producto = new Producto();
				Proveedor proveedor = new Proveedor();
				Pedido pedido = new Pedido();
				
				pedido.setFechaPedido(rs.getDate(1));
				pedido.setFechaEntrada(rs.getDate(2));
				pedido.setRecibido(rs.getBoolean(3));
				proveedor.setCodigo(rs.getString(4));
				pedido.setProveedor(FnProveedores.srcProveedor(proveedor));
				producto.setId(rs.getString(5));
				pedido.setProducto(FnProductos.srcProducto(producto));
				pedido.setCantidad(rs.getInt(6));
				pedido.setImporte(rs.getDouble(7));
				
				lista.add(pedido);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, e);
			e.printStackTrace();
		}
		
		return lista;
		
	}

}
