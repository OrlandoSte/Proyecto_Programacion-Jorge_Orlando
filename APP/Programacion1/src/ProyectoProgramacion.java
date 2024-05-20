import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ProyectoProgramacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProyectoProgramacion frame = new ProyectoProgramacion();
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
	
	public void abrirInscribirse() {
		Inscribirse form=new Inscribirse();
		form.setVisible(true);
		this.dispose();
	}
	/*public void abrirInformacion() {
		Informacion form2=new Informacion();
		form2.setVisible(true);
		this.dispose();
	}*/
	
	public ProyectoProgramacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Informacion = new JButton("Informacion");
		Informacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Informacion.setBounds(163, 71, 117, 25);
		contentPane.add(Informacion);
		
		JButton INSCRIBIRSE = new JButton("Inscribirse");
		INSCRIBIRSE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInscribirse();
			}
		});
		INSCRIBIRSE.setBounds(163, 130, 117, 25);
		contentPane.add(INSCRIBIRSE);
	}
}
