import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Vector;

import jade.core.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetInitiator;
import jade.proto.ContractNetResponder;

@SuppressWarnings("serial")
public class Persona extends Agent {
    private CompradorGUI gui = null;
    private String otraPersona;

    protected void setup() {
        // Leer destinatario desde el argumento
        Object[] args = this.getArguments();
        if (args != null && args.length > 0) {
            this.otraPersona = (String) args[0];
        } else {
            System.out.println("No se especificó destinatario");
            if (this.gui != null) {
                this.gui.setVisible(false);
                this.gui = null;
            }
            this.doDelete();
        }

        // Registrar agente como "persona"
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(this.getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("persona");
        sd.setName(this.getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        // Mostrar GUI
        gui = new CompradorGUI(this);
        gui.setVisible(true);

        // Agregar comportamiento ContractNetResponder
        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
                MessageTemplate.MatchPerformative(ACLMessage.CFP)
        );
        addBehaviour(new ContractNetResponder(this, template) {
            /**
             * 
             */
            private static final long serialVersionUID = 2297530524097875696L;

            protected ACLMessage handleCfp(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
                ACLMessage propose = cfp.createReply();
                propose.setPerformative(ACLMessage.PROPOSE);
                propose.setContent(String.valueOf(1000));
                return propose;
            }

            protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
                ACLMessage inform = accept.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                return inform;
            }
        });

        System.out.println(this.getLocalName() + " iniciado con " + otraPersona);
    }

    protected void takeDown() {
        // Eliminar agente del registro
        try {
            DFService.deregister(this);
        } catch (Exception e) {}
        System.out.println(this.getLocalName() + " finalizado");
    }

    void buscarLibro(String titulo) {
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        msg.addReceiver(new AID(this.otraPersona, AID.ISLOCALNAME));
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 5000));
        msg.setContent(titulo);
        addBehaviour(new ContractNetInitiator(this, msg) {
            /**
             * 
             */
            private static final long serialVersionUID = -8839651048576471707L;

            protected void handlePropose(ACLMessage propose, Vector v) {
                System.out.println("Vendedor " + propose.getSender().getLocalName() + " ofrece el libro en " + propose.getContent() + " BsF.");
            }

            protected void handleRefuse(ACLMessage refuse) {
                System.out.println("Vendedor " + refuse.getSender().getLocalName() + " no tiene el libro");
            }

            protected void handleFailure(ACLMessage failure) {
                if (failure.getSender().equals(myAgent.getAMS())) {
                    // Mensaje de la plataforma JADE: El destinatario no existe
                    System.out.println("El vendedor no existe");
                } else {
                    System.out.println("Vendedor " + failure.getSender().getLocalName() + " falló en realizar la venta");
                }
            }

            protected void handleAllResponses(Vector responses, Vector acceptances) {
                try { // Aceptar la primera oferta
                    ACLMessage response = (ACLMessage) responses.firstElement();
                    if (response.getPerformative() == ACLMessage.PROPOSE) {
                        ACLMessage reply = response.createReply();
                        reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                        acceptances.addElement(reply);
                    } else { // No se recibio oferta
                        System.out.println("No se consiguió el libro");
                    }
                    
                } catch (NoSuchElementException e) { // No hubo ninguna respuesta
                    System.out.println("No se consiguieron vendedores");
                }
            }

            protected void handleInform(ACLMessage inform) {
                System.out.println("Se realizó la compra del libro al vendedor " + inform.getSender().getLocalName());
            }
        });
    }

    void venderLibro(String titulo, String precio) {
        // TODO Auto-generated method stub
    }
}