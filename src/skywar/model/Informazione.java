package skywar.model;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe per la finestra dell'informazione relativa al Software
 * 
 * @author ErioPc
 */
public class Informazione extends Application {

	private double WIDTH =320, HEIGHT = 270; 


	/**
	 * Inserisce un testo in una Hbox, imposta la grandezza del testo,
	 * il colore, il tipo di Font (carattere) e realizza l'animazione
	 * 
	 * @param parent la HBox
	 * @param text il testo da visualizzare
	 */
	public void scrollingText(HBox parent, String text) {

		Text scrollingText = new Text(text); 
		parent.getChildren().add(scrollingText); 
		scrollingText.setLayoutX(0); 
		scrollingText.setLayoutY(20); 
		scrollingText.setWrappingWidth(WIDTH); 
		scrollingText.setFont(Font.font ("Arial", FontWeight.BOLD, 16));
		scrollingText.setFill(Color.WHITE);

		TranslateTransition tt = new TranslateTransition(Duration.millis(45000), scrollingText); 
		tt.setFromY(770);
		tt.setToY(-760);
		tt.setCycleCount(2);
		tt.setAutoReverse(false);
		tt.play(); 
	} 

	@Override
	public void start(Stage primaryStage) throws Exception {

		 

		final HBox scrollingArea = new HBox();  

		scrollingText(scrollingArea, 
				"\t\tSKYWAR\n\n"
						+"\t\n\n"
						+ "\t\tCreato da  studente :\n\n"
						+"\t\t\n\n"
						+"\t\t»Erind Peci\n\n"
						+"\t\t\n\n"
						+"\t\tdell'universita' di Bologna\n\n"
						+"\t\t\n\n"
						+"\t\t ( UNIBO )\n\n"
						+"\t\t\n\n"
						+"\t\tCorso di laurea in:\n\n"
						+"\t\t\n\n"
						+"\t\tInformatica per il Management\n\n"
						+"\t\t\n\n"
						+"\t\tMateria :\n\n"
						+"\t\t\n\n"
						+"\t\tProgrammazione Internet\n\n"
						+"\t\t\n\n"
						+"\t\tProfessore :\n\n"
						+"\t\t\n\n"
						+"\t\tAntonio Messina\n\n"
						+"\t\t\n\n"
						+"\t\t\n\n"
						+"\t\t bibliografia: \n\n"
						+"\t\t\n\n"
						+"\t\t* Programmazione di base e avanzata con Java\n\n"
						+"\t\t\n\n"
						+"\t\t* Editore	Pearson, 2014\n\n"
						+"\t\t\n\n"
						+"\t\t* Autore: Walter Savitch\n\n"
						+"\t\t\n\n"
						+"\t\t\n\n"
						+"\t\t\t\t2016\n\n");  


		VBox root = new VBox(); 
		//root.setSpacing(100); ??  
		root.setFillWidth(true);  
		root.setAlignment(Pos.CENTER);  
		root.getChildren().add(scrollingArea);  
		root.setStyle("-fx-background-image: url('"+ "risorse/BG_credits.gif" +"'); " +
				"-fx-background-position: center center; ");

		Scene scene = new Scene(root, WIDTH, HEIGHT);  
		primaryStage.setTitle("Informazione sul Software");  
		primaryStage.setScene(scene);  
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.show(); 
 

	}


}
