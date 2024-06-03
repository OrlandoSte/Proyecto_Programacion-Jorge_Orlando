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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class AñadirCategoria extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tEdad;
    private Conexion conA = new Conexion();
    private Connection con = conA.abrirConexion();

	/**
	 * Launch the application.
	 */


	public void volver() {
		Admin form = new Admin();
		form.setVisible(true);
		this.dispose();
	}

	/**
	 * Create the frame.
	 */
	public AñadirCategoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setBounds(71, 26, 60, 15);
		contentPane.add(Nombre);

		JLabel Edad = new JLabel("Edad Min/Max:");
		Edad.setBounds(30, 70, 101, 15);
		contentPane.add(Edad);

		tNombre = new JFormattedTextField();
		tNombre.setBounds(149, 24, 114, 19);
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
		tEdad.setBounds(149, 68, 114, 19);
		contentPane.add(tEdad);
		tEdad.setColumns(10);

		JButton btnAñadirCategoria = new JButton("Añadir");
		btnAñadirCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					java.sql.Statement stmt = con.createStatement();
					PreparedStatement stm = null;

					String sql1 = "SELECT codigo_categoria FROM CATEGORIA order by codigo_categoria desc limit 1";
					java.sql.ResultSet rs = stmt.executeQuery(sql1);
					int codigo_recorrido_Ultimo = 0;
					while (rs.next()) {
						codigo_recorrido_Ultimo = rs.getInt("codigo_categoria");
					}

					String insert = "INSERT INTO CATEGORIA (codigo_categoria, nombre, edad) VALUES (?,?,?)";
					stm = con.prepareStatement(insert);
					stm.setInt(1, codigo_recorrido_Ultimo + 1);
					stm.setString(2, tNombre.getText());
					stm.setString(3, tEdad.getText());
					stm.executeUpdate();
					JOptionPane.showMessageDialog(null, "Has añadido datos correctamente!");
				}

				catch (SQLException e) {
					System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				}

			}
		});
		btnAñadirCategoria.setBounds(355, 65, 117, 25);
		contentPane.add(btnAñadirCategoria);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(355, 16, 117, 25);
		contentPane.add(btnVolver);
	}

}