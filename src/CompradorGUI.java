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

@SuppressWarnings("serial")
public class CompradorGUI extends JFrame {
    private Persona agente;

    /**
     * Create the frame.
     */
    public CompradorGUI(Persona comprador) {
        agente = comprador;
        setResizable(false);
        setTitle(agente.getLocalName() + " (Comprador)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 260, 138);
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);
        
        JLabel lblNewLabel = new JLabel("Dinero disponible (Bs.)");
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 17, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, getContentPane());
        getContentPane().add(lblNewLabel);
        
        JLabel lblLibro = new JLabel("Libro");
        springLayout.putConstraint(SpringLayout.NORTH, lblLibro, 58, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, lblLibro, 30, SpringLayout.WEST, lblNewLabel);
        springLayout.putConstraint(SpringLayout.WEST, lblLibro, 0, SpringLayout.WEST, lblNewLabel);
        getContentPane().add(lblLibro);
        
        JTextField textField = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, textField, 38, SpringLayout.EAST, lblNewLabel);
        springLayout.putConstraint(SpringLayout.EAST, textField, -10, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, textField, 19, SpringLayout.NORTH, getContentPane());
        getContentPane().add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("Buscar");
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -75, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, textField);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agente.buscarLibro("titulo");
            }
        });
        getContentPane().add(btnNewButton);
        
        JTextField textField_1 = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, textField_1);
        springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -14, SpringLayout.NORTH, textField_1);
        springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
        springLayout.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblLibro);
        springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblLibro);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);
    }
}
