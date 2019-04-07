package Reto4Grupo1BBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;



public class PruebaPool {

	private DataSource dataSource;
	
	public PruebaPool() {
		crearConexiones();
	}

	/**
	 * Crea un pool de conexiones
	 * @return Devuelve el objeto DataSource que gestiona las conexiones
	 */
	public void crearConexiones() {
		Properties propiedades = new Properties();
		
		try {
			propiedades.load(new FileInputStream("/src/main/java/Reto4Grupo1BBDD/datos.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			dataSource = BasicDataSourceFactory.createDataSource(propiedades);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
	
//	/**
//	 * Consulta a la BBDD
//	 * @param dataSource La pool de conexiones
//	 * @param query El select
//	 * @return Devuelve un ResulSet con la informacion de la BBDD
//	 */
//	public ResultSet ConsultaBBDD(DataSource dataSource, String query) {
//		ResultSet rs = null;
//		Connection con =  null;
//		
//		try {
//			con = dataSource.getConnection();
//			Statement st = con.createStatement();
//			rs = st.executeQuery(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return rs;
//	}
//	
//	/**
//	 * Metodo para insertar valores en una tabla de la base de datos
//	 * @param dataSource La pool de conexiones
//	 * @param query Consulta a realizar de tipo INSERT
//	 * @return true si se ha realizado la insercion con exito, false si no
//	 */
//	public boolean insertarDatosBD(DataSource dataSource, String query) {
//		Connection con =  null;
//		
//		try {
//			con = dataSource.getConnection();
//			Statement st = con.createStatement();
//			st.executeUpdate(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//			
//		return true;
//	}
}
