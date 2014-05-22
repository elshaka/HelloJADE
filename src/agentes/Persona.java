package agentes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import modelos.Libro;
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

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class Persona extends Agent {
    private gui.Persona gui;
    public String papel;
    private Libro libro;
    private int dineroDisponible;
    private ArrayList<String> vendedores;

    protected void setup() {
        gui = new gui.Persona(this);
        gui.setVisible(true);

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
                if(papel.equals("Comprador")) {
                    throw new RefuseException("No soy comprador");
                }
                ArrayList<Libro> libros = gui.getLibros();
                libro = new Libro (cfp.getContent());
                if (libros.contains(libro)) {
                    ACLMessage propose = cfp.createReply();
                    propose.setPerformative(ACLMessage.PROPOSE);
                    propose.setContent(String.valueOf(libros.get(libros.indexOf(libro)).getPrecio()));
                    return propose;
                }
                else {
                 // We refuse to provide a proposal
                    System.out.println("Agent "+getLocalName()+": Refuse");
                    throw new RefuseException("No tengo el libro");
                }
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
        gui.setVisible(false);
        gui = null;
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

        gui.setPapel(papel);
    }

    public ArrayList<String> buscarVendedores() {
        ArrayList<String> vendedores = new ArrayList<String>();

        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Vendedor");
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            for(int i = 0; i < result.length; i++) {
                vendedores.add(result[i].getName().getLocalName());
            }
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        return vendedores;
    }
    
    public void buscarLibro(Libro libro) {
        ACLMessage msg = new ACLMessage(ACLMessage.CFP);
        vendedores = buscarVendedores();
        Iterator<String> it = vendedores.iterator();
        while(it.hasNext())
        {
            String vendedor = it.next();
            msg.addReceiver(new AID(vendedor, AID.ISLOCALNAME));
        }
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 5000));
        msg.setContent(libro.getNombre());
        dineroDisponible = libro.getPrecio();

        addBehaviour(new ContractNetInitiator(this, msg) {
            protected void handlePropose(ACLMessage propose, Vector v) {
                System.out.println("Vendedor " + propose.getSender().getLocalName() + " ofrece el libro en Bs." + propose.getContent());
            }

            protected void handleRefuse(ACLMessage refuse) {
                System.out.println(refuse.getSender().getLocalName() + ": " + refuse.getContent());
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
                try { // Aceptar la mejor propuesta
                    int bestProposal = dineroDisponible; //Comprueba que la propuesta entre en el presupuesto
                    @SuppressWarnings("unused")
                    AID bestProposer = null;
                    ACLMessage accept = null;
                    Enumeration e = responses.elements();
                    while (e.hasMoreElements()) {
                        ACLMessage response = (ACLMessage) e.nextElement();
                        if (response.getPerformative() == ACLMessage.PROPOSE) {
                            ACLMessage reply = response.createReply();
                            reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                            acceptances.addElement(reply);
                            int proposal = Integer.parseInt(response.getContent());
                            if (proposal <= bestProposal) {
                                bestProposal = proposal;
                                bestProposer = reply.getSender();
                                accept = reply;
                            }
                        }
                    }
                    // Aceptar propuesta del Vendedor mas economico
                    if (accept != null) {
                        accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                    } else {
                        if (acceptances.isEmpty()){
                            gui.aviso("No se consiguió el libro");
                        }
                        else {
                            gui.aviso("Dinero insuficiente para comprar el libro");
                        }
                    }
                } catch (NoSuchElementException e) { // No hubo ninguna respuesta
                    gui.aviso("No se consiguieron vendedores");
                }
            }

            protected void handleInform(ACLMessage inform) {
                System.out.println("Se realizó la compra del libro al vendedor " + inform.getSender().getLocalName());
            }
        });
    }
}