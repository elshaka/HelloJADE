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
import jade.proto.AchieveREResponder;
import jade.proto.ContractNetInitiator;
import jade.proto.ContractNetResponder;

@SuppressWarnings("serial")
public class Persona extends Agent {
    private CompradorGUIPrincipal guiComprador;
    private VendedorGUI guiVendedor;
    public String papel;
    private String otraPersona;

    protected void setup() {
        // Leer destinatario desde el argumento
        Object[] args = this.getArguments();
        if (args != null && args.length > 0) {
            this.otraPersona = (String) args[0];
        }

        guiComprador = new CompradorGUIPrincipal(this);
        guiVendedor = new VendedorGUI(this);

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

        // Agregar comportamiento ContractNetResponder
        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
                MessageTemplate.MatchPerformative(ACLMessage.CFP)
        );
        addBehaviour(new ContractNetResponder(this, template) {
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

        // Agregar comportamiento AchieveREResponder
        template = MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST)
        );
        addBehaviour(new AchieveREResponder(this, template) {
            protected ACLMessage prepareResponse(ACLMessage request) throws NotUnderstoodException, RefuseException {
                if(request.getContent().equals(papel)) {
                    throw new RefuseException("El papel a asignar es el mismo que el actual");
                }
                ACLMessage agree = request.createReply();
                agree.setPerformative(ACLMessage.AGREE);
                return agree;
            }

            protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
                try {
                    actualizarPapel(request.getContent());
                    ACLMessage inform = request.createReply();
                    inform.setPerformative(ACLMessage.INFORM);
                    return inform;
                } catch (FIPAException fe) {
                    throw new FailureException(fe.getMessage());
                }
            }
        });

        System.out.println(this.getLocalName() + " iniciado");
    }

    protected void takeDown() {
        // Eliminar la GUI
        this.guiComprador = null;
        this.guiVendedor = null;
        // Eliminar agente del registro
        try {
            DFService.deregister(this);
        } catch (Exception e) {}
        System.out.println(this.getLocalName() + " finalizado");
    }

    void actualizarPapel(String papel) throws FIPAException {
        this.papel = papel;

        // Nuevo registro
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(this.getAID());

        // Papel de persona
        ServiceDescription sd = new ServiceDescription();
        sd.setType("persona");
        sd.setName(this.getLocalName());
        dfd.addServices(sd);

        // Papel de comprador/vendedor
        sd = new ServiceDescription();
        sd.setType(papel);
        sd.setName(this.getLocalName());
        dfd.addServices(sd);

        // Actualizar registro y actualizar GUI
        DFService.modify(this, dfd);

        cerrarVistas();
        switch(papel) {
        case "Comprador":
            guiComprador.setVisible(true);
            break;
        case "Vendedor":
            guiVendedor.setVisible(true);
            break;
        }
    }

    void cerrarVistas(){
        if (this.guiComprador != null) {
            this.guiComprador.setVisible(false);
        }
        if (this.guiVendedor != null) {
            this.guiVendedor.setVisible(false);
        }
    }

    void buscarLibro(String titulo) {
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        msg.addReceiver(new AID(this.otraPersona, AID.ISLOCALNAME));
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 5000));
        msg.setContent(titulo);

        addBehaviour(new ContractNetInitiator(this, msg) {
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