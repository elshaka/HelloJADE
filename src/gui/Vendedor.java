package gui;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Vendedor extends JFrame {

    private JPanel contentPane;
    private JTable tableLibros;
    private agentes.Persona agente;
    private JButton btnEliminar;
    private DefaultTableModel modelTable;
    private JButton btnEditar;

    /**
     * Create the frame.
     */
    public Vendedor(agentes.Persona persona) {
        this.agente = persona;
        setTitle(agente.getLocalName() + " (Vendedor)");
        setBounds(100, 100, 404, 330);
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

        tableLibros.setColumnSelectionAllowed(false);
        tableLibros.setRowSelectionAllowed(true);
        tableLibros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                boolean enable = tableLibros.getSelectedRow() > -1;
                btnEditar.setEnabled(enable);
                btnEliminar.setEnabled(enable);
            }
        });

        modelTable = (DefaultTableModel) tableLibros.getModel();
        
        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VendedorLibro agregarLibro = new VendedorLibro(Vendedor.this, "Agregar libro", true, null);
                Object[] libro = agregarLibro.mostrar();
                if(libro != null) {
                    modelTable.addRow(libro);
                }
            }
        });
        horizontalBox.add(btnAgregar);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue);
        
        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableLibros.getSelectedRow();
                if (selectedRow > -1) {
                    Object[] libro = new Object[] {modelTable.getValueAt(selectedRow, 0), 
                            modelTable.getValueAt(selectedRow, 1)
                    };
                    VendedorLibro editarLibro = new VendedorLibro(Vendedor.this, "Agregar libro", true, libro);
                    libro = editarLibro.mostrar();
                    modelTable.setValueAt(libro[0], selectedRow, 0);
                    modelTable.setValueAt(libro[1], selectedRow, 1);
                }
            }
        });
        btnEditar.setPreferredSize(new Dimension(100, 23));
        btnEditar.setMinimumSize(new Dimension(100, 23));
        btnEditar.setMaximumSize(new Dimension(100, 23));
        horizontalBox.add(btnEditar);
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_1);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableLibros.getSelectedRow();
                if (selectedRow > -1) {
                    modelTable.removeRow(selectedRow);
                }
            }
        });
        
        btnEliminar.setPreferredSize(new Dimension(100, 23));
        btnEliminar.setMinimumSize(new Dimension(100, 23));
        btnEliminar.setMaximumSize(new Dimension(100, 23));
        horizontalBox.add(btnEliminar);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalStrut_1.setMaximumSize(new Dimension(20, 0));
        contentPane.add(horizontalStrut_1);
    }

}
