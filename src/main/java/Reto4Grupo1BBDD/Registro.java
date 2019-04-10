package Reto4Grupo1BBDD;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import errores.Conexion;

public class Registro {

	private final static Logger LOG_RAIZ = Logger.getLogger("Reto4Grupo1BBDD");
	
	private final static Logger LOGGER = Logger.getLogger("Reto4Grupo1BBDD.Registro");
	
	public static void main(String[] args) {
		try {
			Manejo();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void Manejo() {
		try {
			Handler consoleHandler = new ConsoleHandler();
			 
			Handler fileHandler = new FileHandler("./baseDatos.log.txt", false);
			 
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			 
			fileHandler.setFormatter(simpleFormatter);
			 
			LOG_RAIZ.addHandler(consoleHandler);
			LOG_RAIZ.addHandler(fileHandler);
			 
			Conexion conexion = new Conexion();
			 
			conexion.conectando();
			 
			LOGGER.log(Level.INFO, "Probando manejo de excepciones");
		} catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error de IO");
        } catch (SecurityException ex) {
            LOGGER.log(Level.SEVERE, "Error de Seguridad");
        }		
	}
	
	public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
}
	
}
