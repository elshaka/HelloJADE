import javax.swing.*;
import java.awt.Dimension;

public class JOptionPaneMultiInput {
   public static void main(String[] args) {
      JTextField nombre = new JTextField(5);
      nombre.setMaximumSize(new Dimension(2147483647, 20));
      JTextField precio = new JTextField(5);
      precio.setMaximumSize(new Dimension(2147483647, 20));

      JPanel myPanel = new JPanel();
      myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.X_AXIS));
      myPanel.add(new JLabel("Nombre"));
      myPanel.add(nombre);
      myPanel.add(new JLabel("Precio Bs."));
      myPanel.add(precio);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Editar libro", JOptionPane.OK_CANCEL_OPTION);
   }
}