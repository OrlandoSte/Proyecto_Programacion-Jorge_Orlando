import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	public static Connection con;

    public Connection abrirConexion() {
        try {
            String userName = "root";
            String password = "Jorgepiquer76";
            String url = "jdbc:mysql://localhost:3306/proyectoprogramación";
            
            con = DriverManager.getConnection(url, userName, password);
            
            //System.out.println("Conexión establecida con éxito");
            
            
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
		return con;
    }
    
}