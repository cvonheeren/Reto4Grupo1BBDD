package errores;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

	private final static Logger LOGGER = Logger.getLogger("errores");
	
	public void conectando(){
        LOGGER.log(Level.SEVERE, "Fallo de conexión");
	}
	
}
