package Datos;

public class Proveedor {

	private String codigo;
	private String nombre;
	private String telefono;
	
	public Proveedor() {
		
		this.codigo = null;
		this.nombre = null;
		this.telefono = null;
	}

	public Proveedor(String codigo, String nombre, String telefono) {
		super();
		this.codigo = codigo.toUpperCase();
		this.nombre = nombre.toUpperCase();
		this.telefono = telefono.toUpperCase();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo.toUpperCase();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono.toUpperCase();
	}
	
	
	
}