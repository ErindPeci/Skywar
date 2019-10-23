
package skywar;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import skywar.controller.FinestraInizialeController;

/** 
 * Main class del progetto      
 * @author ErioPc : erind.peci@studio.unibo.it
 */
public class Skywar extends Application {
 
    /**
     * stage del applicazione
     */
    private static Stage stage ;
    
    /**
     * anchorpane principale
     */
    private AnchorPane anchorPane ;
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("Fine programma");
    }
   
    
    
      @Override
    public void start(Stage primaryStage) throws Exception {
        
            stage = primaryStage;
            stage.setTitle("SKYWAR");
            

            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Skywar.class.getResource("/skywar/view/FinestraIniziale.fxml"));
                
                anchorPane =loader.load();
                
                 FinestraInizialeController controller = (FinestraInizialeController)loader.getController() ;
                
                Scene scene = new Scene(anchorPane);
                
                stage.setScene(scene);
                stage.setResizable(false);
                 controller.ini();
                stage.show();
                
                
                

            } catch(IllegalStateException ie) {
                System.out.println("Errore:FinestraIniziale.fxml non trovata " + ie);
            } catch(NullPointerException ne) {
                System.out.println("Errore:Variabile AnchorPane è null" + ne);
            }
    }
        
    /**
     *
     * @return Stage
     */
    public static Stage getStageIniziale()
    {
            return stage;
    }
    
       
}

