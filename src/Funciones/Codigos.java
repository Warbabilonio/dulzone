package Funciones;

import Datos.Proveedor;

public class Codigos {

	public static String idProveedor(String nombre, String telefono) {
		
		String id="";
		
		
		char[] caracteres = new char[10];
		
		for(int i = 0; i < 10; i++) {
			if(i<4) {
				if(nombre.charAt(i) == ' ')
					caracteres[i] = 'X';
				else
					caracteres[i] = nombre.toUpperCase().charAt(i);
			}else {
				caracteres[i] = conversorChar(telefono.charAt(i-4), telefono.charAt(i-3));
			}
		}
		
		for(char x : caracteres) {
			id += x; 
		}
		
		/*** PROBAR LUEGO
		 * 
		 
		if(FnProveedores.checkIdProveedor(id)) {
			String numeros = id.substring(5);
			int reemplazo = 1 + Integer.parseInt(numeros);
			
			id.replaceAll(numeros, String.valueOf(reemplazo));
			
			
		}
		*/
		return id;
	}

	private static char conversorChar(char char1, char char2) {
		// TODO Auto-generated method stub
		int n1, n2, result;
			
		n1 = Integer.parseInt(Character.toString(char1));
		n2 = Integer.parseInt(Character.toString(char2));
		
		if(n2 == 0) {
			n2 = n1;
			n1 = 0;
			
		}
		
		result = n1 % n2;
		
		return String.valueOf(result).charAt(0);
	}
}
