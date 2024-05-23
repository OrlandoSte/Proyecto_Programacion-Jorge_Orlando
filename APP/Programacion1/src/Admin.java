import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void abrirPatrocinador() {
		AñadirPatrocinador form=new AñadirPatrocinador();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirCarrera() {
		AñadirCarrera form=new AñadirCarrera();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirCategoria() {
		AñadirCategoria form=new AñadirCategoria();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirClub() {
		AñadirClub form=new AñadirClub();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirRecorrido() {
		AñadirRecorrido form=new AñadirRecorrido();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirPersona() {
		AñadirPErsona form=new AñadirPErsona();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirPuesto() {
		AñadirPuesto form=new AñadirPuesto();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirAportacion() {
		AñadirAportacion form=new AñadirAportacion();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirInicio() {
		ProyectoProgramacion form=new ProyectoProgramacion();
		form.setVisible(true);
		this.dispose();
	}
	public void abrirValidacion() {
		ValidarInscripcion form=new ValidarInscripcion();
		form.setVisible(true);
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAadirAportacion = new JButton("Añadir Aportacion");
		btnAadirAportacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirAportacion();
			}
		});
		btnAadirAportacion.setBounds(106, 20, 200, 25);
		contentPane.add(btnAadirAportacion);
		
		JButton btnAadirAportacion_1 = new JButton("Añadir Patrocinador");
		btnAadirAportacion_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirPatrocinador();
			}
		});
		btnAadirAportacion_1.setBounds(106, 50, 200, 25);
		contentPane.add(btnAadirAportacion_1);
		
		JButton btnAadirAportacion_2 = new JButton("Añadir Carrera");
		btnAadirAportacion_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirCarrera();
			}
		});
		btnAadirAportacion_2.setBounds(106, 80, 200, 25);
		contentPane.add(btnAadirAportacion_2);
		
		JButton btnAadirAportacion_3 = new JButton("Añadir Categoria");
		btnAadirAportacion_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirCategoria();
			}
		});
		btnAadirAportacion_3.setBounds(106, 110, 200, 25);
		contentPane.add(btnAadirAportacion_3);
		
		JButton btnAadirAportacion_4 = new JButton("Añadir Club");
		btnAadirAportacion_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirClub();
			}
		});
		btnAadirAportacion_4.setBounds(106, 140, 200, 25);
		contentPane.add(btnAadirAportacion_4);
		
		JButton btnAadirAportacion_5 = new JButton("Añadir Recorrido");
		btnAadirAportacion_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirRecorrido();
			}
		});
		btnAadirAportacion_5.setBounds(106, 170, 200, 25);
		contentPane.add(btnAadirAportacion_5);
		
		JButton btnAadirAportacion_6 = new JButton("Añadir Persona");
		btnAadirAportacion_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirPersona();
			}
		});
		btnAadirAportacion_6.setBounds(106, 200, 200, 25);
		contentPane.add(btnAadirAportacion_6);
		
		JButton btnAadirAportacion_7 = new JButton("Añadir Puesto");
		btnAadirAportacion_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirPuesto();
			}
		});
		btnAadirAportacion_7.setBounds(106, 230, 200, 25);
		contentPane.add(btnAadirAportacion_7);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInicio();
			}
		});
		btnVolver.setBounds(341, 22, 87, 25);
		contentPane.add(btnVolver);
		
		JButton btnAadirAportacion_8 = new JButton("Validar Inscripcion");
		btnAadirAportacion_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirValidacion();
			}
		});
		btnAadirAportacion_8.setBounds(106, 260, 200, 25);
		contentPane.add(btnAadirAportacion_8);
	}
}
