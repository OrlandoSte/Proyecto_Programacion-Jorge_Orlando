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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.cj.xdevapi.Statement;

public class AñadirCarrera extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tSexo;
    private JTextField tHoraComienzo;
    private JTextField tRegla;
    private JComboBox<String> tCodCategoria;
    private JComboBox<Integer> tCodRecorrido;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	AñadirCarrera frame = new AñadirCarrera();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void volver() {
        ProyectoProgramacion form = new ProyectoProgramacion();
        form.setVisible(true);
        this.dispose();
    }

    public AñadirCarrera() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 527, 415);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Sexo = new JLabel("Sexo(H/M):");
        Sexo.setBounds(71, 26, 82, 15);
        contentPane.add(Sexo);

        JLabel HoraComienzo = new JLabel("Hora de comienzo:");
        HoraComienzo.setBounds(71, 53, 137, 15);
        contentPane.add(HoraComienzo);

        JLabel Regla = new JLabel("Reglas de la carrera:");
        Regla.setBounds(71, 80, 157, 15);
        contentPane.add(Regla);

        JLabel CodCategoria = new JLabel("Código de la categoría:");
        CodCategoria.setBounds(71, 107, 184, 15);
        contentPane.add(CodCategoria);

        JLabel CodRecorrido = new JLabel("Código del recorrido:");
        CodRecorrido.setBounds(71, 134, 157, 15);
        contentPane.add(CodRecorrido);

        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("U");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tSexo = new JFormattedTextField(formatter);
        tSexo.setBounds(158, 24, 114, 19);
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
        tRegla.setBounds(242, 78, 114, 19);
        contentPane.add(tRegla);
        tRegla.setColumns(10);

        tCodCategoria = new JComboBox<>();
        tCodCategoria.setBounds(255, 105, 114, 19);
        contentPane.add(tCodCategoria);

        tCodRecorrido = new JComboBox<>();
        tCodRecorrido.setBounds(228, 134, 114, 19);
        contentPane.add(tCodRecorrido);

        // Poblar el JComboBox con los códigos de la categoría
        desplegableCategoria();
        desplegableRecorrido();

        JButton btnAñadirCarrera = new JButton("Añadir Club");
        btnAñadirCarrera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                añadirCarrera();
            }
        });
        btnAñadirCarrera.setBounds(378, 291, 117, 25);
        contentPane.add(btnAñadirCarrera);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                volver();
            }
        });
        btnVolver.setBounds(378, 21, 117, 25);
        contentPane.add(btnVolver);
    }

    String userName = "bd";
    String password = "proyecto";
    String url = "jdbc:mysql://192.168.11.155:3306/ProyectoProgramación";
    
    private void desplegableCategoria() {
        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            String query = "SELECT nombre FROM CATEGORIA";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String codigoCategoria = rs.getString("nombre");
                tCodCategoria.addItem(codigoCategoria);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
    
    private void desplegableRecorrido() {
        try {
            
            Connection con = DriverManager.getConnection(url, userName, password);
            String query = "SELECT codigo_recorrido FROM RECORRIDO";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer codigoRecorrido = rs.getInt("codigo_recorrido");
                tCodRecorrido.addItem(codigoRecorrido);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    private void añadirCarrera() {
        try {


            Connection con = DriverManager.getConnection(url, userName, password);
            System.out.println("Has introducido el club correctamente");

            java.sql.Statement stmt = con.createStatement();
            
            
            String sql1 = "SELECT codigo_carrera FROM CARRERA ORDER BY codigo_carrera DESC LIMIT 1";
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql1);
            
            int codigoCarreraUltimo = 0;
            
            if (rs.next()) {
                codigoCarreraUltimo = rs.getInt("codigo_carrera");
            }
            
            String sql2 = "SELECT codigo_categoria FROM CATEGORIA where nombre='"+tCodCategoria.getSelectedItem()+"'";   
            rs = ((java.sql.Statement) stmt).executeQuery(sql2);
            
            int codigoCat=0;
            
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
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}