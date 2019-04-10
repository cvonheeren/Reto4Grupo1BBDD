package Reto4Grupo1BBDD;

public class AlojamientoBBDD {

	protected int codAlojamiento;
	protected String nombre;
	protected String ubicacion;
	
	/**
	 * Objeto padre Alojamiento
	 * @param codAlojamiento Codigo principal del alojamiento
	 * @param nombre Nombre
	 * @param ubicacion Ubicacion
	 */
	public AlojamientoBBDD(int codAlojamiento, String nombre, String ubicacion) {
		this.codAlojamiento = codAlojamiento;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	
	public AlojamientoBBDD() {
		
	}

	public int getCodAlojamiento() {
		return codAlojamiento;
	}

	public void setCodAlojamiento(int codAlojamiento) {
		this.codAlojamiento = codAlojamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String toString()
	{
		return nombre + " (" + ubicacion + ")";
	}
}
