package Reto4Grupo1BBDD;

import java.io.BufferedReader;
import java.io.FileReader;

public class Textos {

	public String[] cogerDatosDeFichero(String datosBD) {
		String[] datos = new String[4];
		FileReader fileReader = null;
		BufferedReader buffer = null;
		
		try {
			fileReader = new FileReader(datosBD);
			buffer = new BufferedReader(fileReader);
			String linea = "";
			String clave = "";
			String dato = "";
			
			while ((linea = buffer.readLine()) != null) {
				clave = linea.substring(0, linea.indexOf(":"));
				dato = linea.substring(linea.indexOf(":") + 2);
				
				switch (clave) {
					case "url": datos[0] = dato; break;
					case "database": datos[1] = dato; break;
					case "user": datos[2] = dato; break;
					case "password": datos[3] = dato; break;
				}
			}
		} catch (Exception e) {
			//Implementar logger?
			System.out.println(e.getMessage());
		} finally {
			try {
				fileReader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return datos;
	}
}
