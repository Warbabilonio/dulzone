package Funciones;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
	
	private static java.util.Date fechaUtil;
	private static java.sql.Date fechaSql;
	private static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String toString(java.sql.Date fch) {
		return fch.toString();
	}
	
	public static java.sql.Date toDate(String fch){
		return java.sql.Date.valueOf(fch);
	}
	
	public static java.sql.Date hoy(){
		fechaUtil = new Date();
		fechaSql = new java.sql.Date(fechaUtil.getTime());
		return fechaSql;
	}

}
