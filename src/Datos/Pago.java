package Datos;

import java.sql.Date;

public class Pago {
	private Proveedor proveedor;
	private Double importe;
	private java.sql.Date fecha;
	private boolean pagado;
	private String descripcion;
	
		
	
	public Pago() {
		this.proveedor = null;
		this.importe = 0.00;
		this.fecha = null;
		this.pagado = false;
		this.descripcion = "";
	}
	
	public Pago(Proveedor proveedor, Double importe, Date fecha, boolean pagado, String descripcion) {
		
		this.proveedor = proveedor;
		this.importe = importe;
		this.fecha = fecha;
		this.pagado = pagado;
		this.descripcion = descripcion.toUpperCase();
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
		this.descripcion = descripcion.toUpperCase();
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
	 * @return the fecha
	 */
	public java.sql.Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the pagado
	 */
	public boolean isPagado() {
		return pagado;
	}
	/**
	 * @param pagado the pagado to set
	 */
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	
}