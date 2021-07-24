package it.polito.tdp.TE_impostazione.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.TE_impostazione.controller.model.Giocatore;
import it.polito.tdp.TE_impostazione.controller.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AcquistaController {

	private Model model;
	private List<Giocatore> incedibili=new ArrayList<Giocatore>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lbnAvviso;

    @FXML
    private Label lbnGiocatoriDaAcquistare;
    
    @FXML
    private ImageView foto;

    @FXML
    private Label lbnListaIncedibili;

    @FXML
    private Label lbnGiocatoriIncedibili;

    @FXML
    private TableView<Giocatore> tvRoster;

    @FXML
    private TableColumn<Giocatore, String> tcRoster1; //nome

    @FXML
    private TableColumn<Giocatore, Float> tcRoster2; //punti

    @FXML
    private TableColumn<Giocatore, Float> tcRoster3; //assist

    @FXML
    private TableColumn<Giocatore, Float> tcRoster4; //rimbalzi

    @FXML
    private TableView<Giocatore> tvPossibilita;

    @FXML
    private TableColumn<Giocatore, String> tcAcquista1; //nome

    @FXML
    private TableColumn<Giocatore, String> tcAcquista2; //squadra

    @FXML
    private TableColumn<Giocatore, Float> tcAcquista3; //punti

    @FXML
    private TableColumn<Giocatore, Float> tcAcquista4; //assist

    @FXML
    void doIncedibili(ActionEvent event) {
    	b=0;
    	Giocatore g=tvRoster.getSelectionModel().getSelectedItem();
    	if(g!=null && incedibili.size()<5) {
    		if(incedibili.contains(g)) {
    			incedibili.remove(g);
    			setLabelIncedibili();
    			return;
    		}
    		else {
    			incedibili.add(g);
    			setLabelIncedibili();
    			return;
    		}
    	}
    }
    
    private void setLabelIncedibili() {
    	lbnGiocatoriIncedibili.setText("");
    	
    	String s="";
    	if(incedibili.size()>0) {
    		lbnListaIncedibili.setText("Lista:");
    	for(int i=0;i<incedibili.size()-1;i++)
    		s=s+""+incedibili.get(i).getNome()+"\n";
    	s=s+""+incedibili.get(incedibili.size()-1).getNome();
    	lbnGiocatoriIncedibili.setText(s);
    	}
    	else {
    		lbnListaIncedibili.setText("");
    	}
    }

    private int b=0;
    private int c=-1;
    private int cont=0;
    private List<List<Giocatore>> possibilita=new ArrayList<List<Giocatore>>();
    @FXML
    void doPossibilita(ActionEvent event) {
    	
    	
    	if(b==0) {
    		lbnAvviso.setText("");
    		ObservableList<Giocatore> result=FXCollections.observableArrayList();
    		
        	result.clear();
    		b=1;
    		long inizio=System.currentTimeMillis();
    		possibilita=model.trovaPossibilita(incedibili);
    		long fine=System.currentTimeMillis();
        	double tempo=((double) fine-inizio)/1000;
        	System.out.println("tempo= "+tempo);
    		if(possibilita==null) {
    			lbnAvviso.setText("Non c'è bisogno di cedere giocatori"); 
    			return;
    		}
    		System.out.println(possibilita.size());
    		c=possibilita.size();
    		
    		if(c==0) {
    			lbnAvviso.setText("Nessuna combinazione possibile");
    			return;
    		}
    		
    		result.addAll(possibilita.get(0));
    		System.out.println(result.size());
    		
    		tvPossibilita.setItems(result);
    		tcAcquista1.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
    		tcAcquista2.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("points"));
    		tcAcquista3.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
    		tcAcquista4.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		cont++;
    		return;
    	}
    	
    	if(b==1 && c>cont) {
    		lbnAvviso.setText("");
    		if(c==0) {
    			lbnAvviso.setText("Nessuna combinazione possibile");
    			return;
    		}
    		ObservableList<Giocatore> result=FXCollections.observableArrayList();
        	result.clear();
    		if(cont==0) {
    			result.addAll(possibilita.get(0));
        		tvPossibilita.setItems(result);
        		tcAcquista1.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcAcquista2.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("points"));
        		tcAcquista3.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcAcquista4.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
        		cont++;
        		return;
    		}
    		else {
    			result.addAll(possibilita.get(cont));
        		tvPossibilita.setItems(result);
        		tcAcquista1.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcAcquista2.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("points"));
        		tcAcquista3.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcAcquista4.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
        		cont++;
        		return;
    		}
    	}
    	
    	if(b==1 && c==cont) {
    		if(possibilita==null) {
    			lbnAvviso.setText("Non c'è bisogno di cedere giocatori");
    			return;
    		}
    		if(c==0) {
    			lbnAvviso.setText("Nessuna combinazione possibile");
    			return;
    		}
    		lbnAvviso.setText("Possibilità finite");
    		b=1;
    		cont=0;
    	}
    }
    

    @FXML
    void initialize() {
        assert lbnGiocatoriDaAcquistare != null : "fx:id=\"lbnGiocatoriDaAcquistare\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert lbnListaIncedibili != null : "fx:id=\"lbnListaIncedibili\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert lbnGiocatoriIncedibili != null : "fx:id=\"lbnGiocatoriIncedibili\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tvRoster != null : "fx:id=\"tvRoster\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcRoster1 != null : "fx:id=\"tcRoster1\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcRoster2 != null : "fx:id=\"tcRoster2\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcRoster3 != null : "fx:id=\"tcRoster3\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcRoster4 != null : "fx:id=\"tcRoster4\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tvPossibilita != null : "fx:id=\"tvPossibilita\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcAcquista1 != null : "fx:id=\"tcAcquista1\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcAcquista2 != null : "fx:id=\"tcAcquista2\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcAcquista3 != null : "fx:id=\"tcAcquista3\" was not injected: check your FXML file 'Acquista.fxml'.";
        assert tcAcquista4 != null : "fx:id=\"tcAcquista4\" was not injected: check your FXML file 'Acquista.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model=m;
    	
    	String testo="";
    	for(int i=0;i<model.selezionatiDaAcquistare().size()-1;i++) {
    		testo=testo+""+model.selezionatiDaAcquistare().get(i).getNome()+", ";
    	}
    	testo=testo+""+model.selezionatiDaAcquistare().get(model.selezionatiDaAcquistare().size()-1).getNome();
    	this.lbnGiocatoriDaAcquistare.setText(testo);
    	
    	ObservableList<Giocatore> roster=FXCollections.observableArrayList(model.getRoster(model.getSquadraSelezionata()));
		tvRoster.setItems(roster);
		tcRoster1.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
		tcRoster2.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
		tcRoster3.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
		tcRoster4.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
		
		File file=new File("im/acquista1.jpg");
		Image image=new Image(file.toURI().toString());
		foto.setImage(image);
    	
    }
}

