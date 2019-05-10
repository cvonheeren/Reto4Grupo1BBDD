package Reto4Grupo1BBDD;

import static org.junit.Assert.*;



import org.junit.Test;


public class TextosTest {

	Textos textos = new Textos();
	
//	@Test
//	public void testCogerDatosDeFicheroTest() {
//		
//		
//		String ficheroTest = "./datosBD.txt";
//		String ficheroErrorTest = "";
//		String[] datoTest = {"localhost", "termibus", "hr", "PepeJeans"};
//		String[] resultado = textos.cogerDatosDeFichero(ficheroTest);
//		System.out.println(resultado[3]);
//		assertEquals(resultado[0], datoTest[0]);
//		assertEquals(resultado[1], datoTest[1]);
//		assertEquals(resultado[2], datoTest[2]);
//		assertEquals(resultado[3], datoTest[3]);
//		
//		String[] resultadoError = textos.cogerDatosDeFichero(ficheroErrorTest);
//		assertEquals(resultadoError[0], null);		
//	}

	@Test
	public void testEncriptar()  {
		String md5 = "d44b121fc3524fe5cdc4f3feb31ceb78";
		
		System.out.println(textos.encriptar("perro"));
		
		assertEquals(md5, textos.encriptar("perro"));		
	}
	
}
