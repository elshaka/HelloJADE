import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Vector;

import jade.core.*;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class Comprador extends Agent {
    /**
     * Serial para que Eclipse deje deje joder con la advertencia
     */
    private static final long serialVersionUID = 1520345756839421693L;
    private CompradorGUI gui = null;

    protected void setup() {
        System.out.println("Comprador iniciado");
        gui = new CompradorGUI(this);
        gui.setVisible(true);
    }

    protected void takeDown() {
        System.out.println("Comprador finalizado");
    }

    void buscarLibro(String titulo) {
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        msg.addReceiver(new AID("vendedor", AID.ISLOCALNAME));
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 5000));
        msg.setContent(titulo);
        addBehaviour(new ContractNetInitiator(this, msg) {
            /**
             * 
             */
            private static final long serialVersionUID = -8839651048576471707L;

            protected void handlePropose(ACLMessage propose, Vector v) {
                System.out.println("Vendedor " + propose.getSender() + " ofrece el libro en " + propose.getContent() + " BsF.");
            }

            protected void handleRefuse(ACLMessage refuse) {
                System.out.println("Vendedor " + refuse.getSender() + " no tiene el libro");
            }

            protected void handleFailure(ACLMessage failure) {
                if (failure.getSender().equals(myAgent.getAMS())) {
                    // Mensaje de la plataforma JADE: El destinatario no existe
                    System.out.println("El vendedor no existe");
                } else {
                    System.out.println("Vendedor " + failure.getSender() + " falló en realizar la venta");
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
                System.out.println("Se realizó la compra del libro al vendedor " + inform.getSender());
            }
        });
    }
}