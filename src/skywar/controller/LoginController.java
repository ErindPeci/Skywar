


package skywar.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import skywar.model.Informazione;
import skywar.model.Utility;

/**
 * Login.fxml Controller class
 * @author ErioPC
 */
public class LoginController {
        
        @FXML
        private TextField textField ;
        @FXML
        private Button continueButton ;
        @FXML
	private MenuItem menuItemExit;
	@FXML
	private MenuItem menuItemInfo;

        
    
	private String titolo;
	private String headerText;
	private String contenuto;
       
        /**
         * Initializes the controller class.
         * Cambio il stile dell pulsante.
         */
        public void init() 
        {
            this.continueButton.setStyle(Utility.buttonStyle);
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
	 * Controlla che il nickname inserito dall giocatore è corretto
	 * Se tutto è ok hides la finestra corrente e carica la finestra d'impostazione del gioco .
         * Se no da warning.
	 */
        @FXML
        public void ButtonContinue()
        {
                Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");

		String nickname = this.textField.getText().trim();

		Matcher control = pattern.matcher(nickname);
                    
                    if(!control.matches() || nickname.length()==0)
                    {
                            
                            this.titolo = "[ WARNING ]";
                            this.headerText = "  Nickname non valido!";
                            this.contenuto = "Il nickname non puo  avere spazi o caratteri speciali!";

                            Utility.generaWarning(titolo, headerText, contenuto, "warning");
                    }
                    
                    else
                    {      
                           try{

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/skywar/view/Impostazioni.fxml"));
				Parent root11 = (Parent)loader.load();
				//get controller from ImpostazioniController:
				
				Stage stage = new Stage();
				stage.setScene(new Scene(root11));
				stage.setResizable(false);
				ImpostazioniController controller = (ImpostazioniController)loader.getController();
                                //ini:
				 controller.initial(nickname);
				stage.show();

			}catch(IllegalStateException ie) {
				System.out.println("!Errore: Impostazioni.fxml non trovato!"+ie);	
			}catch(IOException ioe){
				System.out.println("!LoginController Errore: fxmlLoader.load()"+ioe);
			}catch(NullPointerException ne){
				System.out.println("!Errore: Variabile Parent e' nulll!"+ne);
			}

			//nascondo la finestra corrente
			(this.continueButton.getScene().getWindow()).hide();
                    }
                
                
                
                
                
        }   
        
        
        

     
    
}
