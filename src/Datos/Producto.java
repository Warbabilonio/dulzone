package Datos;

import java.sql.Date;

public class Producto {

	private String id;
	private String nombre;
	private Double precio;
	private Double precioPVP;
	private java.sql.Date caducidad;
	private int unidadesCaja;
	/**
	 * 
	 */
	public Producto() {
		super();
		
	}
	/**
	 * @param id
	 * @param precio
	 * @param precioPVP
	 * @param caducidad
	 * @param unidadesCaja
	 */
	public Producto(String id,String nombre, Double precio, Double precioPVP, Date caducidad, int unidadesCaja) {
		super();
		this.id = id.toUpperCase();
		this.nombre = nombre.toUpperCase();
		this.precio = precio;
		this.precioPVP = precioPVP;
		this.caducidad = caducidad;
		this.unidadesCaja = unidadesCaja;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id.toUpperCase();
	}
	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	/**
	 * @return the precioPVP
	 */
	public Double getPrecioPVP() {
		return precioPVP;
	}
	/**
	 * @param precioPVP the precioPVP to set
	 */
	public void setPrecioPVP(Double precioPVP) {
		this.precioPVP = precioPVP;
	}
	/**
	 * @return the caducidad
	 */
	public java.sql.Date getCaducidad() {
		return caducidad;
	}
	/**
	 * @param caducidad the caducidad to set
	 */
	public void setCaducidad(java.sql.Date caducidad) {
		this.caducidad = caducidad;
	}
	/**
	 * @return the unidadesCaja
	 */
	public int getUnidadesCaja() {
		return unidadesCaja;
	}
	/**
	 * @param unidadesCaja the unidadesCaja to set
	 */
	public void setUnidadesCaja(int unidadesCaja) {
		this.unidadesCaja = unidadesCaja;
	}
	
	

	
	
}
