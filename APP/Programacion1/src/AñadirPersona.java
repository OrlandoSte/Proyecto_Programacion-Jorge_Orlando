import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JRadioButton;

public class AñadirPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tDNI;
	private JTextField tNombre;
	private JTextField tApellidos;
	private JTextField tSexo;
	private JTextField tFechaNacimiento;
	private JTextField tDireccion;
	private JTextField tCodigoPostal;
	private JTextField tLocalidad;
	private JTextField tProvincia;
	private JTextField tTelefono;
	private JComboBox<String> tClub;
	private JComboBox<String> tPuesto;
	private JRadioButton rdbtnTrabajador;
	private JRadioButton rdbtnCorredor;
	private JRadioButton rdbtnVoluntario;
	private JTextField tFInicio;
	private JTextField tFFinal;
	private JLabel lblTrabajadorsiLoHas;
	private JLabel lblFechaContratoInicio;
	private JLabel lblFechaContratoFinal;
	private JLabel lblVoluntariosiLoHas;
	private JLabel lblPuesto_1;
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AñadirPersona frame = new AñadirPersona();
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
	public AñadirPersona() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel DNI = new JLabel("DNI:");
		DNI.setBounds(191, 26, 29, 15);
		contentPane.add(DNI);

		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setBounds(160, 53, 60, 15);
		contentPane.add(Nombre);

		JLabel Apellidos = new JLabel("Apellidos:");
		Apellidos.setBounds(150, 80, 70, 15);
		contentPane.add(Apellidos);

		JLabel Sexo = new JLabel("Sexo(H/M):");
		Sexo.setBounds(142, 107, 78, 15);
		contentPane.add(Sexo);

		JLabel FechaNacimiento = new JLabel("Fecha de nacimiento:");
		FechaNacimiento.setBounds(71, 134, 157, 15);
		contentPane.add(FechaNacimiento);

		JLabel Direccion = new JLabel("Direccion:");
		Direccion.setBounds(150, 161, 70, 15);
		contentPane.add(Direccion);

		JLabel CodigoPostal = new JLabel("Codigo postal:");
		CodigoPostal.setBounds(117, 188, 103, 15);
		contentPane.add(CodigoPostal);

		JLabel Localidad = new JLabel("Localidad:");
		Localidad.setBounds(142, 215, 78, 15);
		contentPane.add(Localidad);

		JLabel Provincia = new JLabel("Provincia:");
		Provincia.setBounds(150, 242, 70, 15);
		contentPane.add(Provincia);

		JLabel Telefono = new JLabel("Número de teléfono:");
		Telefono.setBounds(71, 269, 149, 15);
		contentPane.add(Telefono);

		JLabel Club = new JLabel("Club(opcional):");
		Club.setBounds(109, 296, 111, 15);
		contentPane.add(Club);

		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("########-U");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tDNI = new JFormattedTextField(formatter);
		tDNI.setBounds(230, 24, 114, 19);
		contentPane.add(tDNI);
		tDNI.setColumns(10);

		tNombre = new JTextField();
		tNombre.setBounds(230, 51, 114, 19);
		contentPane.add(tNombre);
		tNombre.setColumns(10);

		tApellidos = new JTextField();
		tApellidos.setBounds(230, 78, 114, 19);
		contentPane.add(tApellidos);
		tApellidos.setColumns(10);

		MaskFormatter formatter2 = null;
		try {
			formatter2 = new MaskFormatter("U");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tSexo = new JFormattedTextField(formatter2);
		tSexo.setBounds(230, 105, 114, 19);
		contentPane.add(tSexo);
		tSexo.setColumns(10);

		MaskFormatter formatter3 = null;
		try {
			formatter3 = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tFechaNacimiento = new JFormattedTextField(formatter3);
		tFechaNacimiento.setBounds(230, 132, 114, 19);
		contentPane.add(tFechaNacimiento);
		tFechaNacimiento.setColumns(10);

		tDireccion = new JTextField();
		tDireccion.setBounds(230, 159, 114, 19);
		contentPane.add(tDireccion);
		tDireccion.setColumns(10);

		tCodigoPostal = new JTextField();
		tCodigoPostal.setBounds(230, 186, 114, 19);
		contentPane.add(tCodigoPostal);
		tCodigoPostal.setColumns(10);

		tLocalidad = new JTextField();
		tLocalidad.setBounds(230, 213, 114, 19);
		contentPane.add(tLocalidad);
		tLocalidad.setColumns(10);

		tProvincia = new JTextField();
		tProvincia.setBounds(230, 240, 114, 19);
		contentPane.add(tProvincia);
		tProvincia.setColumns(10);

		tTelefono = new JTextField();
		tTelefono.setBounds(230, 267, 114, 19);
		contentPane.add(tTelefono);
		tTelefono.setColumns(10);

		tClub = new JComboBox<>();
		tClub.setBounds(230, 294, 114, 19);
		contentPane.add(tClub);

		desplegableClub();

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(587, 21, 117, 25);
		contentPane.add(btnVolver);

		JButton btnInscribirse = new JButton("Inscribirse");
		btnInscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				añadirPersona();
			}
		});
		btnInscribirse.setBounds(587, 450, 117, 25);
		contentPane.add(btnInscribirse);

		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(165, 323, 55, 15);
		contentPane.add(lblPuesto);

		rdbtnTrabajador = new JRadioButton("Trabajador");
		rdbtnTrabajador.setBounds(230, 321, 103, 23);
		contentPane.add(rdbtnTrabajador);

		tFInicio = new JFormattedTextField(formatter3);
		tFInicio.setBounds(255, 379, 114, 19);
		contentPane.add(tFInicio);
		tFInicio.setColumns(10);

		tFFinal = new JFormattedTextField(formatter3);
		tFFinal.setBounds(397, 379, 114, 19);
		contentPane.add(tFFinal);
		tFFinal.setColumns(10);

		lblTrabajadorsiLoHas = new JLabel("Trabajador:(Si lo es)");
		lblTrabajadorsiLoHas.setBounds(71, 352, 149, 15);
		contentPane.add(lblTrabajadorsiLoHas);

		lblFechaContratoInicio = new JLabel("Fecha contrato inicio:");
		lblFechaContratoInicio.setBounds(230, 352, 157, 15);
		contentPane.add(lblFechaContratoInicio);

		lblFechaContratoFinal = new JLabel("Fecha contrato final:");
		lblFechaContratoFinal.setBounds(387, 352, 157, 15);
		contentPane.add(lblFechaContratoFinal);

		rdbtnCorredor = new JRadioButton("Corredor");
		rdbtnCorredor.setBounds(337, 321, 88, 23);
		contentPane.add(rdbtnCorredor);

		rdbtnVoluntario = new JRadioButton("Voluntario");
		rdbtnVoluntario.setBounds(429, 319, 103, 23);
		contentPane.add(rdbtnVoluntario);

		lblVoluntariosiLoHas = new JLabel("Voluntario:(si lo es)");
		lblVoluntariosiLoHas.setBounds(81, 420, 139, 15);
		contentPane.add(lblVoluntariosiLoHas);

		tPuesto = new JComboBox<>();
		tPuesto.setBounds(290, 415, 111, 25);
		contentPane.add(tPuesto);

		desplegablePuesto();

		lblPuesto_1 = new JLabel("Puesto:");
		lblPuesto_1.setBounds(230, 420, 70, 15);
		contentPane.add(lblPuesto_1);
	}

	private void desplegableClub() {
		try {

			String query = "SELECT nombre FROM CLUB";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String nombreClub = rs.getString("nombre");
				tClub.addItem(nombreClub);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}

	private void desplegablePuesto() {
		try {

			String query = "SELECT nombre FROM PUESTO";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String nombrePuesto = rs.getString("nombre");
				tPuesto.addItem(nombrePuesto);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}

	public int calcularEdad(String fechaNacimiento) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);

			LocalDate fechaActual = LocalDate.now();

			Period edad = Period.between(fechaNac, fechaActual);

			return edad.getYears();
		} catch (DateTimeParseException e) {
			System.out.println("La fecha de nacimiento no tiene el formato correcto.");
			return -1;
		}
	}

	public int calcularCarrera() {
		try {

			String query = "SELECT nombre FROM CLUB";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			int codigoChup = 0;
			System.out.println(calcularEdad(tFechaNacimiento.getText()));

			if ((calcularEdad(tFechaNacimiento.getText())) < 5) {
				String Chupetines = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=1";
				rs = ((java.sql.Statement) stmt).executeQuery(Chupetines);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			} else if ((calcularEdad(tFechaNacimiento.getText())) < 9) {
				String Minibenjamines = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=2";
				rs = ((java.sql.Statement) stmt).executeQuery(Minibenjamines);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			} else if ((calcularEdad(tFechaNacimiento.getText())) < 11) {
				String Benjamines = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=3";
				rs = ((java.sql.Statement) stmt).executeQuery(Benjamines);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			} else if ((calcularEdad(tFechaNacimiento.getText())) < 13) {
				String Alevin = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=4";
				rs = ((java.sql.Statement) stmt).executeQuery(Alevin);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			} else if ((calcularEdad(tFechaNacimiento.getText())) < 15) {
				String Infantil = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=5";
				rs = ((java.sql.Statement) stmt).executeQuery(Infantil);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			} else if ((calcularEdad(tFechaNacimiento.getText())) < 17 && tSexo.getText().equals("H")) {
				String Cadete = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=6";
				rs = ((java.sql.Statement) stmt).executeQuery(Cadete);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			}

			else if ((calcularEdad(tFechaNacimiento.getText())) < 17 && tSexo.getText().equals("M")) {
				String Cadete = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=7";
				rs = ((java.sql.Statement) stmt).executeQuery(Cadete);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			}

			else if ((calcularEdad(tFechaNacimiento.getText())) < 20 && tSexo.getText().equals("M")) {
				String Juvenil = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=9";
				rs = ((java.sql.Statement) stmt).executeQuery(Juvenil);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			}

			else if ((calcularEdad(tFechaNacimiento.getText())) < 20 && tSexo.getText().equals("H")) {
				String Juvenil = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=8";
				rs = ((java.sql.Statement) stmt).executeQuery(Juvenil);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			}

			else if ((calcularEdad(tFechaNacimiento.getText())) < 35 && tSexo.getText().equals("M")) {
				String Senior = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=11";
				rs = ((java.sql.Statement) stmt).executeQuery(Senior);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			}

			else if ((calcularEdad(tFechaNacimiento.getText())) < 40 && tSexo.getText().equals("H")) {
				String Senior = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=10";
				rs = ((java.sql.Statement) stmt).executeQuery(Senior);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			}

			else if ((calcularEdad(tFechaNacimiento.getText())) < 99 && tSexo.getText().equals("H")) {
				String VeteranosH = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=12";
				rs = ((java.sql.Statement) stmt).executeQuery(VeteranosH);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			} else if ((calcularEdad(tFechaNacimiento.getText())) < 99 && tSexo.getText().equals("M")) {
				String VeteranosM = "SELECT codigo_carrera FROM CARRERA WHERE cod_categoria=13";
				rs = ((java.sql.Statement) stmt).executeQuery(VeteranosM);

				if (rs.next()) {
					codigoChup = rs.getInt("codigo_carrera");
				}
			}
			return codigoChup;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private void añadirPersona() {

		String primerosOcho = tDNI.getText().substring(0, Math.min(tDNI.getText().length(), 8));
		String letra = tDNI.getText().substring(9, Math.min(tDNI.getText().length(), 10));
		char letra1 = letra.charAt(0);
		int a = Integer.parseInt(primerosOcho);
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int resto = a % 23;
		char letraCalculada = letras.charAt(resto);
		if (letra1 == letraCalculada) {
			desplegablePuesto();
			Statement st;

			try {

				System.out.println("Has realizado la inscripción correctamente");

				java.sql.Statement stmt = con.createStatement();
				PreparedStatement stm = null;
				PreparedStatement stmi = null;
				PreparedStatement stmie = null;
				PreparedStatement stmiep = null;
				PreparedStatement p = null;
				String sql1 = "SELECT codigo_persona FROM PERSONA order by codigo_persona desc limit 1";
				java.sql.ResultSet rs = stmt.executeQuery(sql1);
				int idClienteUltimo = 0;
				while (rs.next()) {
					idClienteUltimo = rs.getInt("codigo_persona");
				}

				String sql2 = "SELECT codigo_club FROM CLUB where nombre='" + tClub.getSelectedItem() + "'";
				rs = ((java.sql.Statement) stmt).executeQuery(sql2);

				int codigoClub = 0;

				if (rs.next()) {
					codigoClub = rs.getInt("codigo_club");
				}

				String insert = "INSERT INTO PERSONA (codigo_persona, DNI, nombre, apellidos, sexo, fecha_nacimiento, direccion, codeigo_postal, localidad, provincia, telefono) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				stm = con.prepareStatement(insert);
				stm.setInt(1, idClienteUltimo + 1);
				stm.setString(2, primerosOcho + letra);
				stm.setString(3, tNombre.getText());
				stm.setString(4, tApellidos.getText());
				stm.setString(5, tSexo.getText());
				stm.setString(6, tFechaNacimiento.getText());
				stm.setString(7, tDireccion.getText());
				stm.setString(8, tCodigoPostal.getText());
				stm.setString(9, tLocalidad.getText());
				stm.setString(10, tProvincia.getText());
				stm.setString(11, tTelefono.getText());
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "Has añadido datos correctamente!");
				if (rdbtnCorredor.isSelected()) {
					calcularCarrera();
					String insert2 = "INSERT INTO CORREDOR (club, dorsal, tiempo, codigo_carrera,codigo_persona,valido) VALUES (?,?,?,?,?,?)";
					stmi = con.prepareStatement(insert2);
					stmi.setInt(1, codigoClub);
					stmi.setInt(2, idClienteUltimo + 1);
					stmi.setString(3, null);
					stmi.setInt(4, calcularCarrera());
					stmi.setInt(5, idClienteUltimo + 1);
					stmi.setString(6, "t");
					stmi.executeUpdate();
				}

				if (rdbtnTrabajador.isSelected()) {
					String insert3 = "INSERT INTO TRABAJADOR (codigo_persona, fecha_contrato_inicio, fecha_contrato_final) VALUES (?,?,?)";
					stmie = con.prepareStatement(insert3);
					stmie.setInt(1, idClienteUltimo + 1);
					stmie.setString(2, tFInicio.getText());
					stmie.setString(3, tFFinal.getText());
					stmie.executeUpdate();
				}
				if (rdbtnVoluntario.isSelected()) {

					String insert4 = "INSERT INTO VOLUNTARIO (codigo_persona) VALUES (?)";
					stmiep = con.prepareStatement(insert4);
					stmiep.setInt(1, idClienteUltimo + 1);
					stmiep.executeUpdate();

					String insert5 = "INSERT INTO DESEMPENA (codigo_persona, nombre_puesto) VALUES (?,?)";
					String Puesto = String.valueOf(tPuesto.getSelectedItem());
					p = con.prepareStatement(insert5);
					p.setInt(1, idClienteUltimo + 1);
					p.setString(2, Puesto);
					p.executeUpdate();
				}
			}

			catch (SQLException e) {
				System.out.println("Error al conectar a la base de datos: " + e.getMessage());
			}
		} else {
			System.out.println("El DNI no es válido. Inténtalo de nuevo.");
		}

	}

}