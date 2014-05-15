import jade.core.*;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

@SuppressWarnings("serial")
public class Planificador extends Agent {
    protected void setup() {
        System.out.println(this.getLocalName() + " iniciado");

        // Buscar personas cada segundo
        addBehaviour(new TickerBehaviour(this, 1000) {
            protected void onTick() {
                buscarPersonas();
            } 
        });
    }

    protected void takeDown() {
        System.out.println(this.getLocalName() + " terminado");
    }

    void buscarPersonas() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd  = new ServiceDescription();
        sd.setType("persona");
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            System.out.println(result.length + " personas encontradas:" );
            for(int i = 0; i < result.length; i++) {
                System.out.println(" " + result[i].getName().getLocalName());
            }
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }
}
