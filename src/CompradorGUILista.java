import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CompradorGUILista extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private CompradorGUIAgregarEditarLibro guiCompradorAgregarEditarLibro;

    /**
     * Create the frame.
     */
    public CompradorGUILista() {
        setTitle("Editar lista");
        setBounds(100, 100, 325, 170);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        
        Box verticalBox = Box.createVerticalBox();
        contentPane.add(verticalBox);
        
        table = new JTable();
        table.setBorder(new LineBorder(Color.GRAY));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null},
                {null},
                {null},
            },
            new String[] {
                "Libro"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        verticalBox.add(table);
        
        Component verticalGlue = Box.createVerticalGlue();
        verticalBox.add(verticalGlue);
        
        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);
        
        Component horizontalGlue_2 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_2);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiCompradorAgregarEditarLibro.setTitle("Agregar libro");
                guiCompradorAgregarEditarLibro.setVisible(true);
            }
        });
        horizontalBox.add(btnAgregar);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                guiCompradorAgregarEditarLibro.setTitle("Editar libro");
                guiCompradorAgregarEditarLibro.setVisible(true);
            }
        });
        horizontalBox.add(btnEditar);
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_1);
        
        JButton btnEliminar = new JButton("Eliminar");
        horizontalBox.add(btnEliminar);
        
        Component horizontalGlue_3 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_3);
        
        guiCompradorAgregarEditarLibro = new CompradorGUIAgregarEditarLibro();
    }

}
