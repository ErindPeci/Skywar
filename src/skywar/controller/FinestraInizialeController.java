 
package skywar.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import skywar.model.Informazione;
import skywar.model.Utility;

/**
 * FinestraIniziale.fxml Controller class
 * @author ErioPc
 */
public class FinestraInizialeController {
    
        @FXML
        private AnchorPane anchorPane;
        @FXML
        private Button loginButton;
        
        
        
        
        /**
         * Inizializazzione della finestra iniziale.
         */ 
        public void ini()
        {
                loginButton.setStyle(Utility.buttonStyle);
        }
        
        
        /**
	 * Visualizza l'informazione del Software: nome degli sviluppatori, ecc
	 */
       @FXML
	public void info()
        {

		Stage stage = new Stage();
		Informazione info = new Informazione();

		try {
			info.start(stage);
		} catch (Exception e) {
			System.out.println("! Errore info()"+e);
		}
        }
        
        
         /**
	 * Per uscire dal gioco
	 */
       @FXML
        public void exit(){
                Platform.exit();
	}
        
         

        /**
         * Nasconde la finestra  e apre la nuova finestra di Login.
         * @param e ActionEvent
         */
        @FXML
        public void loginButton(ActionEvent e)
        {
                
                try{
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/skywar/view/Login.fxml"));
                    Parent root= (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    LoginController controller = (LoginController)fxmlLoader.getController();
                    controller.init();
                    stage.show();
                    stage.setTitle("");
                    
                }catch(IllegalStateException ie) {
			System.out.println("Error: Login.fxml non trovato!"+ie);	
		}catch(IOException ioe){
			System.out.println("LoginController Errore: fxmlLoader.load()"+ioe);
		}catch(NullPointerException ne){
			System.out.println("Error: Variabile Parent e' null!"+ ne);
		}
                
                
                //nascondo la finestra corrente
		(this.loginButton.getScene().getWindow()).hide();
                    
                    
           
        }

}


