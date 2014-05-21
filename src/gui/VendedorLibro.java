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

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class VendedorLibro extends JDialog {
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;
    private Object[] libro;

    /**
     * Create the dialog.
     */
    public VendedorLibro(JFrame parent, String titulo, boolean modal, Object[] libro) {
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
                    JButton btnAceptar = new JButton("Aceptar");
                    btnAceptar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            // Validar campos
                            if(validarCampos()) {
                                VendedorLibro.this.libro = new Object[] {
                                        textFieldNombre.getText(),
                                        textFieldPrecio.getText(),
                                };
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

        // Validador de nombre (No puede ser vacío)
        textFieldNombre.setInputVerifier(new InputVerifier() {
            public boolean verify(JComponent comp) {
                JTextField textField = (JTextField) comp;

                boolean valido = textField.getText().trim().length() > 0;

                Color color = valido ? null : Color.red;
                comp.setBorder(BorderFactory.createLineBorder(color));

                return valido;
            }
        });

        // Validador de precio (Debe ser un número entero no negativo)
        textFieldPrecio.setInputVerifier(new InputVerifier() {
            public boolean verify(JComponent comp) {
                JTextField textField = (JTextField) comp;

                boolean valido = false;
                try {
                    int precio = Integer.parseInt(textField.getText());
                    valido = precio >= 0;
                } catch (NumberFormatException e) {}

                Color color = valido ? null : Color.red;
                comp.setBorder(BorderFactory.createLineBorder(color));

                return valido;
            }
        });

        // Cargar libro (en caso de edición)
        if(libro != null) {
            textFieldNombre.setText((String) libro[0]);
            textFieldPrecio.setText((String) libro[1]);
        }
    }

    public Object[] mostrar() {
        setVisible(true);
        return libro;
    }

    private boolean validarCampos() {
        return textFieldNombre.getInputVerifier().verify(textFieldNombre)
                && textFieldPrecio.getInputVerifier().verify(textFieldPrecio);
    }
}
