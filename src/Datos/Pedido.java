package Datos;

import java.sql.Date;


public class Pedido {
	
	private Integer cantidad;
	private Double importe;
	private Proveedor proveedor;
	private Producto producto;
	private java.sql.Date fechaPedido;
	private java.sql.Date fechaEntrada;
	private boolean recibido;
	/**
	 * 
	 */
	public Pedido() {
		super();
	}
	/**
	 * @param cantidad
	 * @param importe
	 * @param proveedor
	 * @param producto
	 * @param fechaPedido
	 * @param fechaEntrada
	 * @param recibido
	 */
	public Pedido(Integer cantidad, Double importe, Proveedor proveedor, Producto producto, Date fechaPedido,
			Date fechaEntrada, boolean recibido) {
		super();
		this.cantidad = cantidad;
		this.importe = importe;
		this.proveedor = proveedor;
		this.producto = producto;
		this.fechaPedido = fechaPedido;
		this.fechaEntrada = fechaEntrada;
		this.recibido = recibido;
	}
	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the importe
	 */
	public Double getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}
	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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
	 * @return the fechaPedido
	 */
	public java.sql.Date getFechaPedido() {
		return fechaPedido;
	}
	/**
	 * @param fechaPedido the fechaPedido to set
	 */
	public void setFechaPedido(java.sql.Date fechaPedido) {
		this.fechaPedido = fechaPedido;
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
	 * @return the recibido
	 */
	public boolean isRecibido() {
		return recibido;
	}
	/**
	 * @param recibido the recibido to set
	 */
	public void setRecibido(boolean recibido) {
		this.recibido = recibido;
	}
	
	
	
 
}
