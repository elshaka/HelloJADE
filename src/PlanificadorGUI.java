import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;


public class PlanificadorGUI extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlanificadorGUI frame = new PlanificadorGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PlanificadorGUI() {
        setTitle("Planificador");
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
        
        JComboBox comboBox_Agente = new JComboBox();
        sl_contentPane.putConstraint(SpringLayout.NORTH, comboBox_Agente, 0, SpringLayout.NORTH, lblAgente);
        sl_contentPane.putConstraint(SpringLayout.EAST, comboBox_Agente, -10, SpringLayout.EAST, contentPane);
        contentPane.add(comboBox_Agente);
        
        JComboBox comboBox_Papel = new JComboBox();
        sl_contentPane.putConstraint(SpringLayout.NORTH, comboBox_Papel, -3, SpringLayout.NORTH, lblPapel);
        sl_contentPane.putConstraint(SpringLayout.EAST, comboBox_Papel, 0, SpringLayout.EAST, comboBox_Agente);
        comboBox_Papel.setToolTipText("");
        comboBox_Papel.setModel(new DefaultComboBoxModel(new String[] {"Comprador", "Vendedor"}));
        contentPane.add(comboBox_Papel);
        
        JButton btnAplicar = new JButton("Aplicar");
        sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAplicar, 0, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, btnAplicar, -74, SpringLayout.EAST, contentPane);
        contentPane.add(btnAplicar);
    }
}
