import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class AñadirAportacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tConcepto;
	private JTextField tCantidad;
	private JTextField tTipo_aportacion;
	private JComboBox<String> tPatrocinador;
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
	public AñadirAportacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Concepto = new JLabel("Concepto:");
		Concepto.setBounds(78, 26, 72, 15);
		contentPane.add(Concepto);

		JLabel Cantidad = new JLabel("Cantidad:");
		Cantidad.setBounds(81, 53, 69, 15);
		contentPane.add(Cantidad);

		JLabel aportación = new JLabel("Tipo de aportación:");
		aportación.setBounds(12, 80, 138, 15);
		contentPane.add(aportación);

		JLabel patrocinador = new JLabel("Patrocinador:");
		patrocinador.setBounds(52, 107, 184, 15);
		contentPane.add(patrocinador);

		tConcepto = new JTextField();
		tConcepto.setBounds(161, 24, 114, 19);
		contentPane.add(tConcepto);
		tConcepto.setColumns(10);

		tCantidad = new JTextField();
		tCantidad.setBounds(161, 51, 114, 19);
		contentPane.add(tCantidad);
		tCantidad.setColumns(10);

		tTipo_aportacion = new JTextField();
		tTipo_aportacion.setBounds(161, 78, 114, 19);
		contentPane.add(tTipo_aportacion);
		tTipo_aportacion.setColumns(10);

		tPatrocinador = new JComboBox<>();
		tPatrocinador.setBounds(161, 105, 114, 19);
		contentPane.add(tPatrocinador);

		desplegablePatrocinador();

		JButton btnAnadirAport = new JButton("Añadir");
		btnAnadirAport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					System.out.println("Has introducido una aportacion correctamente");

					java.sql.Statement stmt = con.createStatement();
					PreparedStatement stm = null;

					String sql2 = "SELECT codigo_patrocinador FROM PATROCINADOR where nombre='"
							+ tPatrocinador.getSelectedItem() + "'";
					
					java.sql.ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql2);

					int codigoPatrocinador = 0;

					if (rs.next()) {
						codigoPatrocinador = rs.getInt("codigo_patrocinador");
					}

					String sql1 = "SELECT codigo_aportacion FROM APORTACION order by codigo_aportacion desc limit 1";
					rs = stmt.executeQuery(sql1);
					int codigo_aport_Ultimo = 0;
					while (rs.next()) {
						codigo_aport_Ultimo = rs.getInt("codigo_aportacion");
					}
					String insert = "INSERT INTO APORTACION (codigo_aportacion, concepto, cantidad, tipo_aportacion, codigo_patrocinador) VALUES (?,?,?,?,?)";
					stm = con.prepareStatement(insert);
					stm.setInt(1, codigo_aport_Ultimo + 1);
					stm.setString(2, tConcepto.getText());
					stm.setString(3, tCantidad.getText());
					stm.setString(4, tTipo_aportacion.getText());
					stm.setInt(5, codigoPatrocinador);
					stm.executeUpdate();
					JOptionPane.showMessageDialog(null, "Has añadido datos correctamente!");
				}

				catch (SQLException e) {
					System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				}

			}
		});
		btnAnadirAport.setBounds(378, 153, 117, 25);
		contentPane.add(btnAnadirAport);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(378, 21, 117, 25);
		contentPane.add(btnVolver);
	}

	private void desplegablePatrocinador() {
		try {

			String query = "SELECT nombre FROM PATROCINADOR";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String nombrePatrocinador = rs.getString("nombre");
				tPatrocinador.addItem(nombrePatrocinador);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}

}