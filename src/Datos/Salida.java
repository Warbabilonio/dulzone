package Datos;

import java.sql.Date;

public class Salida {
	
	private Double importe;
	private java.sql.Date fecha;
	private String tipo;
	private String descripcion;
	/**
	 * 
	 */
	public Salida() {
		super();
	}
	/**
	 * @param importe
	 * @param fecha
	 * @param tipo
	 * @param descripcion
	 */
	public Salida(Double importe, Date fecha, String tipo, String descripcion) {
		super();
		this.importe = importe;
		this.fecha = fecha;
		this.tipo = tipo.toUpperCase();
		this.descripcion = descripcion.toUpperCase();
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
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
	
	

}
