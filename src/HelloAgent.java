import jade.core.*;

public class HelloAgent extends Agent {
    /**
     * Serial para que Eclipse deje deje joder con la advertencia
     */
    private static final long serialVersionUID = 1520345756839421693L;

    protected void setup() {
        System.out.println("Hello World!");
    }

    protected void takeDown() {
        System.out.println("Goodbye World!");
    }
}