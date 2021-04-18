package it.polito.tdp.TE_impostazione;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.TE_impostazione.model.Archetipo;
import it.polito.tdp.TE_impostazione.model.Giocatore;
import it.polito.tdp.TE_impostazione.model.Model;
import it.polito.tdp.TE_impostazione.model.Squadre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CediController {

	private Model model; 
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbnGiocatore1;

    @FXML
    private Label lbnGiocatore2;

    @FXML
    private Label lbnGiocatore3;

    @FXML
    private Label lbnSalary;

    @FXML
    private Button btnConfermaTipo;

    @FXML
    private ChoiceBox<String> boxCaratteristiche;

    @FXML
    private CheckBox bxGuardia;

    @FXML
    private CheckBox bxForward;

    @FXML
    private CheckBox bxCentro;

    @FXML
    private Button btnDettagli;

    @FXML
    private TableView<Archetipo> tvArchetipi;

    @FXML
    private TableColumn<Archetipo, String> tcArchetipo;

    @FXML
    private TableColumn<Archetipo, String> tcCerca;

    @FXML
    private Button btnCerca;
    
    @FXML
    private Label lbnAvvisi;

    @FXML
    private Button btnRimuovi;

    @FXML
    private TableView<Giocatore> tvGiocatori;

    @FXML
    private TableColumn<Giocatore, String> tcNome;

    @FXML
    private TableColumn<Giocatore, String> tcSquadra;

    @FXML
    private TableColumn<Giocatore, Integer> tcIngaggio;
    
    private ObservableList<Archetipo> tipi=FXCollections.observableArrayList(); //DA AZZERARE
    private Squadre scelta;

    @FXML
    void doCerca(ActionEvent event) {
    	ObservableList<Giocatore> trovati=FXCollections.observableArrayList();
    	//trovati=(ObservableList<Giocatore>) model.trovaMiglioriGiocatori(tipi,scelta);
    	trovati.addAll(model.trovaMiglioriGiocatori(tipi, scelta));
    	tvGiocatori.setItems(trovati);
    	tcNome.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
    	System.out.println(trovati.size());
    }

    @FXML
    void doConfermaTipo(ActionEvent event) {
    	lbnAvvisi.setText("");
    	if(!bxGuardia.isSelected() && !bxForward.isSelected() && !bxCentro.isSelected()) {
    		lbnAvvisi.setText("Selezionare un ruolo");
    		return;
    	}
    	else {
    		String ruolo="";
    		if(bxGuardia.isSelected())
    			ruolo=ruolo+"G";
    		if(bxGuardia.isSelected() && bxForward.isSelected())
    			ruolo=ruolo+"-F";
    		if(!bxGuardia.isSelected() && bxForward.isSelected())
    			ruolo=ruolo+"F";
    		if((bxGuardia.isSelected() || bxForward.isSelected() ) && bxCentro.isSelected())
    			ruolo=ruolo+"-C";
    		if(!bxGuardia.isSelected() && !bxForward.isSelected() && bxCentro.isSelected())
    			ruolo=ruolo+"C";
    		String tipo=boxCaratteristiche.getValue();
    		if(tipo==null) {
    			tipo="Qualunque";
    		}
    		
    		tipi.add(new Archetipo(tipo, ruolo));
    		tvArchetipi.setItems(tipi);
    		tcArchetipo.setCellValueFactory(new PropertyValueFactory<Archetipo,String>("tipo"));
    		tcCerca.setCellValueFactory(new PropertyValueFactory<Archetipo,String>("ruolo"));
    	}
    }

    @FXML
    void doDettagli(ActionEvent event) {

    }

    @FXML
    void doRimuovi(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lbnGiocatore1 != null : "fx:id=\"lbnGiocatore1\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert lbnGiocatore2 != null : "fx:id=\"lbnGiocatore2\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert lbnGiocatore3 != null : "fx:id=\"lbnGiocatore3\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert lbnSalary != null : "fx:id=\"lbnSalary\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert btnConfermaTipo != null : "fx:id=\"btnConfermaTipo\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert boxCaratteristiche != null : "fx:id=\"boxCaratteristiche\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert bxGuardia != null : "fx:id=\"bxGuardia\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert bxForward != null : "fx:id=\"bxForward\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert bxCentro != null : "fx:id=\"bxCentro\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert btnDettagli != null : "fx:id=\"btnDettagli\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert tvArchetipi != null : "fx:id=\"tvArchetipi\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert tcArchetipo != null : "fx:id=\"tcArchetipo\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert tcCerca != null : "fx:id=\"tcCerca\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert btnRimuovi != null : "fx:id=\"btnRimuovi\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert tvGiocatori != null : "fx:id=\"tvGiocatori\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert tcNome != null : "fx:id=\"tcNome\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert tcSquadra != null : "fx:id=\"tcSquadra\" was not injected: check your FXML file 'Cedi.fxml'.";
        assert tcIngaggio != null : "fx:id=\"tcIngaggio\" was not injected: check your FXML file 'Cedi.fxml'.";

    }
    
    private Integer getSalaryDisponibile(Squadre s) {
    	Integer salari=model.getSalaryCap(s);
    	for(Giocatore g:model.selezionati()) {
    		salari=salari-g.getSalary();
    	}
    	return salari;
    }
    
    public void setModel(Model m, Squadre squadra) {
    	this.model=m;
    	String testo="";
    	for(Giocatore g: model.selezionati()) {
    		testo=testo+""+g.getNome()+", ";
    	}
    	lbnGiocatore1.setText(testo);
    	lbnGiocatore2.setText("");
    	lbnGiocatore3.setText("");
    	
    	List<String> archetipi=new ArrayList<String>();
		archetipi.add("Scorer");
		archetipi.add("Assistman");
		archetipi.add("Stoppatore");
		archetipi.add("Uomo squadra");
		archetipi.add("Tiratore da 3");
		archetipi.add("Tiratore da 2");
		archetipi.add("Rimbalzista");
		boxCaratteristiche.getItems().addAll(archetipi);
		
		if(getSalaryDisponibile(squadra)<=model.getLimiteSalariale()) {
		double p=model.getSalaryCap(squadra);
		lbnSalary.setText(""+(p/1000000));
		lbnSalary.styleProperty().set("-fx-color:green");} //LLLLLLLLLLLLLLLLLLLLLLLL
		
		if(getSalaryDisponibile(squadra)>model.getLimiteSalariale()) {
			lbnSalary.setText(""+model.getSalaryCap(squadra));
			lbnSalary.styleProperty().set("-fx-color:red");}
		
    }
}

