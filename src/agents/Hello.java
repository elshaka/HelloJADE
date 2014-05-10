package agents;
import jade.core.*;

public class Hello extends Agent {
    protected void setup()  {
        System.out.println("Hello World!");
    }

    protected void takeDown() {
        System.out.println("Goodbye World!");
    }
}