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
import java.util.Vector;


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
    
    //Comunica como FIPARequestInitiatorAgent
    void papelPersonaIniciador(String persona, String papel){
            System.out.println("Asigna el papel de"+papel+", a la persona."+persona);
            
            // Fill the REQUEST message
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID((String) persona, AID.ISLOCALNAME));
            
                msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                // We want to receive a reply in 10 secs
                msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
                msg.setContent(papel);
                
                addBehaviour(new AchieveREInitiator(this, msg) {
                    protected void handleInform(ACLMessage inform) {
                        System.out.println("La Persona "+inform.getSender().getName()+" recibio de manera correcta su papel");
                    }
                    protected void handleRefuse(ACLMessage refuse) {
                        System.out.println("La Persona "+refuse.getSender().getName()+" NO recibio de manera correcta su papel");
                    }
                    protected void handleFailure(ACLMessage failure) {
                        if (failure.getSender().equals(myAgent.getAMS())) {
                            // FAILURE notification from the JADE runtime: the receiver
                            // does not exist
                            System.out.println("Responder does not exist");
                        }
                        else {
                            System.out.println("Agent "+failure.getSender().getName()+" failed to perform the requested action");
                        }
                    }
                    protected void handleAllResultNotifications(Vector notifications) {
                        if (notifications.size() == 0) {
                            // Some responder didn't reply within the specified timeout
                            System.out.println("Timeout expired: missing  responses");
                        }
                    }
                } );
    }
}
