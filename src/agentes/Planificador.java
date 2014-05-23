package agentes;

import java.util.ArrayList;
import java.util.Date;

import jade.core.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.domain.FIPANames;

@SuppressWarnings("serial")
public class Planificador extends Agent {
    private vistas.Planificador gui;

    protected void setup() {
        gui = new vistas.Planificador(this);
        gui.setVisible(true);
    }

    protected void takeDown() {
        gui.dispose();
        System.out.print(this.getLocalName() + " finalizado");
    }

    public ArrayList<String> buscarPersonas() {
        ArrayList<String> personas = new ArrayList<String>();

        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("persona");
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            for(int i = 0; i < result.length; i++) {
                personas.add(result[i].getName().getLocalName());
            }
        } catch(FIPAException fe) {
            fe.printStackTrace();
        }

        return personas;
    }

    public void aplicarPapel(String persona, String papel){
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID(persona, AID.ISLOCALNAME));
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
        msg.setContent(papel);

        // Agregar comportamiento AchieveREInitiator (Asignar papel a persona)
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
