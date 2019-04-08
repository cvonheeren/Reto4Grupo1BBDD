package Reto4Grupo1BBDD;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class Pool {
	
	private BasicDataSource dataSource;
	
	public Pool() {
		crearPoolConexiones();
	}
    
    public void crearPoolConexiones() {
    	String[] datos = getDatos();
    	dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://" + datos[0] + "/" + datos[1] + "?serverTimezone=UTC");
        dataSource.setUsername(datos[2]);
        dataSource.setPassword(datos[3]);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }
     
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public String[] getDatos() {
    	Textos textos = new Textos();
    	final String NombreFichero = System.getProperty("user.dir") + "\\datosBD.txt";
    	return textos.cogerDatosDeFichero(NombreFichero);
    }
    
}
