import jade.core.*;

public class Planificador extends Agent {

    /**
     * 
     */
    private static final long serialVersionUID = -7879441730678625604L;

    protected void setup() {
        System.out.println(this.getLocalName() + " iniciado");
    }

    protected void takeDown() {
        System.out.println(this.getLocalName() + "terminado");
    }
}
