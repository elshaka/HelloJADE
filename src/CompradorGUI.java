import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CompradorGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -9169532709872494043L;
    private JPanel contentPane;
    private Comprador agente;

    /**
     * Create the frame.
     */
    public CompradorGUI(Comprador comprador) {
        agente = comprador;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JButton btnHola = new JButton("Hola");
        btnHola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                agente.algo();
            }
        });
        contentPane.add(btnHola, BorderLayout.CENTER);
    }

}
