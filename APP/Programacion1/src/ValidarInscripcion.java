import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValidarInscripcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValidarInscripcion frame = new ValidarInscripcion();
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
	public ValidarInscripcion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 0, 855, 418);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(603, 430, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCdigoQueQuieres = new JLabel("Código que quieres validar:");
		lblCdigoQueQuieres.setBounds(400, 432, 197, 15);
		contentPane.add(lblCdigoQueQuieres);
		
		JButton btnValidar = new JButton("VALIDAR");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = "bd";
		        String password = "proyecto";
		        String url = "jdbc:mysql://192.168.11.155:3306/ProyectoProgramación";
		        
		        Connection con=null;
		        PreparedStatement pstmt=null;
				try {
					con = DriverManager.getConnection(url, userName, password);
					
					String b=textField.getText();
					String update="UPDATE CORREDOR SET valido='t' where codigo_persona="+b;
					pstmt = con.prepareStatement(update);
					pstmt.executeUpdate();

					textArea.revalidate();
					textArea.repaint();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnValidar.setBounds(739, 430, 117, 25);
		contentPane.add(btnValidar);
		
		String userName = "bd";
        String password = "proyecto";
        String url = "jdbc:mysql://192.168.11.155:3306/ProyectoProgramación";
        
        Connection con;
        
		try {
			con = DriverManager.getConnection(url, userName, password);
			
			java.sql.Statement stmt = con.createStatement();
			java.sql.Statement stmt2 = con.createStatement();
			java.sql.Statement stmt3 = con.createStatement();
			
			String sql1 ="SELECT * FROM PERSONA where codigo_persona in (SELECT codigo_persona FROM CORREDOR where valido='f')";
	        java.sql.ResultSet rs = stmt.executeQuery(sql1);
	        
	        String sql2 ="SELECT * FROM CORREDOR where valido='f'";
	        java.sql.ResultSet rt = stmt2.executeQuery(sql2);
	        String a="";
	        while(rs.next()) { 
		        int var1=rs.getInt(1);
		        String var2=rs.getString(2);
		        String var3=rs.getString(3);
		        String var4=rs.getString(4);
		        String var5=rs.getString(5);
		        String var6=rs.getString(6);
		        String var7=rs.getString(7);
		        String var8=rs.getString(8);
		        String var9=rs.getString(9);
		        String var10=rs.getString(10);
		        String var11=rs.getString(11);
		        
		        String frase="Persona "+var1+"\n"+var1+" "+var2+"  "+var3+"  "+var4+"  "+var5+"  "+var6+"  "+var7+"  "+var8+"  "+var9+"  "+var10+"  "+var11;
		        
	        
	        if(rt.next()) { 
	        int var111=rt.getInt(1);
	        int var112=rt.getInt(2);
	        String var113=rt.getString(3);
	        int var114=rt.getInt(4);
	        int var115=rt.getInt(5);
	        String var116=rt.getString(6);
	        String frase1=var111+" "+var112+"  "+var113+"  "+var114+"  "+var115+"  "+var116;
	        textArea.setText(a+frase+frase1+"\n"+"\n");
	        a=textArea.getText();
	        }}
			
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
