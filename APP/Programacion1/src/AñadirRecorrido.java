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

public class AñadirRecorrido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tDescripcion;
	private JTextField tkm_totales;
	private JTextField tEstado;
	private JTextField tObservaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirRecorrido frame = new AñadirRecorrido();
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
	public AñadirRecorrido() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Descripcion = new JLabel("Descripcion:");
		Descripcion.setBounds(71, 26, 94, 15);
		contentPane.add(Descripcion);
		
		JLabel km_totales = new JLabel("km_totales:");
		km_totales.setBounds(71, 53, 94, 15);
		contentPane.add(km_totales);
		
		JLabel Estado = new JLabel("Estado:");
		Estado.setBounds(71, 80, 70, 15);
		contentPane.add(Estado);
		
		JLabel Observaciones = new JLabel("Observaciones:");
		Observaciones.setBounds(71, 107, 117, 15);
		contentPane.add(Observaciones);
		
		tDescripcion = new JFormattedTextField();
		tDescripcion.setBounds(165, 24, 114, 19);
		contentPane.add(tDescripcion);
		tDescripcion.setColumns(10);
		
		tkm_totales = new JTextField();
		tkm_totales.setBounds(165, 53, 114, 19);
		contentPane.add(tkm_totales);
		tkm_totales.setColumns(10);
		
		tEstado = new JTextField();
		tEstado.setBounds(151, 78, 114, 19);
		contentPane.add(tEstado);
		tEstado.setColumns(10);
		
		tObservaciones = new JTextField();
		tObservaciones.setBounds(190, 105, 114, 19);
		contentPane.add(tObservaciones);
		tObservaciones.setColumns(10);
		

		JButton btnAñadirRecorrido = new JButton("Añadir Recorrido");
		btnAñadirRecorrido.addActionListener(new ActionListener() {
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
				            
				            String sql1 ="SELECT codigo_recorrido FROM RECORRIDO order by codigo_recorrido desc limit 1";
				            java.sql.ResultSet rs = stmt.executeQuery(sql1);
				            int codigo_recorrido_Ultimo=0;
				            while(rs.next()) {
				            	codigo_recorrido_Ultimo = rs.getInt("codigo_recorrido");
				            }
				            
				            String insert = "INSERT INTO RECORRIDO (codigo_recorrido, descripcion, km_totales, estado, observaciones) VALUES (?,?,?,?,?)";
				            stm = con.prepareStatement(insert);
				            stm.setInt(1, codigo_recorrido_Ultimo+1);
				            stm.setString(2, tDescripcion.getText());
				            stm.setString(3, tkm_totales.getText());
				            stm.setString(4, tEstado.getText());
				            stm.setString(5, tObservaciones.getText());
				            stm.executeUpdate();
				        } 
				        
				        		
					        
				 
				        catch (SQLException e) {
				            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				        }
		        
		        
			
			}});
		btnAñadirRecorrido.setBounds(363, 285, 152, 25);
		contentPane.add(btnAñadirRecorrido);
		
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
