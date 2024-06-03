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

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.Statement;

public class CorredoresActivos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField_1;
	private Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	/**
	 * Create the frame.
	 */
	public CorredoresActivos() {
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

		loadTableData();
	}

	private void loadTableData() {
		try {

			java.sql.Statement stmt = con.createStatement();
			java.sql.Statement stmt2 = con.createStatement();

			String sql1 = "SELECT * FROM PERSONA where codigo_persona in (SELECT codigo_persona FROM CORREDOR where valido='t')";
			ResultSet rs = stmt.executeQuery(sql1);

			String sql2 = "SELECT * FROM CORREDOR where valido='t'";
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

