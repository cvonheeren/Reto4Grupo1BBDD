package Reto4Grupo1BBDD;

public class ClienteBBDD {
	
	private String dni;
	private String nombre;
	private String password;
	private String direccion;
	
	public ClienteBBDD(String dni, String nombre, String password, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.password = password;
		this.direccion = direccion;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
