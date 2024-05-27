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

public class AñadirPuesto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tCometido;
	private Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirPuesto frame = new AñadirPuesto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void volver() {
		Admin form = new Admin();
		form.setVisible(true);
		this.dispose();
	}

	/**
	 * Create the frame.
	 */
	public AñadirPuesto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setBounds(83, 59, 60, 15);
		contentPane.add(nombre);

		JLabel Cometido = new JLabel("Cometido:");
		Cometido.setBounds(71, 86, 72, 15);
		contentPane.add(Cometido);

		tNombre = new JTextField();
		tNombre.setBounds(151, 57, 114, 19);
		contentPane.add(tNombre);
		tNombre.setColumns(10);

		tCometido = new JTextField();
		tCometido.setBounds(151, 84, 114, 19);
		contentPane.add(tCometido);
		tCometido.setColumns(10);

		JButton btnAñadirClub = new JButton("Añadir");
		btnAñadirClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					System.out.println("Has introducido el club correctamente");

					PreparedStatement stm = null;

					String insert = "INSERT INTO PUESTO (nombre, cometido) VALUES (?,?)";
					stm = con.prepareStatement(insert);
					stm.setString(1, tNombre.getText());
					stm.setString(2, tCometido.getText());
					stm.executeUpdate();
				}

				catch (SQLException e) {
					System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				}

			}
		});
		btnAñadirClub.setBounds(368, 112, 117, 25);
		contentPane.add(btnAñadirClub);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(368, 12, 117, 25);
		contentPane.add(btnVolver);
	}

}
