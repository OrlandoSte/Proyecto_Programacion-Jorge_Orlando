/**
 * @author Jorge Piquer Samper
 * @author Orlando Stefan Ionus
 * 
 * @version 1.0
 * 
 */
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

/**
 * Añade una Club cogiendo los datos introducidos en el formulario y lo
 * agrega a la base de datos
 */
public class AñadirClub extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tDireccion;
	private JTextField tTelefono;
	private JTextField tResponsable;
	private Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	/**
	 * 
	 * Vuelve atras de la pagina en la que se encuentra que en este caso volveria a
	 * Admin()
	 * 
	 */

	public void volver() {
		Admin form = new Admin();
		form.setVisible(true);
		this.dispose();
	}

	public AñadirClub() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(108, 26, 60, 15);
		contentPane.add(nombre);

		JLabel Direccion = new JLabel("Dirección:");
		Direccion.setBounds(98, 53, 70, 15);
		contentPane.add(Direccion);

		JLabel Telefono = new JLabel("Teléfono:");
		Telefono.setBounds(101, 80, 67, 15);
		contentPane.add(Telefono);

		JLabel Responsable = new JLabel("Responsable:");
		Responsable.setBounds(71, 107, 97, 15);
		contentPane.add(Responsable);

		tNombre = new JFormattedTextField();
		tNombre.setBounds(180, 24, 114, 19);
		contentPane.add(tNombre);
		tNombre.setColumns(10);

		tDireccion = new JTextField();
		tDireccion.setBounds(180, 51, 114, 19);
		contentPane.add(tDireccion);
		tDireccion.setColumns(10);

		tTelefono = new JTextField();
		tTelefono.setBounds(180, 78, 114, 19);
		contentPane.add(tTelefono);
		tTelefono.setColumns(10);

		tResponsable = new JTextField();
		tResponsable.setBounds(180, 105, 114, 19);
		contentPane.add(tResponsable);
		tResponsable.setColumns(10);

		JButton btnAñadirClub = new JButton("Añadir");
		btnAñadirClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					System.out.println("Has introducido el club correctamente");

					java.sql.Statement stmt = con.createStatement();
					PreparedStatement stm = null;

					String sql1 = "SELECT codigo_club FROM CLUB order by codigo_club desc limit 1";
					java.sql.ResultSet rs = stmt.executeQuery(sql1);
					int codigo_club_Ultimo = 0;
					while (rs.next()) {
						codigo_club_Ultimo = rs.getInt("codigo_club");
					}

					String insert = "INSERT INTO CLUB (codigo_club, nombre, dirección, teléfono, responsable) VALUES (?,?,?,?,?)";
					stm = con.prepareStatement(insert);
					stm.setInt(1, codigo_club_Ultimo + 1);
					stm.setString(2, tNombre.getText());
					stm.setString(3, tDireccion.getText());
					stm.setString(4, tTelefono.getText());
					stm.setString(5, tResponsable.getText());
					stm.executeUpdate();
					JsonDatos json = new JsonDatos(conA);
					json.exportarEventoAJson();
					JOptionPane.showMessageDialog(null, "Has añadido datos correctamente!");
				}

				catch (SQLException e) {
					System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				}}

			
		});
		btnAñadirClub.setBounds(378, 102, 117, 25);
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
