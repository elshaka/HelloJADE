import jade.core.*;

public class Comprador extends Agent {
    /**
     * Serial para que Eclipse deje deje joder con la advertencia
     */
    private static final long serialVersionUID = 1520345756839421693L;
    private CompradorGUI gui = null;

    protected void setup() {
        System.out.println("Hola comprador!");
        gui = new CompradorGUI(this);
        gui.setVisible(true);
    }

    protected void takeDown() {
        System.out.println("Chao comprador!");
    }

    void algo() {
        System.out.println("Hace algo!");
    }
}