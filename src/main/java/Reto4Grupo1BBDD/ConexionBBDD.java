package Reto4Grupo1BBDD;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBBDD {

	/**
	 * Realiza la conexion a la base de datos con los parametros de un txt
	 * @param testos El txt con el nombre de la BD, usuario y contrasenia
	 * @return La conecion
	 */
	public Connection conectarBD(Textos testos) {
		Connection conexion = null;
		
		final String NombreFichero = System.getProperty("user.dir") + "\\datosBD.txt";
		String[] datos = testos.cogerDatosDeFichero(NombreFichero);
		
		String url = "jdbc:mysql://" + datos[0] + "/" + datos[1] + "?useTimezone=true&serverTimezone=UTC";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, datos[2], datos[3]);
		} catch (Exception e) {
			//Implementar logger?
			System.out.println(e.getMessage());
		}
		return conexion;
	}
}
