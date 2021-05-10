package it.polito.tdp.TE_impostazione.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.TE_impostazione.controller.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class DettagliController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private BorderPane tutto;

    @FXML
    private VBox corpo;

    @FXML
    private ImageView immagine;

    @FXML
    private TextArea txtTesto;

    @FXML
    void initialize() {
    	assert tutto != null : "fx:id=\"tutto\" was not injected: check your FXML file 'dettagli.fxml'.";
        assert corpo != null : "fx:id=\"corpo\" was not injected: check your FXML file 'dettagli.fxml'.";
        assert immagine != null : "fx:id=\"immagine\" was not injected: check your FXML file 'dettagli.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'dettagli.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model=m;
    	txtTesto.setStyle("-fx-font-family: monospace");
    	File f=new File("im/perDettagli.jpg");
    	Image logo=new Image(f.toURI().toString());
    	immagine.setImage(logo);
    	tutto.styleProperty().set("-fx-background-color:#d3d3d3");
    	setTesto();
    }
    
    private void setTesto() {
    	StringBuilder sb=new StringBuilder();
    	
    	sb.append(String.format("%-15s ", "Scorer:"));
    	sb.append(String.format("%-120s\n", "Giocatore che ha come caratteristica quella di segnare molti punti a partita"));
    	sb.append(String.format("%-15s ", "Assistman:"));
    	sb.append(String.format("%-120s\n", "Giocatore che ha come caratteristica quella di fornire molti assist a partita"));
    	sb.append(String.format("%-15s ", "Stoppatore:"));
    	sb.append(String.format("%-120s\n", "Giocatore con un elevato numero di stoppate a partita"));
    	sb.append(String.format("%-15s ", "Uomo squadra:"));
    	sb.append(String.format("%-120s\n", "Basandosi sulla statistica del plus-minus, maggiore e' il valore di tale statistica piu' il giocatore viene considerato uomo-squadra")); //////////
    	sb.append(String.format("%-15s ", "Tiratore da 3:"));
    	sb.append(String.format("%-120s\n", "Giocatore con un elevata percentuale del tiro da 3 punti e con un numero di tentativi significativo"));
    	sb.append(String.format("%-15s ", "Tiratore da 2:"));
    	sb.append(String.format("%-120s\n", "Giocatore con un elevata percentuale del tiro da 2 punti e con un numero di tentativi significativo"));
    	sb.append(String.format("%-15s ", "Rimbalzista:"));
    	sb.append(String.format("%-120s\n", "Giocatore con un elevato numero di rimbalzi a partita"));
    	sb.append(String.format("%-15s ", "G"));
    	sb.append(String.format("%-120s\n", "Indica il ruolo di Guardia"));
    	sb.append(String.format("%-15s ", "F"));
    	sb.append(String.format("%-120s\n", "Indica il ruolo di Ala (dall'inglese Forward)"));
    	sb.append(String.format("%-15s ", "C"));
    	sb.append(String.format("%-120s\n", "Indica il ruolo di Centro"));
    	
    	txtTesto.appendText(sb.toString());
    	txtTesto.home();
    }
}
