import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CompradorGUIAgregarEditarLibro extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldNombre;

    /**
     * Create the frame.
     */
    public CompradorGUIAgregarEditarLibro() {
        setTitle("Agregar libro");
        setBounds(100, 100, 250, 120);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        
        Box verticalBox = Box.createVerticalBox();
        contentPane.add(verticalBox);
        
        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);
        horizontalBox.setMaximumSize(new Dimension(500, 20));
        
        JLabel lblNombre = new JLabel("Nombre");
        horizontalBox.add(lblNombre);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setMaximumSize(new Dimension(15, 32767));
        horizontalStrut.setMinimumSize(new Dimension(15, 0));
        horizontalStrut.setPreferredSize(new Dimension(15, 0));
        horizontalBox.add(horizontalStrut);
        
        textFieldNombre = new JTextField();
        horizontalBox.add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setMaximumSize(new Dimension(32767, 10));
        verticalStrut.setMinimumSize(new Dimension(0, 10));
        verticalStrut.setPreferredSize(new Dimension(0, 10));
        verticalBox.add(verticalStrut);
        
        Box horizontalBox_1 = Box.createHorizontalBox();
        verticalBox.add(horizontalBox_1);
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox_1.add(horizontalGlue_1);
        
        JButton btnAceptar = new JButton("Aceptar");
        horizontalBox_1.add(btnAceptar);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox_1.add(horizontalGlue);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        horizontalBox_1.add(btnCancelar);
        
        Component horizontalGlue_2 = Box.createHorizontalGlue();
        horizontalBox_1.add(horizontalGlue_2);
    }

}
