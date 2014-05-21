package gui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import modelos.Libro;

@SuppressWarnings("serial")
public class Vendedor extends JFrame {

    private JPanel contentPane;
    private JTable tablaLibros;
    private agentes.Persona agente;
    private JButton btnEliminar;
    private ModeloTablaLibros modeloTablaLibros;
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

        modeloTablaLibros = new ModeloTablaLibros(new ArrayList<Libro>());

        tablaLibros = new JTable();
        tablaLibros.setModel(modeloTablaLibros);
        scrollPane.setViewportView(tablaLibros);

        tablaLibros.setColumnSelectionAllowed(false);
        tablaLibros.setRowSelectionAllowed(true);
        tablaLibros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                boolean enable = tablaLibros.getSelectedRow() > -1;
                btnEditar.setEnabled(enable);
                btnEliminar.setEnabled(enable);
            }
        });

        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VendedorLibro agregarLibro = new VendedorLibro(Vendedor.this, "Agregar libro", true, null);
                Libro libro = agregarLibro.mostrar();
                if(libro != null) {
                    if(!getLibros().contains(libro)) {
                        modeloTablaLibros.agregarLibro(libro);
                    } else {
                        JOptionPane.showMessageDialog(Vendedor.this, "El libro '" + libro.getNombre() + "' ya existe");
                    }
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
                int selectedRow = tablaLibros.getSelectedRow();
                if (selectedRow > -1) {
                    Libro libroEditado = modeloTablaLibros.getLibro(selectedRow);
                    VendedorLibro editarLibro = new VendedorLibro(Vendedor.this, "Editar libro", true, libroEditado);
                    libroEditado = editarLibro.mostrar();
                    modeloTablaLibros.actualizarLibro(selectedRow, libroEditado);
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
                int selectedRow = tablaLibros.getSelectedRow();
                if (selectedRow > -1) {
                    modeloTablaLibros.eliminarLibro(selectedRow);
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

    // Metodo que usaría el agente comprador para obtener sus libros
    public ArrayList<Libro> getLibros() {
        return modeloTablaLibros.getLibros();
    }

    // Modelo de tabla tuning para manejar libros 
    private class ModeloTablaLibros extends AbstractTableModel {
        private ArrayList<Libro> libros;
        private String[] columnas = {"Título", "Precio"};

        public ModeloTablaLibros(ArrayList<Libro> libros) {
            super();
            this.libros = libros;
        }

        public String getColumnName(int col) {
            return columnas[col];
        }

        @Override
        public int getColumnCount() {
            return columnas.length;
        }

        @Override
        public int getRowCount() {
            return libros.size();
        }

        @Override
        public Object getValueAt(int row, int col) {
            Object object = null;
            switch(col) {
            case 0:
                object = (Object) libros.get(row).getNombre();
                break;
            case 1:
                object = (Object) libros.get(row).getPrecio();
                break;
            }
            return object;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void agregarLibro(Libro libro) {
            libros.add(libro);
            fireTableDataChanged();
        }

        public Libro getLibro(int index) {
            return libros.get(index);
        }

        public void actualizarLibro(int index, Libro libroActualizado) {
            libros.set(index, libroActualizado);
            fireTableDataChanged();
        }

        public void eliminarLibro(int index) {
            libros.remove(index);
            fireTableDataChanged();
        }

        public ArrayList<Libro> getLibros() {
            return libros;
        }
    }

}
