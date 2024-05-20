import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public static Connection con;
    public static Statement st;
	public static ResultSet rs;

    public static void main(String[] args) {
        abrirConexion();
    }

    public static void abrirConexion() {
        try {
            String userName = "bd";
            String password = "proyecto";
            String url = "jdbc:mysql://192.168.11.155:3306/Ejemplo1";
            
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexión establecida con éxito");
            
            java.sql.Statement stmt = con.createStatement();
            PreparedStatement stm = null;
           // String sql ="SELECT * FROM Clientes";
           // java.sql.ResultSet rs = stmt.executeQuery(sql);
            String sql1 ="SELECT ClienteID FROM Clientes order by ClienteID desc limit 1";
            java.sql.ResultSet rs = stmt.executeQuery(sql1);
            int idClienteUltimo=0;
            while(rs.next()) {
            	idClienteUltimo = rs.getInt("ClienteID");
            }
            
            
            int sCliente=4;
            String sNombre="Jorge";
            String sEmail="Jorgeksp76@gmail.com";
            
            String insert = "INSERT INTO Clientes (ClienteID, Nombre, Email) VALUES (?,?,?)";
            stm = con.prepareStatement(insert);
            stm.setInt(1, idClienteUltimo+1);
            stm.setString(2, sNombre);
            stm.setString(3, sEmail);
            int retorno = stm.executeUpdate();
            
            /*while(rs.next()) {
            	int idCliente = rs.getInt("ClienteID");
            	String Nombre = rs.getString("Nombre");
            	String Email = rs.getString("Email");
            	System.out.println(idCliente+" "+Nombre+" "+Email);
            }*/
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}