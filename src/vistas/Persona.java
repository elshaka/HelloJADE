package vistas;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import modelos.Libro;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Persona extends JFrame {

    private JPanel contentPane;
    private Comprador panelComprador;
    private Vendedor panelVendedor;

    private agentes.Persona agente;

    public Persona(agentes.Persona persona) {
        agente = persona;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                agente.doDelete();
            }
        });
        setTitle(persona.getLocalName());

        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        panelComprador = new Comprador(this, persona);
        panelComprador.setVisible(false);
        getContentPane().add(panelComprador);

        panelVendedor = new Vendedor(this, persona);
        panelVendedor.setVisible(false);
        getContentPane().add(panelVendedor);
    }

    public ArrayList<Libro> getLibros() {
        return panelVendedor.getLibros();
    }

    public void setPapel(String papel) {
        panelComprador.setVisible(false);
        panelVendedor.setVisible(false);
        switch(papel) {
        case "Comprador":
            panelComprador.setVisible(true);
            break;
        case "Vendedor":
            panelVendedor.setVisible(true);
            break;
        }
    }

    public void aviso(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
