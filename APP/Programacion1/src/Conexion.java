/**
 * @author Jorge Piquer Samper
 * @author Orlando Stefan Ionus
 * 
 * @version 1.0
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Clase que gestiona la conexión a una base de datos MySQL.
 *
 */
public class Conexion {

	public static Connection con;
	 
	 /**	
     * Abre una conexión a la base de datos MySQL.
     * 
     * @return la conexión a la base de datos.
     */
    public Connection abrirConexion() {
        try {
            String userName = "orlo";
            String password = "Admin@18";
            String url = "jdbc:mysql://localhost:3306/proyectoprogramación2";
            
            con = DriverManager.getConnection(url, userName, password);
            
            //System.out.println("Conexión establecida con éxito");
            
            
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
		return con;
    }
    
}