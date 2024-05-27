import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tUsuario;
	private JPasswordField tContrasena;
	private JButton btnEntrar;

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
		setBounds(100, 100, 317, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(78, 27, 60, 15);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(50, 62, 88, 15);
		contentPane.add(lblContrasea);

		tUsuario = new JTextField();
		tUsuario.setBounds(147, 25, 114, 19);
		contentPane.add(tUsuario);
		tUsuario.setColumns(10);

		tContrasena = new JPasswordField();
		tContrasena.setBounds(147, 60, 114, 19);
		contentPane.add(tContrasena);

		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tUsuario.getText().equals("Jorge") && tContrasena.getText().equals("Jorge")) {
					abrirAdmin();
				}
				if (tUsuario.getText().equals("Orlo") && tContrasena.getText().equals("Orlo")) {
					abrirAdmin();
				}
			}
		});
		btnEntrar.setBounds(107, 94, 117, 25);
		contentPane.add(btnEntrar);

	}

	public void abrirAdmin() {
		Admin form = new Admin();
		form.setVisible(true);
		this.dispose();
	}
}
