import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CompradorGUI extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -6485511916292451003L;
    private JTextField textField;
    private Persona agente;

    /**
     * Create the frame.
     */
    public CompradorGUI(Persona comprador) {
        agente = comprador;
        setResizable(false);
        setTitle(agente.getLocalName() + " (Comprador)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 260, 160);
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);
        
        JLabel lblNewLabel = new JLabel("Dinero disponible (Bs.)");
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 17, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, getContentPane());
        getContentPane().add(lblNewLabel);
        
        JLabel lblLibro = new JLabel("Libro");
        springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -17, SpringLayout.NORTH, lblLibro);
        springLayout.putConstraint(SpringLayout.WEST, lblLibro, 0, SpringLayout.WEST, lblNewLabel);
        getContentPane().add(lblLibro);
        
        JComboBox comboBox = new JComboBox();
        springLayout.putConstraint(SpringLayout.NORTH, lblLibro, 3, SpringLayout.NORTH, comboBox);
        springLayout.putConstraint(SpringLayout.EAST, comboBox, -10, SpringLayout.EAST, getContentPane());
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Algebra lineal", "Calculo II", "Fisica I"}));
        getContentPane().add(comboBox);
        
        textField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, comboBox, 16, SpringLayout.SOUTH, textField);
        springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, comboBox);
        springLayout.putConstraint(SpringLayout.EAST, textField, -10, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, textField, 19, SpringLayout.NORTH, getContentPane());
        getContentPane().add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agente.buscarLibro("titulo");
            }
        });
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -154, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -89, SpringLayout.EAST, getContentPane());
        getContentPane().add(btnNewButton);
    }
}
