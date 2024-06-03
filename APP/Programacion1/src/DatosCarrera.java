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

public class DatosCarrera extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField_1;
	private Conexion conA = new Conexion();
	private Connection con = conA.abrirConexion();

	public void volver() {
		AñadirCarrera form = new AñadirCarrera();
		form.setVisible(true);
		this.dispose();
	}
	

	public DatosCarrera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 0, 855, 418);
		contentPane.add(scrollPane);

		tableModel = new DefaultTableModel(new Object[] { "Código", "Detalles Corredor"}, 0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(321, 429, 229, 27);
		contentPane.add(btnVolver);

		loadTableData();
	}

	private void loadTableData() {
		try {

			java.sql.Statement stmt = con.createStatement();
			
			String sql1 = "SELECT * FROM CARRERA";
			ResultSet rs = stmt.executeQuery(sql1);



			tableModel.setRowCount(0); // Clear existing data

			while (rs.next()) {
				int var1 = rs.getInt(1);
				String var2 = rs.getString(2);
				String var3 = rs.getString(3);
				String var4 = rs.getString(4);
				int var5 = rs.getInt(5);
				int var6 = rs.getInt(6);
				
				String personaDetails = var2 + "  " + var3 + "  " + var4 + "  " + var5 + "  " + var6;


				tableModel.addRow(new Object[] {var1, personaDetails});
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
