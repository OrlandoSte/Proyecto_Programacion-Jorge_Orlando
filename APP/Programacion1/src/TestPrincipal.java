import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestPrincipal {
	Conexion conA = new Conexion();
	Connection con = conA.abrirConexion();
	Connection conPrueba;

   

	@Test
	void testConexion() throws SQLException {
		String userName = "orlo";
        String password = "Admin@18";
        String url = "jdbc:mysql://localhost:3306/proyectoprogramación2";
         
        String si = url+ userName+ password;
         
		assertTrue(si.equalsIgnoreCase(url+userName+password));
		
	}
	
	@Test
	void testEdad() {
		Inscribirse ins = new Inscribirse();
		String fecha = "1999-12-18";
		String edad = "24";
		int edadFun = ins.calcularEdad(fecha);
		assertTrue(Integer.toString(edadFun).equals(edad));
		
	}
	
	
	@Test
	void testDni() {
		int primerosOcho = 9719327;
		String letra = "X";
		int a = primerosOcho;
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int resto = a % 23;
		char letraCalculada = letras.charAt(resto);
		
		assertTrue(letra.equals(letraCalculada+""));
	}
	
	@Test
	void testCarrera() {
		String fecha = "1999-12-12";
		AñadirPersona pers = new AñadirPersona();
		
		assertTrue(10 == pers.calcularCarrera(fecha));
		
	}
	


}
