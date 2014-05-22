package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import modelos.Libro;
import agentes.Persona;

import javax.swing.SwingConstants;

import utilidades.ValidadorNombre;
import utilidades.ValidadorPrecio;

@SuppressWarnings({"serial", "unused"})
public class Comprador extends JPanel {

    private agentes.Persona agente;
    private JFrame frame;
    private JTextField textFieldDineroDisponible;
    private JTextField textFieldNombre;
    private JPanel panel;
    private JPanel panel_1;
    private JLabel lblDineroDisponible;
    private JLabel lblDineroDisponible_1;
    private JLabel lblNewLabel;

    public Comprador(JFrame frame, agentes.Persona comprador) {
        this.frame = frame;
        agente = comprador;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel lblNewLabel = new JLabel("Comprador");
        add(lblNewLabel);

        panel = new JPanel();
        add(panel);
        FormLayout fl_panel = new FormLayout(new ColumnSpec[] {
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
            new RowSpec[] {
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,});
        panel.setLayout(fl_panel);

        lblDineroDisponible = new JLabel("Nombre");
        panel.add(lblDineroDisponible, "2, 2, right, default");

        textFieldNombre = new JTextField();
        panel.add(textFieldNombre, "4, 2, fill, default");
        textFieldNombre.setInputVerifier(new ValidadorNombre());

        lblDineroDisponible_1 = new JLabel("Dinero disponible");
        panel.add(lblDineroDisponible_1, "2, 4");

        textFieldDineroDisponible = new JTextField();
        panel.add(textFieldDineroDisponible, "4, 4, fill, default");
        textFieldDineroDisponible.setInputVerifier(new ValidadorPrecio());

        panel_1 = new JPanel();
        add(panel_1);

        JButton btnComprar = new JButton("Comprar libro");
        panel_1.add(btnComprar);
        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()) {
                    Libro libro = new Libro(textFieldNombre.getText().trim(),
                            Integer.parseInt(textFieldDineroDisponible.getText()));
                    agente.buscarLibro(libro);
                }
            }
        });
    }

    private boolean validarCampos() {
        return textFieldNombre.getInputVerifier().verify(textFieldNombre)
                && textFieldDineroDisponible.getInputVerifier().verify(textFieldDineroDisponible);
    }
}
