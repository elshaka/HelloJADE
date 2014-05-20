import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;


public class VendedorGUI extends JFrame {

    private JPanel contentPane;
    
    private JTextField txtArticulo;
    private Persona agente;
    private JTextField txtPrecio;
    private JLabel lblArticulo;
    private JLabel lblPrecio;
    private JTable tableArticulos;
    private DefaultTableModel modelo;
    /**
     * Create the frame.
     */
    public VendedorGUI(Persona persona) {
        this.agente = persona;
        setTitle(agente.getLocalName() + " (Vendedor)");
        setBounds(100, 100, 415, 319);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        SpringLayout sl_contentPane = new SpringLayout();
        contentPane.setLayout(sl_contentPane);
        
        lblArticulo = new JLabel("Articulo:");
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblArticulo, 16, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, lblArticulo, 3, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, lblArticulo, 30, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, lblArticulo, 68, SpringLayout.WEST, contentPane);
        contentPane.add(lblArticulo);
        lblArticulo.setHorizontalAlignment(SwingConstants.RIGHT);
        
        txtArticulo = new JTextField();
        sl_contentPane.putConstraint(SpringLayout.WEST, txtArticulo, 74, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, txtArticulo, 294, SpringLayout.WEST, contentPane);
        contentPane.add(txtArticulo);
        txtArticulo.setColumns(10);
        
        lblPrecio = new JLabel("Precio:");
        sl_contentPane.putConstraint(SpringLayout.WEST, lblPrecio, 3, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, lblPrecio, 61, SpringLayout.WEST, contentPane);
        contentPane.add(lblPrecio);
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        
        txtPrecio = new JTextField();
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblPrecio, 3, SpringLayout.NORTH, txtPrecio);
        sl_contentPane.putConstraint(SpringLayout.WEST, txtPrecio, 74, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.NORTH, txtPrecio, 38, SpringLayout.NORTH, contentPane);
        contentPane.add(txtPrecio);
        txtPrecio.setColumns(10);
        
        JButton btnNewButton = new JButton("Agregar Articulo");
        sl_contentPane.putConstraint(SpringLayout.SOUTH, txtArticulo, -4, SpringLayout.NORTH, btnNewButton);
        sl_contentPane.putConstraint(SpringLayout.EAST, txtPrecio, -6, SpringLayout.WEST, btnNewButton);
        sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 144, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 37, SpringLayout.NORTH, contentPane);
        contentPane.add(btnNewButton);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
        modelo = new DefaultTableModel();
        tableArticulos = new JTable(modelo);
        tableArticulos.setBackground(Color.LIGHT_GRAY);
        tableArticulos.setShowVerticalLines(false);
        sl_contentPane.putConstraint(SpringLayout.NORTH, tableArticulos, -163, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, tableArticulos, 25, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, tableArticulos, -10, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, tableArticulos, -53, SpringLayout.EAST, contentPane);
        tableArticulos.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Articulo", "Precio"
            }
        ));
        contentPane.add(tableArticulos);
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int numCols = tableArticulos.getModel().getColumnCount();
                Object [] fila = new Object[numCols];
                fila[0] = txtArticulo.getText().toString();
                fila[1] = txtPrecio.getText();
                ((DefaultTableModel) tableArticulos.getModel()).addRow(fila);
                agente.venderLibro(txtArticulo.getText().toString(), txtPrecio.getText());
                txtArticulo.setText(null);
                txtPrecio.setText(null);
            }
        });
    }
}
