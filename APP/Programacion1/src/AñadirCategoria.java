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

public class AñadirCategoria extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirCategoria frame = new AñadirCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void volver() {
		Admin form=new Admin();
		form.setVisible(true);
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public AñadirCategoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setBounds(71, 26, 94, 15);
		contentPane.add(Nombre);
		
		JLabel Edad = new JLabel("Edad:");
		Edad.setBounds(71, 53, 94, 15);
		contentPane.add(Edad);
		
		tNombre = new JFormattedTextField();
		tNombre.setBounds(165, 24, 114, 19);
		contentPane.add(tNombre);
		tNombre.setColumns(10);
		
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("##-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tEdad = new JFormattedTextField(formatter);
		tEdad.setBounds(165, 53, 114, 19);
		contentPane.add(tEdad);
		tEdad.setColumns(10);
		

		JButton btnAñadirCategoria = new JButton("Añadir Categoría");
		btnAñadirCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        	Connection con;
				  
					


				    
				        try {
				            String userName = "bd";
				            String password = "proyecto";
				            String url = "jdbc:mysql://192.168.11.155:3306/ProyectoProgramación";
				            
				            con = DriverManager.getConnection(url, userName, password);
				            System.out.println("Has introducido el recorrido correctamente");
				            
				            java.sql.Statement stmt = con.createStatement();
				            PreparedStatement stm = null;
				            
				            String sql1 ="SELECT codigo_categoria FROM CATEGORIA order by codigo_categoria desc limit 1";
				            java.sql.ResultSet rs = stmt.executeQuery(sql1);
				            int codigo_recorrido_Ultimo=0;
				            while(rs.next()) {
				            	codigo_recorrido_Ultimo = rs.getInt("codigo_categoria");
				            }
				            
				            String insert = "INSERT INTO CATEGORIA (codigo_categoria, nombre, edad) VALUES (?,?,?)";
				            stm = con.prepareStatement(insert);
				            stm.setInt(1, codigo_recorrido_Ultimo+1);
				            stm.setString(2, tNombre.getText());
				            stm.setString(3, tEdad.getText());
				            stm.executeUpdate();
				        } 
				        
				        		
					        
				 
				        catch (SQLException e) {
				            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				        }
		        
		        
			
			}});
		btnAñadirCategoria.setBounds(363, 285, 152, 25);
		contentPane.add(btnAñadirCategoria);
		
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