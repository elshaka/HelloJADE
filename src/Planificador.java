import java.util.ArrayList;

import jade.core.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.domain.FIPANames;

import java.util.Date;

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
    
    // Inicia el protocolo FIPARequest para asignar el papel a una persona
    void aplicarPapel(String persona, String papel){
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID((String) persona, AID.ISLOCALNAME));
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
        msg.setContent(papel);

        addBehaviour(new AchieveREInitiator(this, msg) {
            protected void handleInform(ACLMessage inform) {
                System.out.println(inform.getSender().getLocalName() + " recibió de manera correcta su papel");
            }

            protected void handleRefuse(ACLMessage refuse) {
                System.out.println(refuse.getSender().getLocalName() + ": " + refuse.getContent());
            }

            protected void handleFailure(ACLMessage failure) {
                if (failure.getSender().equals(myAgent.getAMS())) {
                    // Mensaje de la plataforma JADE: El destinatario no existe
                    System.out.println("La persona no existe");
                } else {
                    System.out.println(failure.getSender().getLocalName() + ": " + failure.getContent());
                }
            }
        });
    }
}
