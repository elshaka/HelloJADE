import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Rectangle;


public class VendedorGUIAgregarEditarLibro extends JDialog {
    private JTextField textField;
    private JTextField textFieldPrecio;

    /**
     * Create the dialog.
     */
    public VendedorGUIAgregarEditarLibro() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 266, 130);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        {
            Component horizontalStrut = Box.createHorizontalStrut(20);
            horizontalStrut.setPreferredSize(new Dimension(15, 0));
            horizontalStrut.setMinimumSize(new Dimension(15, 0));
            horizontalStrut.setMaximumSize(new Dimension(15, 0));
            getContentPane().add(horizontalStrut);
        }
        {
            Box verticalBox = Box.createVerticalBox();
            verticalBox.setBounds(new Rectangle(0, 100, 100, 100));
            getContentPane().add(verticalBox);
            {
                Box horizontalBox = Box.createHorizontalBox();
                verticalBox.add(horizontalBox);
                {
                    JLabel lblNombre = new JLabel("Nombre");
                    horizontalBox.add(lblNombre);
                }
                {
                    Component horizontalStrut = Box.createHorizontalStrut(20);
                    horizontalStrut.setMaximumSize(new Dimension(20, 0));
                    horizontalBox.add(horizontalStrut);
                }
                {
                    textField = new JTextField();
                    textField.setMaximumSize(new Dimension(2147483647, 20));
                    horizontalBox.add(textField);
                    textField.setColumns(10);
                }
            }
            {
                Component verticalStrut = Box.createVerticalStrut(20);
                verticalStrut.setPreferredSize(new Dimension(0, 10));
                verticalStrut.setMinimumSize(new Dimension(0, 10));
                verticalStrut.setMaximumSize(new Dimension(0, 10));
                verticalBox.add(verticalStrut);
            }
            {
                Box horizontalBox = Box.createHorizontalBox();
                verticalBox.add(horizontalBox);
                {
                    JLabel lblNewLabel = new JLabel("Precio Bs.");
                    horizontalBox.add(lblNewLabel);
                }
                {
                    Component horizontalStrut = Box.createHorizontalStrut(20);
                    horizontalStrut.setPreferredSize(new Dimension(10, 0));
                    horizontalStrut.setMinimumSize(new Dimension(10, 0));
                    horizontalStrut.setMaximumSize(new Dimension(10, 0));
                    horizontalBox.add(horizontalStrut);
                }
                {
                    textFieldPrecio = new JTextField();
                    textFieldPrecio.setMaximumSize(new Dimension(2147483647, 20));
                    horizontalBox.add(textFieldPrecio);
                    textFieldPrecio.setColumns(10);
                }
            }
            {
                Component verticalStrut = Box.createVerticalStrut(20);
                verticalStrut.setPreferredSize(new Dimension(0, 10));
                verticalStrut.setMinimumSize(new Dimension(0, 10));
                verticalStrut.setMaximumSize(new Dimension(0, 10));
                verticalBox.add(verticalStrut);
            }
            {
                Box horizontalBox = Box.createHorizontalBox();
                verticalBox.add(horizontalBox);
                {
                    Component horizontalGlue = Box.createHorizontalGlue();
                    horizontalBox.add(horizontalGlue);
                }
                {
                    JButton btnNewButton = new JButton("Aceptar");
                    horizontalBox.add(btnNewButton);
                }
                {
                    Component horizontalGlue = Box.createHorizontalGlue();
                    horizontalBox.add(horizontalGlue);
                }
                {
                    JButton btnNewButton_1 = new JButton("Cancelar");
                    horizontalBox.add(btnNewButton_1);
                }
                {
                    Component horizontalGlue = Box.createHorizontalGlue();
                    horizontalBox.add(horizontalGlue);
                }
            }
        }
        {
            Component horizontalStrut = Box.createHorizontalStrut(20);
            horizontalStrut.setMinimumSize(new Dimension(15, 0));
            horizontalStrut.setPreferredSize(new Dimension(15, 0));
            horizontalStrut.setMaximumSize(new Dimension(15, 0));
            getContentPane().add(horizontalStrut);
        }
    }

}
