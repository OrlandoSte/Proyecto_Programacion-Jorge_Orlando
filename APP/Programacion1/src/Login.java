import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tUsuario;
	private JPasswordField tContrasena;
	private JButton btnEntrar;
	private JTextField txtContraseaIncorrecta;
	private Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(124, 27, 60, 15);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(96, 62, 88, 15);
		contentPane.add(lblContrasea);

		tUsuario = new JTextField();
		tUsuario.setBounds(202, 25, 114, 19);
		contentPane.add(tUsuario);
		tUsuario.setColumns(10);

		tContrasena = new JPasswordField();
		tContrasena.setBounds(202, 60, 114, 19);
		contentPane.add(tContrasena);
		
		txtContraseaIncorrecta = new JTextField();
		txtContraseaIncorrecta.setVisible(false);
		txtContraseaIncorrecta.setFont(new Font("Dialog", Font.BOLD, 12));
		txtContraseaIncorrecta.setForeground(new Color(224, 27, 36));
		txtContraseaIncorrecta.setText("CREDENCIALES INCORRECTOS!!");
		txtContraseaIncorrecta.setBounds(66, 125, 218, 22);
		contentPane.add(txtContraseaIncorrecta);
		txtContraseaIncorrecta.setColumns(10);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement stm = null;
				String sql = "SELECT NombreUsua, contrasena from admins";
				try {
					stm = con.prepareStatement(sql);
					ResultSet rs = stm.executeQuery();

					
					while(rs.next()) {
						String Nombre=rs.getString("NombreUsua");
						String Contraseña=rs.getString("contrasena");
						String admin=(Nombre + Contraseña);
						String contra=tContrasena.getText();
						
						if(admin.equals(Nombre+hashPassword(contra))) {
							abrirAdmin();
						}
						else {
							txtContraseaIncorrecta.setVisible(true);
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEntrar.setBounds(199, 91, 117, 25);
		contentPane.add(btnEntrar);
	}

	public void abrirAdmin() {
		Admin form = new Admin();
		form.setVisible(true);
		this.dispose();
	}
	public static String hashPassword(String password) {
        try {
            // Crear una instancia de MessageDigest con SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convertir la contraseña a bytes y calcular el hash
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash a una representación hexadecimal
            BigInteger numero = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(numero.toString(16));

            // Rellenar con ceros a la izquierda si es necesario
            while (hexString.length() < 64) {
                hexString.insert(0, '0');
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
