package Ventanas;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Iconos {
	
	
	
	public Iconos() {
		
	}
	
	public Icon icono (String icon) {
		
		
		switch(icon) {
		
		case "entradas":
			icon = "/img/entradas.png";
			break;
			
		case "salidas":
			icon = "/img/salidas.png";
			break;
			
		case "proveedores":
			icon = "/img/proveedores.png";
			break;
			
		case "productos":
			icon = "/img/productos.png";
			break;
			
		case "almacen":
			icon = "/img/almacen.png";
			break;
			
		case "pagos":
			icon = "/img/pagos.png";
			break;
			
		case "pedidos":
			icon = "/img/pedidos.png";
			break;
		
		}
		
		return new ImageIcon(getClass().getResource(icon));
		
	}
	

}
