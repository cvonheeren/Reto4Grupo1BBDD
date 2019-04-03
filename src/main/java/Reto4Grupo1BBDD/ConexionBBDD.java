package Reto4Grupo1BBDD;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBBDD {

	public Connection conectarBD(Textos testos) {
		Connection conexion = null;
		
		final String NombreFichero = "datosBD.txt";
		String[] datos = testos.cogerDatosDeFichero(NombreFichero);
		
		String url = "jdbc:mysql://" + datos[0] + "/" + datos[1];

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, datos[2], datos[3]);
		} catch (Exception e) {
			//Implementar logger?
			System.out.println(e.getMessage());
		}
		return conexion;
	}
}
