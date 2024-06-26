
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.cj.xdevapi.Statement;

/**
 * Añade una carrera cogiendo los datos introducidos en el formulario y lo
 * agrega a la base de datos
 */
public class AñadirCarrera extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tSexo;
	private JTextField tHoraComienzo;
	private JTextField tRegla;
	private JComboBox<String> tCodCategoria;
	private JComboBox<Integer> tCodRecorrido;
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
	/**
	 * Abre la ventana de DatosCarrera()
	 */
	public void abrirDatosCarrera() {
		DatosCarrera form = new DatosCarrera();
		form.setVisible(true);
		this.dispose();
	}

	public AñadirCarrera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Sexo = new JLabel("Sexo(H/M):");
		Sexo.setBounds(133, 26, 75, 15);
		contentPane.add(Sexo);

		JLabel HoraComienzo = new JLabel("Hora de comienzo:");
		HoraComienzo.setBounds(76, 53, 132, 15);
		contentPane.add(HoraComienzo);

		JLabel Regla = new JLabel("Reglas de la carrera:");
		Regla.setBounds(60, 80, 148, 15);
		contentPane.add(Regla);

		JLabel CodCategoria = new JLabel("Código de la categoría:");
		CodCategoria.setBounds(43, 107, 165, 15);
		contentPane.add(CodCategoria);

		JLabel CodRecorrido = new JLabel("Código del recorrido:");
		CodRecorrido.setBounds(54, 134, 154, 15);
		contentPane.add(CodRecorrido);

		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("U");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tSexo = new JFormattedTextField(formatter);
		tSexo.setBounds(215, 24, 114, 19);
		contentPane.add(tSexo);
		tSexo.setColumns(10);

		MaskFormatter hora = null;
		try {
			hora = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tHoraComienzo = new JFormattedTextField(hora);
		tHoraComienzo.setBounds(215, 51, 114, 19);
		contentPane.add(tHoraComienzo);
		tHoraComienzo.setColumns(10);

		tRegla = new JTextField();
		tRegla.setBounds(215, 78, 114, 19);
		contentPane.add(tRegla);
		tRegla.setColumns(10);

		tCodCategoria = new JComboBox<>();
		tCodCategoria.setBounds(215, 105, 114, 19);
		contentPane.add(tCodCategoria);

		tCodRecorrido = new JComboBox<>();
		tCodRecorrido.setBounds(215, 132, 114, 19);
		contentPane.add(tCodRecorrido);

		// Poblar el JComboBox con los códigos de la categoría
		desplegableCategoria();
		desplegableRecorrido();

		JButton btnAñadirCarrera = new JButton("Añadir");
		btnAñadirCarrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JsonDatos json = new JsonDatos(conA);
				json.exportarEventoAJson();
				añadirCarrera();
			}
		});
		btnAñadirCarrera.setBounds(378, 178, 117, 25);
		contentPane.add(btnAñadirCarrera);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(378, 21, 117, 25);
		contentPane.add(btnVolver);

		JButton btnVerPatrocinadores = new JButton("Ver carreras");
		btnVerPatrocinadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirDatosCarrera();
			}
		});
		btnVerPatrocinadores.setBounds(180, 209, 171, 25);
		contentPane.add(btnVerPatrocinadores);
	}
	/**
	 * Genera una lista de los CATEGORIAS que tenemos apuntados en la base de datos
	 */
	private void desplegableCategoria() {
		try {

			String query = "SELECT nombre FROM CATEGORIA";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String codigoCategoria = rs.getString("nombre");
				tCodCategoria.addItem(codigoCategoria);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}
	/**
	 * Genera una lista de los RECORRIDOS que tenemos apuntados en la base de datos
	 */
	private void desplegableRecorrido() {
		try {

			String query = "SELECT codigo_recorrido FROM RECORRIDO";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Integer codigoRecorrido = rs.getInt("codigo_recorrido");
				tCodRecorrido.addItem(codigoRecorrido);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}
/**
 * Agrega una carreca cogiendo los datos pasados atraves del formulario
 */
	private void añadirCarrera() {
		try {

			System.out.println("Has introducido el club correctamente");

			java.sql.Statement stmt = con.createStatement();

			String sql1 = "SELECT codigo_carrera FROM CARRERA ORDER BY codigo_carrera DESC LIMIT 1";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql1);

			int codigoCarreraUltimo = 0;

			if (rs.next()) {
				codigoCarreraUltimo = rs.getInt("codigo_carrera");
			}

			String sql2 = "SELECT codigo_categoria FROM CATEGORIA where nombre='" + tCodCategoria.getSelectedItem()
					+ "'";
			rs = ((java.sql.Statement) stmt).executeQuery(sql2);

			int codigoCat = 0;

			if (rs.next()) {
				codigoCat = rs.getInt("codigo_categoria");
			}

			String insert = "INSERT INTO CARRERA (codigo_carrera, sexo, hora_comienzo, regla, cod_categoria, cod_recorrido) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(insert);
			stm.setInt(1, codigoCarreraUltimo + 1);
			stm.setString(2, tSexo.getText());
			stm.setString(3, tHoraComienzo.getText());
			stm.setString(4, tRegla.getText());
			stm.setInt(5, codigoCat);
			stm.setInt(6, (int) tCodRecorrido.getSelectedItem());
			stm.executeUpdate();

			rs.close();
			stmt.close();
			stm.close();
			JOptionPane.showMessageDialog(null, "Has añadido datos correctamente!");
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}
}