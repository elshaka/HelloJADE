import jade.core.Runtime; 
import jade.core.Profile; 
import jade.core.ProfileImpl; 
import jade.wrapper.*;

import javax.swing.UIManager;

public class HelloJADE {

    public static void main(String[] args) {
        try {
            // Usar look & feel nativo en las vistas
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Plataforma JADE
        Runtime runtime = Runtime.instance();

        // Perfil predetermiando (localhost:1099)
        Profile profile = new ProfileImpl();

        // Contenedor principal
        AgentContainer mainContainer = runtime.createMainContainer(profile);

        // Crear agentes
        try {
            // RMA (Jade Boot GUI)
            AgentController ac = mainContainer.createNewAgent("rma",
                    "jade.tools.rma.rma", null);
            ac.start();

            // Planificador
            ac = mainContainer.createNewAgent("Planificador",
                    "agentes.Planificador", null);
            ac.start();

            // 4 personas
            String[] personas = {"Eleazar", "Stefan", "German", "Bachaquero"};
            for(int i = 0; i < personas.length; i++) {
                ac = mainContainer.createNewAgent(personas[i],
                        "agentes.Persona", null);
                ac.start();
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
