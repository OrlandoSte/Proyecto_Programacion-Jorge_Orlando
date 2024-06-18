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
 * Añade una Patrocinado cogiendo los datos introducidos en el formulario y lo
 * agrega a la base de datos
 */
public class AñadirPatrocinador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tDireccion;
	private JTextField tPcontacto;
	private JTextField tLocalidad;
	private JTextField tCodPostal;
	private JTextField tProvincia;
	private Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	
	 /**
     * Método para volver a la pantalla de administración y cerrar la actual.
     */

	public void volver() {
		Admin form = new Admin();
		form.setVisible(true);
		this.dispose();
	}
	/**
     * Método para abrir la ventana de datos del patrocinador y cerrar la actual.
     */
	public void abrirDatosPatrocinador() {
		DatosPatrocinador form = new DatosPatrocinador();
		form.setVisible(true);
		this.dispose();
	}
	/**
     * Método para actualizar la ventana de añadir patrocinador .
     */
	public void AbrirPatrocinador() {
		AñadirPatrocinador form = new AñadirPatrocinador();
		form.setVisible(true);
		this.dispose();
	}

	/**
	 * Create the frame.
	 */
	public AñadirPatrocinador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(124, 26, 60, 15);
		contentPane.add(nombre);

		JLabel personaContacto = new JLabel("Persona de contacto:");
		personaContacto.setBounds(32, 53, 152, 15);
		contentPane.add(personaContacto);

		JLabel Direccion = new JLabel("Dirección:");
		Direccion.setBounds(114, 80, 70, 15);
		contentPane.add(Direccion);

		JLabel cod_postal = new JLabel("Código Postal:");
		cod_postal.setBounds(81, 134, 103, 15);
		contentPane.add(cod_postal);

		JLabel Localidad = new JLabel("Localidad:");
		Localidad.setBounds(111, 107, 73, 15);
		contentPane.add(Localidad);

		JLabel Provincia = new JLabel("Provincia:");
		Provincia.setBounds(114, 167, 70, 15);
		contentPane.add(Provincia);

		tNombre = new JFormattedTextField();
		tNombre.setBounds(198, 24, 114, 19);
		contentPane.add(tNombre);
		tNombre.setColumns(10);

		tPcontacto = new JTextField();
		tPcontacto.setBounds(198, 78, 114, 19);
		contentPane.add(tPcontacto);
		tPcontacto.setColumns(10);

		tDireccion = new JTextField();
		tDireccion.setBounds(198, 51, 114, 19);
		contentPane.add(tDireccion);
		tDireccion.setColumns(10);

		tCodPostal = new JTextField();
		tCodPostal.setBounds(198, 133, 114, 19);
		contentPane.add(tCodPostal);
		tCodPostal.setColumns(10);

		tLocalidad = new JTextField();
		tLocalidad.setBounds(198, 105, 114, 19);
		contentPane.add(tLocalidad);
		tLocalidad.setColumns(10);

		tProvincia = new JTextField();
		tProvincia.setBounds(198, 165, 114, 19);
		contentPane.add(tProvincia);
		tProvincia.setColumns(10);
		

		JButton btnAñadirClub = new JButton("Añadir");
		btnAñadirClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					System.out.println("Has introducido el club correctamente");

					java.sql.Statement stmt = con.createStatement();
					PreparedStatement stm = null;

					String sql1 = "SELECT codigo_patrocinador FROM PATROCINADOR order by codigo_patrocinador desc limit 1";
					java.sql.ResultSet rs = stmt.executeQuery(sql1);
					int codigo_patr_Ultimo = 0;
					while (rs.next()) {
						codigo_patr_Ultimo = rs.getInt("codigo_patrocinador");
					}

					String insert = "INSERT INTO PATROCINADOR (codigo_patrocinador, nombre, persona_contacto, direccion, codigo_postal, localidad, provincia) VALUES (?,?,?,?,?,?,?)";
					stm = con.prepareStatement(insert);
					stm.setInt(1, codigo_patr_Ultimo + 1);
					stm.setString(2, tNombre.getText());
					stm.setString(3, tPcontacto.getText());
					stm.setString(4, tDireccion.getText());
					stm.setString(5, tCodPostal.getText());
					stm.setString(6, tLocalidad.getText());
					stm.setString(7, tProvincia.getText());
					stm.executeUpdate();
					JsonDatos json = new JsonDatos(conA);
					json.exportarEventoAJson();
					JOptionPane.showMessageDialog(null, "Has añadido datos correctamente!");
					AbrirPatrocinador();
				}

				catch (SQLException e) {
					System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				}

			}
		});
		btnAñadirClub.setBounds(378, 209, 117, 25);
		contentPane.add(btnAñadirClub);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(378, 21, 117, 25);
		contentPane.add(btnVolver);
		
		JButton btnVerPatrocinadores = new JButton("Ver patrocinadores");
		btnVerPatrocinadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirDatosPatrocinador();
			}
		});
		btnVerPatrocinadores.setBounds(180, 209, 171, 25);
		contentPane.add(btnVerPatrocinadores);
	}
}