package vistas;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilidades.ValidadorNombre;
import utilidades.ValidadorPrecio;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import modelos.Libro;

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
        setBounds(100, 100, 320, 154);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
            getContentPane().add(panel);
            panel.setLayout(new FormLayout(new ColumnSpec[] {
                    FormFactory.RELATED_GAP_COLSPEC,
                    FormFactory.DEFAULT_COLSPEC,
                    FormFactory.RELATED_GAP_COLSPEC,
                    ColumnSpec.decode("default:grow"),},
                new RowSpec[] {
                    FormFactory.RELATED_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.RELATED_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,}));
            {
                JLabel lblNombre = new JLabel("Nombre");
                panel.add(lblNombre, "2, 2");
            }
            {
                textFieldNombre = new JTextField();
                panel.add(textFieldNombre, "4, 2, fill, default");
                textFieldNombre.setMaximumSize(new Dimension(2147483647, 20));
                textFieldNombre.setColumns(10);
                textFieldNombre.setInputVerifier(new ValidadorNombre());
            }
            {
                JLabel lblNewLabel = new JLabel("Precio Bs.");
                panel.add(lblNewLabel, "2, 4, fill, default");
            }
            {
                textFieldPrecio = new JTextField();
                panel.add(textFieldPrecio, "4, 4");
                textFieldPrecio.setMaximumSize(new Dimension(2147483647, 20));
                textFieldPrecio.setColumns(10);
                textFieldPrecio.setInputVerifier(new ValidadorPrecio());
            }
        }
        {
            JPanel panel_1 = new JPanel();
            getContentPane().add(panel_1);
            {
                JButton btnAceptar = new JButton("Aceptar");
                panel_1.add(btnAceptar);
                {
                    JButton btnCancelar = new JButton("Cancelar");
                    panel_1.add(btnCancelar);
                    btnCancelar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            setVisible(false);
                        }
                    });
                }
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
