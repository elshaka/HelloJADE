import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VendedorGUIPrincipal extends JFrame {

    private JPanel contentPane;
    private JTable tableLibros;
    private Persona agente;
    private JButton btnEliminar;
    private VendedorGUIAgregarEditarLibro guiVendedorAgregarEditarLibro;
    private JOptionPaneMultiInput dialog;
    private String[] arg;

    /**
     * Create the frame.
     */
    public VendedorGUIPrincipal(Persona persona) {
        this.agente = persona;
        setTitle(agente.getLocalName() + " (Vendedor)");
        setBounds(100, 100, 350, 310);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setMaximumSize(new Dimension(20, 0));
        contentPane.add(horizontalStrut);
        
        Box verticalBox = Box.createVerticalBox();
        contentPane.add(verticalBox);
        
        Component verticalStrut_1 = Box.createVerticalStrut(20);
        verticalStrut_1.setPreferredSize(new Dimension(0, 10));
        verticalStrut_1.setMinimumSize(new Dimension(0, 10));
        verticalStrut_1.setMaximumSize(new Dimension(0, 10));
        verticalBox.add(verticalStrut_1);
        
        JScrollPane scrollPane = new JScrollPane();
        verticalBox.add(scrollPane);
        
        tableLibros = new JTable();
        tableLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLibros.setModel(new DefaultTableModel(
            new Object[][] {
                {"Harry Potter", "1000"},
            },
            new String[] {
                "Libro", "Precio Bs."
            }
        ));
        tableLibros.getColumnModel().getColumn(0).setPreferredWidth(225);
        scrollPane.setViewportView(tableLibros);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setPreferredSize(new Dimension(0, 15));
        verticalStrut.setMinimumSize(new Dimension(0, 15));
        verticalStrut.setMaximumSize(new Dimension(0, 15));
        verticalBox.add(verticalStrut);
        
        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiVendedorAgregarEditarLibro.setVisible(true);
            }
        });
        horizontalBox.add(btnAgregar);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.main(arg);
            }
        });
        btnEditar.setPreferredSize(new Dimension(71, 23));
        btnEditar.setMinimumSize(new Dimension(71, 23));
        btnEditar.setMaximumSize(new Dimension(71, 23));
        horizontalBox.add(btnEditar);
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_1);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        
        tableLibros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tableLibros.getSelectedRow() > -1) {
                    btnEliminar.setEnabled(true);
                }
            }
        });
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableLibros.getSelectedRow();
                DefaultTableModel modelTable = (DefaultTableModel) tableLibros.getModel();
                if (selectedRow > -1) {
                    modelTable.removeRow(selectedRow);
                }
            }
        });
        
        btnEliminar.setPreferredSize(new Dimension(71, 23));
        btnEliminar.setMinimumSize(new Dimension(71, 23));
        btnEliminar.setMaximumSize(new Dimension(71, 23));
        horizontalBox.add(btnEliminar);
        
        Component verticalStrut_2 = Box.createVerticalStrut(20);
        verticalStrut_2.setPreferredSize(new Dimension(0, 10));
        verticalStrut_2.setMinimumSize(new Dimension(0, 10));
        verticalStrut_2.setMaximumSize(new Dimension(0, 10));
        verticalBox.add(verticalStrut_2);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalStrut_1.setMaximumSize(new Dimension(20, 0));
        contentPane.add(horizontalStrut_1);
        
        guiVendedorAgregarEditarLibro = new VendedorGUIAgregarEditarLibro();
    }

}
