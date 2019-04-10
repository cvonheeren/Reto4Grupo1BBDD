package Reto4Grupo1BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModificarBBDD {
	
	Connection conn = null;
	Pool pool = null;

	public ModificarBBDD() {
		pool = new Pool();
		conectar();
	}
	
	private Connection conectar() {
		try {
			conn = pool.getConnection();
		} catch (SQLException e) {
			//Implementar logger?
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return conn;
	}
	
	public ResultSet cargarListaAlojamientos(String ciudad) {
		
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM HOTEL WHERE NOMBRE LIKE UPPER('%" + ciudad + "%') OR UBICACION LIKE UPPER('%" + ciudad + "%')";
		
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		return result;
	}
	
	public ResultSet cargarListaDestinos() {
		
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT DISTINCT UBICACION FROM HOTEL ORDER BY UBICACION ASC;";
		
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public ResultSet cargarCliente(String dniUsuario) {
		
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "select * from clientes where dni = " + dniUsuario;
		
		try {
			stmt = conn.prepareStatement(query);
			
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 
		return result;
	}
	
	/**
	 * Metodo para insertar valores en una tabla de la base de datos
	 * @param con Conexion a la base de datos
	 * @param query Consulta a realizar de tipo INSERT
	 * @return boolean true si se ha realizado la insercion con exito, false si no
	 */
	public int insertarDatosBD(String query) {
		Statement st;
		int codReserva;
		try {
			st = conn.createStatement();
		} catch (SQLException e1) {
			//Implementar logger?
			e1.printStackTrace();
			return -1;
		}
		
		try {
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet  result = st.getGeneratedKeys();
			result.next();
		    codReserva = result.getInt(1);
		} catch (Exception e){
			//Implementar logger?
			System.out.println(e.getMessage());
			return -1;
		}

		return codReserva;
	}
}
