import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CompradorGUI_Lista extends JFrame {

    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CompradorGUI_Lista frame = new CompradorGUI_Lista();
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
    public CompradorGUI_Lista() {
        setTitle("Editar libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 240);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        
        Box verticalBox = Box.createVerticalBox();
        contentPane.add(verticalBox);
        
        table = new JTable();
        table.setBorder(new LineBorder(Color.GRAY));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
            },
            new String[] {
                "Libro"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        verticalBox.add(table);
        
        Component verticalGlue = Box.createVerticalGlue();
        verticalBox.add(verticalGlue);
        
        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);
        
        Component horizontalGlue_2 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_2);
        
        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        horizontalBox.add(btnNewButton);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue);
        
        JButton btnNewButton_1 = new JButton("Editar");
        horizontalBox.add(btnNewButton_1);
        
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_1);
        
        JButton btnNewButton_2 = new JButton("Eliminar");
        horizontalBox.add(btnNewButton_2);
        
        Component horizontalGlue_3 = Box.createHorizontalGlue();
        horizontalBox.add(horizontalGlue_3);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        verticalBox.add(verticalStrut);
    }

}
