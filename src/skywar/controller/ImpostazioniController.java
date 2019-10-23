
package skywar.controller;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


import skywar.model.Utility;

/**
 * Impostazioni.fxml Controller class
 * @author ErioPc
 */
public class ImpostazioniController{
        
        @FXML
	private   ComboBox<String> comboBox1;
	@FXML
	private ComboBox<String> comboBox2;
        @FXML
        private ComboBox<String> comboBox3;
        @FXML
        private Button buttonePlay ;
        
        private String NickName ;

    /**
     *
     */
     public   static String scelta ;

    /**
     *
     */
    public static String bullets ;

    /**
     *
     */
    public static String speed ;
        
        
        /**
	 * Inizializza la finestra dello schieramento
         * cambia lo stile del pulsante 'Play', imposta il nickname del giocatore passato come parametro
	 * inizializza la combobox per la scelta dell'aero 
         * inizializza la combobox per la scelta della proiettile
	 * inizializza la combobox per la scelta della velocita.
	 * @param nick  il nome del giocatore
	 */
	public void initial(String nick ) 
        {
            
                this.buttonePlay.setStyle(Utility.buttonStyle);
               
            
           
		this.NickName = nick;
		//this.label.setText(this.nickname);
 

		ObservableList<String> box1 = FXCollections.observableArrayList(
				("Symbolic" ),
				("Static" ),
				("Animated" ));
				 
                

		comboBox1.setItems(box1);

		//default:
		 this.comboBox1.getSelectionModel().select(0);
                 this.scelta =  comboBox1.getSelectionModel().getSelectedItem();

		//add Listener to ComboBox1
               	comboBox1.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>() 
                {

			@Override
			public void changed( ObservableValue<? extends String> observable, String oldValue, String newValue){

				 scelta = comboBox1.getSelectionModel().getSelectedItem();
                                    cb1Listener();

			}
		}); 
		

		this.comboBox2.getItems().clear();
		ObservableList<String> box2= FXCollections.observableArrayList("1", "2","3");

		comboBox2.setItems(box2);

		//default
		this.comboBox2.getSelectionModel().select(0);
                this.bullets=comboBox2.getSelectionModel().getSelectedItem();
                
                	//add Listener to ComboBox2
		comboBox2.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>() {

			@Override
			public void changed( ObservableValue<? extends String> observable, String oldValue, String newValue){

				bullets = comboBox2.getSelectionModel().getSelectedItem();
				cb2Listener();

			}
		});
                
                

		
                this.comboBox3.getItems().clear();
                ObservableList<String> box3=FXCollections.observableArrayList("Low","Medium","High");
                comboBox3.setItems(box3);
                //default
                this.comboBox3.getSelectionModel().select(0);
                this.speed=comboBox3.getSelectionModel().getSelectedItem();
                	//add Listener to ComboBox3
		comboBox3.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>() {

			@Override
			public void changed( ObservableValue<? extends String> observable, String oldValue, String newValue){

				speed = comboBox3.getSelectionModel().getSelectedItem();
				cb3Listener();

			}
		});
                
                
          cb3Listener();      
                
        }
   
         /**
         * Metodo che permette laccesso al metodo caricaAereo().
         */
        public void cb1Listener()
        {
        GiocoController gc1 = new GiocoController() ;
           gc1.caricaAereo();
        }
        
        
        /**
         * Metodo che permette lacceso al metodo caricaProiettile().
         */
        public void cb2Listener()
        {
            GiocoController gc2=new GiocoController();
            gc2.caricaProiettile();
        }
        
       /**
        * Metodo che permette lacceso al metodo Velocit();
        */ 
       public  void cb3Listener()
       {
            
            if(speed.equalsIgnoreCase("Low"))
                   {
                        
                       GiocoController.setSpeed(110);
                   }
                   if(speed.equalsIgnoreCase("Medium"))
                   {
                       
                       GiocoController.setSpeed(120);
                   }
                   if(speed.equalsIgnoreCase("High"))
                   {
                       
                       GiocoController.setSpeed(150);
                   }
       }
       
     
        
        /**
	 * Nasconde la finestra corrente e carica la finestra del gioco 
         * Chiama il metodo iniiziale di GiocoController.
	 */
	@FXML
	public void ButtonPlay()
        {

		try{

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/skywar/view/Gioco.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
                        stage.setResizable(false);
			//prendo il GicoController:
                        GiocoController controller = (GiocoController)fxmlLoader.getController();
                        controller.iniz(this.NickName);
                        stage.show();
                        
			

		

		}catch(IllegalStateException ie){
			System.out.println("! Errore: GiocoController.fxml non trovato!"+ie);
			ie.printStackTrace();
		}catch(IOException ioe){
			System.out.println("! Errore: fxmlLoader.load()"+ioe);
			ioe.printStackTrace();
		}catch(NullPointerException ne){
			System.out.println("! Errore: Parent root1 e' null! "+ne);
			ne.printStackTrace();
		}
                
                //nascondo la finestra corrente
			(this.buttonePlay.getScene().getWindow()).hide();

	}
        
       
        
        
        
     
                
          

	

  
    
}
