import jade.core.*;

public class Vendedor extends Agent {
    /**
     * Serial para que Eclipse deje deje joder con la advertencia
     */
    private static final long serialVersionUID = 4479575386140888632L;

    protected void setup() {
        System.out.println("Hola vendedor!");
    }

    protected void takeDown() {
        System.out.println("Chao vendedor!");
    }
}