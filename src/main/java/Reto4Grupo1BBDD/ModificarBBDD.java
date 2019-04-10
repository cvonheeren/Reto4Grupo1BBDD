package Reto4Grupo1BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public ArrayList<AlojamientoBBDD> cargarListaAlojamientos(String ciudad) {
		
		ArrayList<AlojamientoBBDD> listaAlojamientos = new ArrayList<AlojamientoBBDD>();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM HOTEL WHERE NOMBRE LIKE UPPER('%" + ciudad + "%') OR UBICACION LIKE UPPER('%" + ciudad + "%')";
		
		try {
			stmt = conn.prepareStatement(query);
			
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			while (result.next()) {
				int cod_alojamiento = result.getInt("COD_HOTEL");
				String ubicacion = result.getString("UBICACION");
				String nombre = result.getString("NOMBRE");
				//float precio = rs.getFloat("PRECIO");
				listaAlojamientos.add(new AlojamientoBBDD(cod_alojamiento, nombre, ubicacion));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlojamientos;
	}
	
	public ArrayList<String> cargarListaDestinos() {
		
		ArrayList<String> ciudades = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT DISTINCT UBICACION FROM HOTEL ORDER BY UBICACION ASC;";
		
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			while (result.next()) {
				String ubicacion = result.getString("UBICACION");
				ciudades.add(ubicacion);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudades;
	}
	
	public ClienteBBDD cargarCliente(int dniUsuario) {
		
		ClienteBBDD cliente = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "select * from clientes where dni = " + dniUsuario;
		
		try {
			stmt = conn.prepareStatement(query);
			
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			while (result.next()) {
				String dni = result.getString("dni");
				String nombre = result.getString("nombre");
				String password = result.getString("password");
				String direccion = result.getString("direccion");
				cliente = new ClienteBBDD(dni, nombre, password, direccion);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cliente;
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
