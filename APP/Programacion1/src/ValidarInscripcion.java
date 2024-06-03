import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

public class ValidarInscripcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField_1;
	private Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	/**
	 * Launch the application.
	 */

	public void abrirValidacion() {
		ValidarInscripcion form=new ValidarInscripcion();
		form.setVisible(true);
		this.dispose();
	}

	public void abrirAdmin() {
		Admin form = new Admin();
		form.setVisible(true);
		this.dispose();
	}

	/**
	 * Create the frame.
	 */
	public ValidarInscripcion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 0, 855, 418);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new Object[] { "Código", "Detalles Persona", "Detalles Corredor" }, 0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setBounds(581, 438, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblCdigoQueQuieres = new JLabel("Código para validar:");
		lblCdigoQueQuieres.setBounds(563, 421, 143, 15);
		contentPane.add(lblCdigoQueQuieres);

		JButton btnValidar = new JButton("VALIDAR");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PreparedStatement pstmt = null;
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Escribe un codigo existente para validarlo");
				}
				else {
				try {
					String b = textField.getText();
					String update = "UPDATE CORREDOR SET valido='t' where codigo_persona=" + b;
					pstmt = con.prepareStatement(update);
					pstmt.executeUpdate();

					dispose();
					abrirValidacion();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}}
		});

		btnValidar.setBounds(439, 430, 117, 25);
		contentPane.add(btnValidar);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirAdmin();
			}
		});
		btnVolver.setBounds(12, 430, 104, 25);
		contentPane.add(btnVolver);

		JButton btnActualizar = new JButton("BORRAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PreparedStatement pstmt = null;

				if(textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Escribe un código valido para eliminarlo");
				}
				else {
				try {

					String b = textField_1.getText();
					String update = "DELETE FROM CORREDOR where codigo_persona=" + b;
					pstmt = con.prepareStatement(update);
					pstmt.executeUpdate();
					
					String b1 = textField_1.getText();
					String update1 = "DELETE FROM PERSONA where codigo_persona=" + b1;
					pstmt = con.prepareStatement(update1);
					pstmt.executeUpdate();
					
					dispose();
					abrirValidacion();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}
		});
		btnActualizar.setBounds(128, 430, 134, 25);
		contentPane.add(btnActualizar);

		textField_1 = new JTextField();
		textField_1.setBounds(288, 438, 104, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblCdigoParaBorrar = new JLabel("Código para borrar:");
		lblCdigoParaBorrar.setBounds(268, 421, 153, 15);
		contentPane.add(lblCdigoParaBorrar);

		loadTableData();
	}

	private void loadTableData() {
		try {

			java.sql.Statement stmt = con.createStatement();
			java.sql.Statement stmt2 = con.createStatement();

			String sql1 = "SELECT * FROM PERSONA where codigo_persona in (SELECT codigo_persona FROM CORREDOR where valido='f')";
			ResultSet rs = stmt.executeQuery(sql1);

			String sql2 = "SELECT * FROM CORREDOR where valido='f'";
			ResultSet rt = stmt2.executeQuery(sql2);

			tableModel.setRowCount(0); // Clear existing data

			while (rt.next() && rs.next()) {
				int var1 = rs.getInt(1);
				String var2 = rs.getString(2);
				String var3 = rs.getString(3);
				String var4 = rs.getString(4);
				String var5 = rs.getString(5);
				String var6 = rs.getString(6);
				String var7 = rs.getString(7);
				String var8 = rs.getString(8);
				String var9 = rs.getString(9);
				String var10 = rs.getString(10);
				String var11 = rs.getString(11);

				String personaDetails = var2 + "  " + var3 + "  " + var4 + "  " + var5 + "  " + var6
						+ "  " + var7 + "  " + var8 + "  " + var9 + "  " + var10 + "  " + var11;

				int var111 = rt.getInt(1);
				int var112 = rt.getInt(2);
				String var113 = rt.getString(3);
				int var114 = rt.getInt(4);
				int var115 = rt.getInt(5);
				String var116 = rt.getString(6);

				String corredorDetails = var111 + " " + var112 + " " + var113 + " " + var114 + " " + var115 + " "
						+ var116;

				tableModel.addRow(new Object[] {var1, personaDetails, corredorDetails });
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
