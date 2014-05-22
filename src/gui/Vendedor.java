package gui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import modelos.Libro;

@SuppressWarnings("serial")
public class Vendedor extends JFrame {

    private JPanel contentPane;
    private JTable tablaLibros;
    private agentes.Persona agente;
    private JButton btnEliminar;
    private JButton btnEditar;
    private ModeloTablaLibros modeloTablaLibros;

    /**
     * Create the frame.
     */
    public Vendedor(agentes.Persona persona) {
        this.agente = persona;
        setTitle(agente.getLocalName() + " (Vendedor)");
        setBounds(100, 100, 400, 252);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        modeloTablaLibros = new ModeloTablaLibros(new ArrayList<Libro>());

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane);

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

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1);

        JButton btnAgregar = new JButton("Agregar");
        panel_1.add(btnAgregar);

        btnEditar = new JButton("Editar");
        panel_1.add(btnEditar);
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

        btnEliminar = new JButton("Eliminar");
        panel_1.add(btnEliminar);
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaLibros.getSelectedRow();
                if (selectedRow > -1) {
                    modeloTablaLibros.eliminarLibro(selectedRow);
                }
            }
        });

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
    }

    // Metodo que usaría el agente comprador para obtener sus libros
    public ArrayList<Libro> getLibros() {
        return modeloTablaLibros.getLibros();
    }

    // Modelo de tabla para manejar libros 
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