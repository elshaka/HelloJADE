package gui;
import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import modelos.Libro;
import modelos.ValidadorNombre;
import modelos.ValidadorPrecio;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VendedorLibro extends JDialog {
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;
    private Libro libro;

    /**
     * Create the dialog.
     */
    public VendedorLibro(JFrame parent, String titulo, boolean modal, Libro libro) {
        super(parent, titulo, modal);
        this.libro = libro;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 320, 167);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
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
                    textFieldNombre = new JTextField();
                    textFieldNombre.setMaximumSize(new Dimension(2147483647, 20));
                    horizontalBox.add(textFieldNombre);
                    textFieldNombre.setColumns(10);
                    textFieldNombre.setInputVerifier(new ValidadorNombre());
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
                    textFieldPrecio.setInputVerifier(new ValidadorPrecio());
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
                    JButton btnAceptar = new JButton("Aceptar");
                    btnAceptar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            if(validarCampos()) {
                                VendedorLibro.this.libro = new Libro(textFieldNombre.getText().trim(),
                                        Integer.parseInt(textFieldPrecio.getText().trim()));
                                setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(VendedorLibro.this, "Campos inválidos");
                            }
                        }
                    });
                    horizontalBox.add(btnAceptar);
                }
                {
                    Component horizontalGlue = Box.createHorizontalGlue();
                    horizontalBox.add(horizontalGlue);
                }
                {
                    JButton btnCancelar = new JButton("Cancelar");
                    btnCancelar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            setVisible(false);
                        }
                    });
                    horizontalBox.add(btnCancelar);
                }
                {
                    Component horizontalGlue = Box.createHorizontalGlue();
                    horizontalBox.add(horizontalGlue);
                }
            }
        }

        // Cargar libro (en caso de edición)
        if(libro != null) {
            textFieldNombre.setText(libro.getNombre());
            textFieldPrecio.setText(Integer.toString(libro.getPrecio()));
        }
    }

    public Libro mostrar() {
        setVisible(true);
        return libro;
    }

    private boolean validarCampos() {
        return textFieldNombre.getInputVerifier().verify(textFieldNombre)
                && textFieldPrecio.getInputVerifier().verify(textFieldPrecio);
    }
}
