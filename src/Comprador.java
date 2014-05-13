import jade.core.*;

public class Comprador extends Agent {
    /**
     * Serial para que Eclipse deje deje joder con la advertencia
     */
    private static final long serialVersionUID = 1520345756839421693L;

    protected void setup() {
        System.out.println("Hola comprador!");
    }

    protected void takeDown() {
        System.out.println("Chao comprador!");
    }
}