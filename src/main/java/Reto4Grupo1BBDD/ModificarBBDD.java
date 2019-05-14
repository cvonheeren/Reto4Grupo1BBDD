package Reto4Grupo1BBDD;

import java.sql.Connection;
import java.sql.Date;
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
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	/**
	 * Obtiene una lista con los nombres de todas las ciudades
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarNombresDestinos() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT DISTINCT UBICACIONES.NOMBRE FROM ALOJAMIENTOS, UBICACIONES WHERE ALOJAMIENTOS.COD_UBICACION = UBICACIONES.COD_UBICACION ORDER BY UBICACIONES.NOMBRE ASC";
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Obtiene los alojamientos ubicados en la ciudad especificada
	 * @param ciudad Nombre de la ciudad por la que se quiere restringir la busqueda
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarAlojamientos(String busqueda, int estrellasMin, int estrellasMax, String[] tipoAloj) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM ALOJAMIENTOS, UBICACIONES WHERE ALOJAMIENTOS.COD_UBICACION = UBICACIONES.COD_UBICACION AND (ALOJAMIENTOS.NOMBRE LIKE UPPER(?) OR UBICACIONES.NOMBRE LIKE UPPER(?) OR UBICACIONES.COD_POSTAL LIKE ?) AND ((ESTRELLAS BETWEEN ? AND ?) OR ESTRELLAS IS NULL) AND TIPO IN (?,?,?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, "%" + busqueda + "%");
			stmt.setString(2, "%" + busqueda + "%");
			stmt.setString(3, "%" + busqueda + "%");
			stmt.setInt(4, estrellasMin);
			stmt.setInt(5, estrellasMax);
			stmt.setString(6, tipoAloj[0]);
			stmt.setString(7, tipoAloj[1]);
			stmt.setString(8, tipoAloj[2]);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Obtiene los nombres de todos los alojamientos
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarNombresAlojamientos() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT NOMBRE FROM ALOJAMIENTOS";
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Obtiene las habitaciones del alojamiento indicado
	 * @param codAlojamiento codigo del alojamiento
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarHabitaciones(int codAlojamiento) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		// informacion de todos los dormitorios de un hotel
		String query = "SELECT * FROM ALOJAMIENTO_HABITACION, HABITACIONES WHERE ALOJAMIENTO_HABITACION.COD_HABITACION = HABITACIONES.COD_HABITACION AND COD_ALOJAMIENTO = ?";		

		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, codAlojamiento);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Obtiene las habitaciones reservadas del alojamiento indicado
	 * @param codAlojamiento codigo del alojamiento
	 * @return ResultSet Resultado devuelto por la consulta
	 */	
	public ResultSet cargarHabitacionesReservadas(int codAlojamiento, Date fechaEntrada, Date fechaSalida) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		// codigo y cantidad de los dormitorios reservados de un hotel
		String query = "SELECT COD_HABITACION, CANTIDAD FROM RESERVA_HABITACION, RESERVAS WHERE RESERVA_HABITACION.COD_RESERVA = RESERVAS.COD_RESERVA AND COD_ALOJAMIENTO = ? AND FECHAENTRADA <= ? AND FECHASALIDA >= ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, codAlojamiento);
			stmt.setDate(2, fechaSalida);
			stmt.setDate(3, fechaEntrada);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Obtiene las estancias del alojamiento indicado
	 * @param codAlojamiento codigo del alojamiento
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet cargarEstancias(int codAlojamiento) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		// informacion de todos los dormitorios de un hotel
		String query = "SELECT * FROM ALOJAMIENTO_ESTANCIA, ESTANCIAS WHERE ALOJAMIENTO_ESTANCIA.COD_ESTANCIA = ESTANCIAS.COD_ESTANCIA AND COD_ALOJAMIENTO = ?";		

		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, codAlojamiento);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Obtiene el cliente correspondiente al dni y contrasena indicados
	 * @param dniUsuario DNI que se quiere buscar
	 * @param pass Contrasena que se quiere buscar
	 * @return ResultSet resultado devuelto por la consulta
	 */
	public ResultSet comprobarCliente(String user, String pass) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM CLIENTES WHERE USER_NAME = ?  AND CONTRASENA = ?";
		Textos textos = new Textos();
		pass = textos.encriptar(pass);
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Inserta los valores de una reserva en la tabla 'reservas'
	 * @param codHotel Codigo del hotel en el que se efectua la reserva
	 * @param precio Precio de la reserva
	 * @param fecha2 
	 * @param fecha1 
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet insertarReserva(int codAlojamiento, float precio, Date fechaCompra, Date fecha1, Date fecha2) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "INSERT INTO RESERVAS (COD_RESERVA, COD_ALOJAMIENTO, FECHACOMPRA, FECHAENTRADA, FECHASALIDA, PRECIOTOTAL) VALUES (NULL, ?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, codAlojamiento);
			stmt.setDate(2, fechaCompra);
			stmt.setDate(3, fecha1);
			
			stmt.setDate(4, fecha2);
			stmt.setFloat(5, precio);
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
		} catch (SQLException e1) {
			//Implementar logger?
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Inserta los valores de una reserva en la tabla 'reservas'
	 * @param codHotel Codigo del hotel en el que se efectua la reserva
	 * @param precio Precio de la reserva
	 * @param fecha2 
	 * @param fecha1 
	 * @return ResultSet Resultado devuelto por la consulta
	 */
	public ResultSet insertarReservaHabitaciones(int codReserva, int codHabitacion, int cantidad) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "INSERT INTO RESERVA_HABITACION (COD_RESERVA, COD_HABITACION, CANTIDAD) VALUES (?, ?, ?)";
		try {
			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, codReserva);
			stmt.setInt(2, codHabitacion);
			stmt.setInt(3, cantidad);
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
		} catch (SQLException e1) {
			//Implementar logger?
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	/**
	 * Inserta los datos del nuevo cliente en la tabla 'clientes'
	 * @param dni
	 * @param password
	 * @param nombre
	 * @param Apellidos
	 * @param fechanac
	 * @param mail
	 * @return true si hace el insert y false si no
	 */
	public ResultSet insertarCliente(String user, String dni, String password, String nombre, String apellidos, Date fechanac, String mail) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "INSERT INTO CLIENTES (USER_NAME, DNI, CONTRASENA, NOMBRE, APELLIDOS, FECHANAC, EMAIL, FECHABASES) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_DATE)";
		
		// encripta los datos personales
		Textos textos = new Textos();
		dni = textos.encriptar(dni).toString();
		password = textos.encriptar(password).toString();
		nombre = textos.encriptar(nombre).toString();
		apellidos = textos.encriptar(apellidos).toString();
		mail = textos.encriptar(mail).toString();
		
		try {
			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user);
			stmt.setString(2, dni);
			stmt.setString(3, password);
			stmt.setString(4, nombre);
			stmt.setString(5, apellidos);
			stmt.setDate(6, fechanac);
			stmt.setString(7, mail);
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
		} catch (SQLException e1) {
			//Implementar logger?
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	}
	
	
	
	
	/**
	 * Comprueba que un dni esté o no en la BBDD
	 * @param dniUsuario El dni que se va a comprobar
	 * @return El dni si existe
	 */
	public ResultSet comprobarDni(String dniUsuario) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM CLIENTES WHERE DNI = ?";
		Textos textos = new Textos();
		dniUsuario = textos.encriptar(dniUsuario);
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, dniUsuario);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return result;
	
	}
	
	public float ValidarCodPromo(String codPromo, String user) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		float descuento;
		String query = "SELECT * FROM PROMOCIONES WHERE USER_NAME = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		try {
			result.next();
			if(result.getString("CODPROMO").equals(codPromo))
			{
				descuento=result.getFloat("DESCUENTO");
				BorrarPromocion(codPromo);
				return descuento;
			}
			else
			{
				return -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -2;
	
	}

	private void BorrarPromocion(String codPromo) {
		PreparedStatement stmt = null;
		String query = "DELETE FROM PROMOCIONES WHERE CODPROMO = ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, codPromo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
