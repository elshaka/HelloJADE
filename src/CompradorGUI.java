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

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class CompradorGUI extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldDineroDisponible;
    private Persona agente;

    /**
     * Create the frame.
     */
    public CompradorGUI(Persona comprador) {
        setMinimumSize(new Dimension(250, 100));
        setMaximumSize(new Dimension(250, 100));
        setResizable(false);
        agente = comprador;
        setTitle(agente.getLocalName() + " (Comprador)");
        setPreferredSize(new Dimension(250, 100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 270, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        Box verticalBox = Box.createVerticalBox();
        
        Component verticalGlue = Box.createVerticalGlue();
        verticalBox.add(verticalGlue);
        
        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setMaximumSize(new Dimension(10, 32767));
        horizontalStrut.setMinimumSize(new Dimension(10, 0));
        horizontalStrut.setPreferredSize(new Dimension(10, 0));
        horizontalBox.add(horizontalStrut);
        
        JLabel lblDineroDisponible = new JLabel("Dinero disponible (Bs.)");
        horizontalBox.add(lblDineroDisponible);
        
        Component horizontalGlue_3 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_3);
        
        textFieldDineroDisponible = new JTextField();
        textFieldDineroDisponible.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldDineroDisponible.setAlignmentX(Component.RIGHT_ALIGNMENT);
        textFieldDineroDisponible.setMinimumSize(new Dimension(100, 20));
        textFieldDineroDisponible.setMaximumSize(new Dimension(100, 20));
        horizontalBox.add(textFieldDineroDisponible);
        textFieldDineroDisponible.setPreferredSize(new Dimension(100, 20));
        textFieldDineroDisponible.setColumns(10);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalStrut_1.setPreferredSize(new Dimension(10, 0));
        horizontalStrut_1.setMinimumSize(new Dimension(10, 0));
        horizontalStrut_1.setMaximumSize(new Dimension(10, 32767));
        horizontalBox.add(horizontalStrut_1);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setPreferredSize(new Dimension(0, 5));
        verticalBox.add(verticalStrut);
        
        Box horizontalBox_1 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_1);
        
        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        horizontalStrut_2.setMaximumSize(new Dimension(10, 32767));
        horizontalStrut_2.setMinimumSize(new Dimension(10, 0));
        horizontalStrut_2.setPreferredSize(new Dimension(10, 0));
        horizontalBox_1.add(horizontalStrut_2);
        
        JLabel lblLibro = new JLabel("Libro");
        horizontalBox_1.add(lblLibro);
        
        Component horizontalGlue_4 = Box.createHorizontalGlue();
        horizontalBox_1.add(horizontalGlue_4);
        
        JComboBox comboBoxLibros = new JComboBox();
        comboBoxLibros.setMinimumSize(new Dimension(100, 20));
        comboBoxLibros.setMaximumSize(new Dimension(200, 20));
        horizontalBox_1.add(comboBoxLibros);
        comboBoxLibros.setPreferredSize(new Dimension(100, 20));
        comboBoxLibros.setModel(new DefaultComboBoxModel(new String[] {"Algebra Lineal", "Calculo II", "Fisica I", "Ingles II"}));
        
        Component horizontalStrut_3 = Box.createHorizontalStrut(20);
        horizontalStrut_3.setPreferredSize(new Dimension(10, 0));
        horizontalStrut_3.setMinimumSize(new Dimension(10, 0));
        horizontalStrut_3.setMaximumSize(new Dimension(10, 32767));
        horizontalBox_1.add(horizontalStrut_3);
        
        Component verticalStrut_1 = Box.createVerticalStrut(20);
        verticalStrut_1.setPreferredSize(new Dimension(0, 10));
        verticalBox.add(verticalStrut_1);
        
        Box horizontalBox_2 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_2);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox_2.add(horizontalGlue);
        
        JButton btnBuscar = new JButton("Buscar");
        horizontalBox_2.add(btnBuscar);
        btnBuscar.setMinimumSize(new Dimension(90, 23));
        btnBuscar.setMaximumSize(new Dimension(90, 23));
        btnBuscar.setPreferredSize(new Dimension(90, 23));
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox_2.add(horizontalGlue_1);
        
        JButton btnEditarLista = new JButton("Editar lista");
        btnEditarLista.setMinimumSize(new Dimension(90, 23));
        btnEditarLista.setMaximumSize(new Dimension(90, 23));
        btnEditarLista.setPreferredSize(new Dimension(90, 23));
        horizontalBox_2.add(btnEditarLista);
        
        Component horizontalGlue_2 = Box.createHorizontalGlue();
        horizontalBox_2.add(horizontalGlue_2);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(verticalBox, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(verticalBox, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );
        
        Component verticalGlue_1 = Box.createVerticalGlue();
        verticalBox.add(verticalGlue_1);
        contentPane.setLayout(gl_contentPane);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agente.buscarLibro("titulo");
            }
        });
    }
}
