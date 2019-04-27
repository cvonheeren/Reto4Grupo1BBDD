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
	
	/**
	 * Pide una conexion al pool de conexiones y la almacena en el atributo 'conn'
	 */
	private void conectar() {
		try {
			conn = pool.getConnection();
		} catch (SQLException e) {
			//Implementar logger?
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	/**
	 * Obtiene una lista con los nombres de todas las ciudades
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarListaDestinos() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT DISTINCT UBICACIONES.NOMBRE FROM ALOJAMIENTOS, UBICACIONES WHERE ALOJAMIENTOS.COD_UBICACION = UBICACIONES.COD_UBICACION ORDER BY UBICACIONES.NOMBRE ASC";
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Obtiene los alojamientos ubicados en la ciudad especificada
	 * @param ciudad Nombre de la ciudad por la que se quiere restringir la busqueda
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarListaAlojamientos(String busqueda) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM ALOJAMIENTOS, UBICACIONES WHERE ALOJAMIENTOS.COD_UBICACION = UBICACIONES.COD_UBICACION AND (ALOJAMIENTOS.NOMBRE LIKE UPPER(?) OR UBICACIONES.NOMBRE LIKE ?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + busqueda + "%");
			stmt.setString(2, "%" + busqueda + "%");
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Obtiene los nombres de todos los alojamientos
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarListaAlojamientos() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT NOMBRE FROM ALOJAMIENTOS";
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Obtiene las habitaciones del alojamiento indicado
	 * @param codAlojamiento codigo del alojamiento
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarListaHabitaciones(int codAlojamiento) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		// informacion de todos los dormitorios de un hotel
		String query = "SELECT * FROM ALOJAMIENTO_DORMITORIO, DORMITORIOS WHERE ALOJAMIENTO_DORMITORIO.COD_DORMITORIO = DORMITORIOS.COD_DORMITORIO AND COD_ALOJAMIENTO = ?";		

		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, codAlojamiento);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Obtiene las habitaciones reservadas del alojamiento indicado
	 * @param codAlojamiento codigo del alojamiento
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarHabitacionesReservadas(int codAlojamiento) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		// codigo y cantidad de los dormitorios reservados de un hotel
		String query = "SELECT COD_DORMITORIO, CANTIDAD FROM RESERVA_DORMITORIO, RESERVAS WHERE RESERVA_DORMITORIO.COD_RESERVA = RESERVAS.COD_RESERVA AND COD_ALOJAMIENTO = ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, codAlojamiento);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Obtiene el cliente correspondiente al dni y contrasena indicados
	 * @param dniUsuario DNI que se quiere buscar
	 * @param pass Contrasena que se quiere buscar
	 * @return ResultSet resultado devuelto por la consulta
	 */
	public ResultSet comprobarCliente(String dniUsuario, String pass) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM CLIENTES WHERE DNI = ? AND CONTRA = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, dniUsuario);
			stmt.setString(2, pass);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Inserta los valores de una reserva en la tabla 'reservas'
	 * @param codHotel Codigo del hotel en el que se efectua la reserva
	 * @param precio Precio de la reserva
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet insertarReserva(int codHotel, float precio) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "INSERT INTO RESERVAS (COD_RESERVA, COD_ALOJAMIENTO, PRECIO) VALUES (NULL, ?, ?)";
		try {
			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, codHotel);
			stmt.setFloat(2, precio);
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
		} catch (SQLException e1) {
			//Implementar logger?
			e1.printStackTrace();
		}
		return result;
	}
}
