import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

@SuppressWarnings("serial")
public class PlanificadorGUI extends JFrame {

    private JPanel contentPane;
    private Planificador agente;
    private JComboBox comboBox_Agente;
    private JComboBox comboBox_Papel;
    private JButton btnAplicar;
    /**
     * Create the frame.
     */
    public PlanificadorGUI(Planificador planificador) {
        agente= planificador;
        setTitle(agente.getLocalName() + " (Planificador)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 250, 142);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        SpringLayout sl_contentPane = new SpringLayout();
        contentPane.setLayout(sl_contentPane);
        
        JLabel lblAgente = new JLabel("Agente");
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblAgente, 10, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, lblAgente, 10, SpringLayout.WEST, contentPane);
        contentPane.add(lblAgente);
        JLabel lblPapel = new JLabel("Papel");
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblPapel, 16, SpringLayout.SOUTH, lblAgente);
        sl_contentPane.putConstraint(SpringLayout.WEST, lblPapel, 0, SpringLayout.WEST, lblAgente);
        contentPane.add(lblPapel);
        comboBox_Agente = new JComboBox();
        comboBox_Agente.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(PopupMenuEvent arg0) {
            }
            public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
            }
            
            public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
                comboBox_Agente.removeAllItems();
                ArrayList<String> personas = agente.buscarPersonas();
                Iterator<String> i = personas.iterator();
                while(i.hasNext()) {
                    comboBox_Agente.addItem(i.next());
                }
                if(comboBox_Agente.getItemCount()!=0){
                    btnAplicar.setEnabled(true);
                }
                else{
                    btnAplicar.setEnabled(false);
                }
                    
            }
        });

        sl_contentPane.putConstraint(SpringLayout.NORTH, comboBox_Agente, 0, SpringLayout.NORTH, lblAgente);
        sl_contentPane.putConstraint(SpringLayout.EAST, comboBox_Agente, -10, SpringLayout.EAST, contentPane);
        contentPane.add(comboBox_Agente);
        
        comboBox_Papel = new JComboBox();
        sl_contentPane.putConstraint(SpringLayout.NORTH, comboBox_Papel, -3, SpringLayout.NORTH, lblPapel);
        sl_contentPane.putConstraint(SpringLayout.EAST, comboBox_Papel, -10, SpringLayout.EAST, contentPane);
        comboBox_Papel.setToolTipText("");
        comboBox_Papel.setModel(new DefaultComboBoxModel(new String[] {"Comprador", "Vendedor"}));
        contentPane.add(comboBox_Papel);
        
        btnAplicar = new JButton("Aplicar");
        btnAplicar.setEnabled(false);
        sl_contentPane.putConstraint(SpringLayout.WEST, comboBox_Agente, 0, SpringLayout.WEST, btnAplicar);
        btnAplicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Asigno los valores de Persona y Papel a quien se desea asignar
                agente.aplicarPapel(comboBox_Agente.getSelectedItem().toString(), comboBox_Papel.getSelectedItem().toString());
            }
        });
        sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAplicar, 0, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, btnAplicar, -74, SpringLayout.EAST, contentPane);
        contentPane.add(btnAplicar);
    }
}
