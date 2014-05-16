import java.util.ArrayList;

import jade.core.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


@SuppressWarnings("serial")
public class Planificador extends Agent {
    private PlanificadorGUI gui= null;
    
    protected void setup() {
        System.out.println(this.getLocalName() + " iniciado");
        gui = new PlanificadorGUI(this);
        gui.setVisible(true);
    }

    protected void takeDown() {
        System.out.println(this.getLocalName() + " terminado");
    }

    ArrayList<String> buscarPersonas() {
        ArrayList<String> personas = new ArrayList<String>();

        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd  = new ServiceDescription();
        sd.setType("persona");
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            for(int i = 0; i < result.length; i++) {
                personas.add(result[i].getName().getLocalName());
            }
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        return personas;
    }
}
