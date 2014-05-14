import jade.core.*;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

public class Vendedor extends Agent {
    /**
     * Serial para que Eclipse deje deje joder con la advertencia
     */
    private static final long serialVersionUID = 4479575386140888632L;

    protected void setup() {
        System.out.println("Vendedor iniciado");
        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
                MessageTemplate.MatchPerformative(ACLMessage.CFP)
        );
        addBehaviour(new ContractNetResponder(this, template) {
            /**
             * 
             */
            private static final long serialVersionUID = 2297530524097875696L;

            @Override
            protected ACLMessage handleCfp(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
                ACLMessage propose = cfp.createReply();
                propose.setPerformative(ACLMessage.PROPOSE);
                propose.setContent(String.valueOf(1000));
                return propose;
            }

            @Override
            protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
                ACLMessage inform = accept.createReply();
                inform.setPerformative(ACLMessage.INFORM);
                return inform;
            }
        });
    }

    protected void takeDown() {
        System.out.println("Vendedor finalizado");
    }
}