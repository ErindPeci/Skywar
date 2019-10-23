
package skywar.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;

/**
 * Classe che contiene dei metodi utilizzati spesso
 * 
 * @author ErioPc
 */
public class Utility {

	// il style per i pulsanti

    /**
     *
     */
	public static String buttonStyle = 
                "-fx-text-fill: #006464;\n" +
                "-fx-background-color: #dd1414;\n" +
                "-fx-border-radius: 20;\n" +
                "-fx-background-radius: 20;\n" +
                "-fx-padding: 5;";

	// il style per il bordo
	private static String borderStyle = "-fx-border-color: black;"
			+ "-fx-border-width: 3;"
			;

	/**
	 * Genera un warning
	 * 
	 * @param titolo il titolo della notifica
	 * @param headerText il header della notifica
	 * @param contenuto il contenuto della notifica
	 * @param tipo il tipo della notifica
	 */
	public static void generaWarning(String titolo, String headerText, String contenuto, String tipo){

		Alert alert = null;

		if(tipo.equalsIgnoreCase("info")){
			alert = new Alert(Alert.AlertType.INFORMATION);
		}

		if(tipo.equalsIgnoreCase("error")){
			alert = new Alert(Alert.AlertType.ERROR);
		}

		if(tipo.equalsIgnoreCase("warning")){
			alert = new Alert(Alert.AlertType.WARNING);
		}


		alert.setTitle(titolo);
		alert.setHeaderText(headerText);
		alert.setContentText(contenuto);

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setStyle(borderStyle);

		GridPane grid = (GridPane)dialogPane.lookup(".header-panel"); 
		grid.setStyle("-fx-text-background-color: Red;"
				+ "-fx-font-size: 20px;"
				+ "-fx-font-weight: bold;"
				+ "-fx-background-color: black;");

		dialogPane.lookup(".content.label").setStyle(
				"-fx-text-fill: Red;"
						+ "-fx-background-color: black ;");

		ButtonBar buttonBar = (ButtonBar)alert.getDialogPane().lookup(".button-bar");
		buttonBar.setStyle("-fx-background-color: black;");
		buttonBar.getButtons().forEach(b->b.setStyle( buttonStyle));

		alert.showAndWait();
	}
       

}