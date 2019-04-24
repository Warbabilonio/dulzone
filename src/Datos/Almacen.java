package Datos;

import java.sql.Date;

public class Almacen {

	private Producto producto;
	private int stock;
	private java.sql.Date fechaEntrada;
	private java.sql.Date fechaModificacion;
	private String descripcion;
	/**
	 * 
	 */
	public Almacen() {
		super();
	}
	/**
	 * @param producto
	 * @param stock
	 * @param fechaEntrada
	 * @param fechaSalida
	 */
	public Almacen(Producto producto, int stock, Date fechaEntrada, Date fechaModificacion, String descripcion) {
		super();
		this.producto = producto;
		this.stock = stock;
		this.fechaEntrada = fechaEntrada;
		this.fechaModificacion = fechaModificacion;
		this.descripcion = descripcion;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	/**
	 * @return the fechaEntrada
	 */
	public java.sql.Date getFechaEntrada() {
		return fechaEntrada;
	}
	/**
	 * @param fechaEntrada the fechaEntrada to set
	 */
	public void setFechaEntrada(java.sql.Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	/**
	 * @return the fechaSalida
	 */
	public java.sql.Date getFechaSalida() {
		return fechaModificacion;
	}
	/**
	 * @param fechaSalida the fechaSalida to set
	 */
	public void setFechaSalida(java.sql.Date fechaSalida) {
		this.fechaModificacion = fechaSalida;
	}
	

	public void salida(int salida) {
		this.stock -= salida;
	}
	
	public void entrada(int entrada) {
		this.stock += entrada;
	}
}
