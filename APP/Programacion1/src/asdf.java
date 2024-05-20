
public class asdf {

	public static void main(String[] args) {
		
		int codigoDeCarrera=0;
        if(calcularEdad(tFechaNacimiento.getText())>5) {
        	String Chupetines="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido=10";
        	rs = ((java.sql.Statement) stmt).executeQuery(Chupetines);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())<9 && calcularEdad(tFechaNacimiento.getText())>4) {
        	String Minibenjamines="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido<9 AND cod_recorrido>4";
        	rs = ((java.sql.Statement) stmt).executeQuery(Minibenjamines);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())==9||calcularEdad(tFechaNacimiento.getText())==10) {
        	String Benjamin="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido=9 OR cod_recorrido=10";
        	rs = ((java.sql.Statement) stmt).executeQuery(Benjamin);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())==11||calcularEdad(tFechaNacimiento.getText())==12) {
        	String Alevin="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido=9 OR cod_recorrido=10";
        	rs = ((java.sql.Statement) stmt).executeQuery(Alevin);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())==13||calcularEdad(tFechaNacimiento.getText())==14) {
        	String Infantil="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido=9 OR cod_recorrido=10";
        	rs = ((java.sql.Statement) stmt).executeQuery(Infantil);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())==15||calcularEdad(tFechaNacimiento.getText())==16) {
        	String Cadete="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido=9 OR cod_recorrido=10";
        	rs = ((java.sql.Statement) stmt).executeQuery(Cadete);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())==17||calcularEdad(tFechaNacimiento.getText())==19) {
        	String Juvenil="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido=9 OR cod_recorrido=10";
        	rs = ((java.sql.Statement) stmt).executeQuery(Juvenil);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())>19 && calcularEdad(tFechaNacimiento.getText())<40) {
        	String Senior="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido<40 AND cod_recorrido>19";
        	rs = ((java.sql.Statement) stmt).executeQuery(Senior);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())>39 && calcularEdad(tFechaNacimiento.getText())<=99 && tSexo.getText()=="H") {
        	String VeteranoH="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido<=99 AND cod_recorrido>39 AND sexo='H'";
        	rs = ((java.sql.Statement) stmt).executeQuery(VeteranoH);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
        }
        else if(calcularEdad(tFechaNacimiento.getText())>34 && calcularEdad(tFechaNacimiento.getText())<=99 && tSexo.getText()=="M") {
        	String VeteranoM="SELECT codigo_carrera FROM CARRERA WHERE cod_recorrido<=99 AND cod_recorrido>34 AND sexo='M'";
        	rs = ((java.sql.Statement) stmt).executeQuery(VeteranoM);
        	int codigoChup=0;
        	if (rs.next()) {
        		codigoChup = rs.getInt("codigo_carrera");
            }
        	codigoDeCarrera=codigoDeCarrera+codigoChup;
	}

}
