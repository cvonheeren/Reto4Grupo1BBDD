package Reto4Grupo1BBDD;

import static org.junit.Assert.*;

import org.junit.Test;


public class TextosTest {

	@Test
	public void testCogerDatosDeFicheroTest() {
		Textos textos = new Textos();
		
		String ficheroTest = "./datosBD.txt";
		String ficheroErrorTest = "";
		String[] datoTest = {"localhost", "termibus", "hr", "PepeJeans"};
		String[] resultado = textos.cogerDatosDeFichero(ficheroTest);
		System.out.println(resultado[3]);
		assertEquals(resultado[0], datoTest[0]);
		assertEquals(resultado[1], datoTest[1]);
		assertEquals(resultado[2], datoTest[2]);
		assertEquals(resultado[3], datoTest[3]);
		
		String[] resultadoError = textos.cogerDatosDeFichero(ficheroErrorTest);
		assertEquals(resultadoError[0], null);		
	}

}
