package skywar.controller;

import java.util.ArrayList;

import skywar.Skywar;
import skywar.model.Utility;
import skywar.model.Giocatore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *Classifica.fxml Controller class
 * @author ErioPc 
 */
public class ClassificaController 
{
        /**
         * labelMatrix dove si posiziano i risultati
         */
	@FXML
	private Label[][] labelMatrix = new Label[10][3];
        
        /**
         * GridPane griglia
         */
	@FXML
	private GridPane griglia;
        
        /**
         * buttone per rigiocare
         */
	@FXML
	private Button newGame;
        
        /**
         * buttone per Exit
         */
	@FXML
	private Button Quit;

 
	private String style="-fx-border-color: black; -fx-effect: dropshadow(gaussian, cyan, 50, 0, 0, 0)";
	private String styleTesto="-fx-border-color:black; -fx-effect: dropshadow(gaussian, cyan, 50, 0, 0, 0); -fx-alignment: CENTER";
	
        /**
         * ArraYlist per i giocatori che si registrano
         */
        private ArrayList<Giocatore> listaGiocatori;

	/**
	 * Inizializza la finestra della classifica, imposta il risultato della partita e carica i giocatori
	 * Inoltre imposta lo Style del pulsante "New Game" e del pulsante "Exit" 
	 * @param lista con i giocatori
	 */
	public void ini( ArrayList<Giocatore> lista) {

	 
		this.listaGiocatori = lista;
		this.newGame.setStyle(Utility.buttonStyle);
		this.Quit.setStyle(Utility.buttonStyle);

		
		caricaClassifica(listaGiocatori);
	}

	/**
	 * Carica tutti i giocari con i dati della rispettiva partita: punteggio, tempo della partita e data.
	 * Se la partita è finita perche' uno dei giocatori si è arreso allora viene visualizzato un messaggio
	 * che indica che tale partita non è considerata valida quindi non verra' registrata.
	 * 
	 * @param lista la lista dei giocatori da caricare
	 */
	public void caricaClassifica(ArrayList<Giocatore> lista) throws ArrayIndexOutOfBoundsException{

		for (int i = 0; i < labelMatrix.length; i++) {
			for (int j = 0; j < labelMatrix[i].length; j++) {
				labelMatrix[i][j] = new Label();
				labelMatrix[i][j].setPrefSize(245, 223);
				labelMatrix[i][j].setStyle(this.style);

				//inizializzo a O 
				labelMatrix[i][j].setTextFill(Color.BLACK);


				griglia.add(labelMatrix[i][j], j, i);
			}
		}

		if(listaGiocatori == null){

			System.out.println("Errore");

		}else{

			if(lista.size() >= 10){

				for (int i = 0; i < 10; i++) {

					labelMatrix[i][0].setText(lista.get(i).getNickName());
					labelMatrix[i][0].setStyle(styleTesto);

					labelMatrix[i][1].setText(lista.get(i).getPunteggio()+"");
					labelMatrix[i][1].setStyle(styleTesto);

					
					labelMatrix[i][2].setText(lista.get(i).getData());
					labelMatrix[i][2].setStyle(styleTesto);

				}

			}else{
				//la dimensione è minore di 10
				//carico i records disponibili
				for (int i = 0; i < lista.size(); i++) {

					labelMatrix[i][0].setText(lista.get(i).getNickName());
					labelMatrix[i][0].setStyle(styleTesto);

					labelMatrix[i][1].setText(lista.get(i).getPunteggio()+"");
					labelMatrix[i][1].setStyle(styleTesto);

		

					labelMatrix[i][2].setText(lista.get(i).getData());
					labelMatrix[i][2].setStyle(styleTesto);

				}
			}
		}

	}

	/**
	 * Metodo che controlla il buttone Quit per uscire dal gioco
	 */
	@FXML
	public void exit(){
		Stage stage = (Stage) Quit.getScene().getWindow();
		stage.close();
                System.exit(0);
	}

	/**
	 * Metodo che controlla il buttone New Game  per iniziare una nuova partita
	 */
	@FXML
	public void newGame(){

		Stage stage = (Stage) newGame.getScene().getWindow();
		stage.close();
		Skywar.getStageIniziale().show();

	}

}