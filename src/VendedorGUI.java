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


public class VendedorGUI extends JFrame {

    private JPanel contentPane;
    
    private JTextField txtArticulo;
    private Vendedor agente;
    private JTextField txtPrecio;
    private JLabel lblArticulo;
    private JLabel lblPrecio;
    /**
     * Create the frame.
     */
    public VendedorGUI(Vendedor vendedor) {
        agente=vendedor;
        setTitle("Vendedor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 366, 158);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnNewButton = new JButton("Agregar Articulo");
        btnNewButton.setBounds(200, 42, 111, 23);
        contentPane.add(btnNewButton);
        
        txtArticulo = new JTextField();
        txtArticulo.setBounds(84, 11, 171, 20);
        contentPane.add(txtArticulo);
        txtArticulo.setColumns(10);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(84, 42, 86, 20);
        contentPane.add(txtPrecio);
        txtPrecio.setColumns(10);
        
        lblArticulo = new JLabel("Articulo:");
        lblArticulo.setBounds(28, 14, 46, 14);
        contentPane.add(lblArticulo);
        
        lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(28, 45, 46, 14);
        contentPane.add(lblPrecio);
       
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agente.venderLibro("titulo", "1000");
            }
        });
        
  

       
    }
}
