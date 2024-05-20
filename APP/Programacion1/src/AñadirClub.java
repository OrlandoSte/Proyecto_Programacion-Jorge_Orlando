import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class AñadirClub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tDireccion;
	private JTextField tTelefono;
	private JTextField tResponsable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirClub frame = new AñadirClub();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void volver() {
		ProyectoProgramacion form=new ProyectoProgramacion();
		form.setVisible(true);
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public AñadirClub() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(71, 26, 70, 15);
		contentPane.add(nombre);
		
		JLabel Direccion = new JLabel("Dirección:");
		Direccion.setBounds(71, 53, 70, 15);
		contentPane.add(Direccion);
		
		JLabel Telefono = new JLabel("Teléfono:");
		Telefono.setBounds(71, 80, 70, 15);
		contentPane.add(Telefono);
		
		JLabel Responsable = new JLabel("Responsable:");
		Responsable.setBounds(71, 107, 105, 15);
		contentPane.add(Responsable);
		
		tNombre = new JFormattedTextField();
		tNombre.setBounds(141, 24, 114, 19);
		contentPane.add(tNombre);
		tNombre.setColumns(10);
		
		tDireccion = new JTextField();
		tDireccion.setBounds(151, 51, 114, 19);
		contentPane.add(tDireccion);
		tDireccion.setColumns(10);
		
		tTelefono = new JTextField();
		tTelefono.setBounds(151, 78, 114, 19);
		contentPane.add(tTelefono);
		tTelefono.setColumns(10);
		
		tResponsable = new JTextField();
		tResponsable.setBounds(180, 105, 114, 19);
		contentPane.add(tResponsable);
		tResponsable.setColumns(10);
		

		JButton btnAñadirClub = new JButton("Añadir Club");
		btnAñadirClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        	Connection con;
				  
					


				    
				        try {
				            String userName = "bd";
				            String password = "proyecto";
				            String url = "jdbc:mysql://192.168.11.155:3306/ProyectoProgramación";
				            
				            con = DriverManager.getConnection(url, userName, password);
				            System.out.println("Has introducido el club correctamente");
				            
				            java.sql.Statement stmt = con.createStatement();
				            PreparedStatement stm = null;
				            
				            String sql1 ="SELECT codigo_club FROM CLUB order by codigo_club desc limit 1";
				            java.sql.ResultSet rs = stmt.executeQuery(sql1);
				            int codigo_club_Ultimo=0;
				            while(rs.next()) {
				            	codigo_club_Ultimo = rs.getInt("codigo_club");
				            }
				            
				            String insert = "INSERT INTO CLUB (codigo_club, nombre, dirección, teléfono, responsable) VALUES (?,?,?,?,?)";
				            stm = con.prepareStatement(insert);
				            stm.setInt(1, codigo_club_Ultimo+1);
				            stm.setString(2, tNombre.getText());
				            stm.setString(3, tDireccion.getText());
				            stm.setString(4, tTelefono.getText());
				            stm.setString(5, tResponsable.getText());
				            stm.executeUpdate();
				        } 
				        
				        		
					        
				 
				        catch (SQLException e) {
				            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				        }
		        
		        
			
			}});
		btnAñadirClub.setBounds(378, 291, 117, 25);
		contentPane.add(btnAñadirClub);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(378, 21, 117, 25);
		contentPane.add(btnVolver);
	}

}
