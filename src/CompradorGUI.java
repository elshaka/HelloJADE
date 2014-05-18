import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CompradorGUI extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldDineroDisponible;
    private Persona agente;

    /**
     * Create the frame.
     */
    public CompradorGUI(Persona comprador) {
        agente = comprador;
        setTitle(agente.getLocalName() + " (Comprador)");
        setPreferredSize(new Dimension(310, 140));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 310, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        SpringLayout sl_contentPane = new SpringLayout();
        contentPane.setLayout(sl_contentPane);
        
        JLabel lblDineroDisponible = new JLabel("Dinero disponible (Bs.)");
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblDineroDisponible, 10, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, lblDineroDisponible, 10, SpringLayout.WEST, contentPane);
        contentPane.add(lblDineroDisponible);
        
        textFieldDineroDisponible = new JTextField();
        sl_contentPane.putConstraint(SpringLayout.NORTH, textFieldDineroDisponible, -3, SpringLayout.NORTH, lblDineroDisponible);
        sl_contentPane.putConstraint(SpringLayout.WEST, textFieldDineroDisponible, -129, SpringLayout.EAST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, textFieldDineroDisponible, -10, SpringLayout.EAST, contentPane);
        textFieldDineroDisponible.setPreferredSize(new Dimension(120, 20));
        contentPane.add(textFieldDineroDisponible);
        textFieldDineroDisponible.setColumns(10);
        
        JComboBox comboBoxLibros = new JComboBox();
        sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxLibros, 39, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, comboBoxLibros, -10, SpringLayout.EAST, contentPane);
        comboBoxLibros.setPreferredSize(new Dimension(120, 20));
        comboBoxLibros.setModel(new DefaultComboBoxModel(new String[] {"Algebra Lineal", "Calculo II", "Fisica I", "Ingles II"}));
        contentPane.add(comboBoxLibros);
        
        JLabel lblLibro = new JLabel("Libro");
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblLibro, 3, SpringLayout.NORTH, comboBoxLibros);
        sl_contentPane.putConstraint(SpringLayout.WEST, lblLibro, 10, SpringLayout.WEST, contentPane);
        contentPane.add(lblLibro);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agente.buscarLibro("titulo");
            }
        });

        sl_contentPane.putConstraint(SpringLayout.WEST, btnBuscar, 39, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, btnBuscar, 0, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, btnBuscar, -162, SpringLayout.EAST, contentPane);
        contentPane.add(btnBuscar);
        
        JButton btnEditarLista = new JButton("Editar lista");
        sl_contentPane.putConstraint(SpringLayout.WEST, btnEditarLista, 39, SpringLayout.EAST, btnBuscar);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, btnEditarLista, 0, SpringLayout.SOUTH, contentPane);
        contentPane.add(btnEditarLista);
    }
}
