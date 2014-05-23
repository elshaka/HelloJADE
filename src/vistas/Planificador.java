package vistas;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class Planificador extends JFrame {

    private JPanel contentPane;
    private JComboBox comboBoxAgentes;
    private JComboBox comboBoxPapeles;
    private JButton btnAplicar;
    private JPanel panel;
    private JPanel panel_1;

    private agentes.Planificador agente;

    public Planificador(agentes.Planificador planificador) {
        agente = planificador;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                agente.doDelete();
            }
        });

        setTitle(agente.getLocalName() + " (Planificador)");
        setBounds(100, 100, 388, 231);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        panel = new JPanel();
        contentPane.add(panel);
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

        JLabel lblAgente = new JLabel("Agente");
        panel.add(lblAgente, "2, 2");
        comboBoxAgentes = new JComboBox();
        panel.add(comboBoxAgentes, "4, 2, fill, default");
        comboBoxAgentes.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(PopupMenuEvent arg0) {}

            public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {}

            public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
                comboBoxAgentes.removeAllItems();
                ArrayList<String> personas = agente.buscarPersonas();
                Iterator<String> i = personas.iterator();
                while(i.hasNext()) {
                    comboBoxAgentes.addItem(i.next());
                }
                btnAplicar.setEnabled(comboBoxAgentes.getItemCount() != 0);
            }
        });
        JLabel lblPapel = new JLabel("Papel");
        panel.add(lblPapel, "2, 4");

        comboBoxPapeles = new JComboBox();
        panel.add(comboBoxPapeles, "4, 4, fill, default");
        comboBoxPapeles.setToolTipText("");
        comboBoxPapeles.setModel(new DefaultComboBoxModel(
                new String[] {"Comprador", "Vendedor"}));

        panel_1 = new JPanel();
        contentPane.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

        btnAplicar = new JButton("Aplicar");
        panel_1.add(btnAplicar);
        btnAplicar.setEnabled(false);
        btnAplicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Asigno los valores de Persona y Papel a quien se desea asignar
                agente.aplicarPapel(comboBoxAgentes.getSelectedItem().toString(),
                        comboBoxPapeles.getSelectedItem().toString());
            }
        });
    }
}
