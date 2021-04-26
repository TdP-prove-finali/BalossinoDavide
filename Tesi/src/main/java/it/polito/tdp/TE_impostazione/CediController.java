package it.polito.tdp.TE_impostazione;

import java.io.File;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class CediController {

	private Model model; 
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbnGiocatore1;
    
    @FXML
    private ImageView spazioImm;

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
    
    private ObservableList<Archetipo> tipi=FXCollections.observableArrayList(); 
    private Integer spazioSalariale;
    private int roster;

    @FXML
    void doCerca(ActionEvent event) {
    	lbnAvvisi.setText("");
    	ObservableList<Giocatore> trovati=FXCollections.observableArrayList();
    	trovati.clear();
    	
    	if(tipi.size()>0) {
    		System.out.println(tipi.size());
    	long inizio=System.currentTimeMillis();
    	trovati.addAll(model.trovaMiglioriGiocatori(tipi, spazioSalariale));
    	long fine=System.currentTimeMillis();
    	System.out.println(fine-inizio);
    	tvGiocatori.setItems(trovati);
    	tcNome.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
    	tcSquadra.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("squadra"));
    	tcIngaggio.setCellValueFactory(new PropertyValueFactory<Giocatore,Integer>("salary"));
    	System.out.println(trovati.size());}
    }

    @FXML
    void doConfermaTipo(ActionEvent event) {
    	lbnAvvisi.setText("");
    	if(!bxGuardia.isSelected() && !bxForward.isSelected() && !bxCentro.isSelected()) {
    		lbnAvvisi.setText("Selezionare un ruolo");
    		return;
    	}
    	else {
    		if(roster-model.selezionati().size()+tipi.size()<17) {
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
    		if(tipo==null) { //////////AAAAAAAAAAAAAAAAAAAAAAAAAA
    			lbnAvvisi.setText("Selezionare un archetipo");
        		return;
    		}
    		
    		tipi.add(new Archetipo(tipo, ruolo));
    		tvArchetipi.setItems(tipi);
    		tcArchetipo.setCellValueFactory(new PropertyValueFactory<Archetipo,String>("tipo"));
    		tcCerca.setCellValueFactory(new PropertyValueFactory<Archetipo,String>("ruolo"));
    	  }
    		else {
    			lbnAvvisi.setText("Il roster supererebbe il limite di 17 giocaotri");
    			return;
    		}
    	}
    }

    @FXML
    void doDettagli(ActionEvent event) throws IOException {
    	lbnAvvisi.setText("");
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/dettagli.fxml"));
    	Parent root=loader.load();
    	DettagliController controller= loader.getController();
    	
    	Scene scene= new Scene(root);
    	scene.getStylesheets().add("/styles/Styles.css");
    	
    	Model ml=new Model();
    	controller.setModel(ml);
    	
    	Stage s=new Stage();
    	s.setTitle("Dettagli");
    	s.setScene(scene);
    	s.setX(+790.0);
    	s.setY(+5.0);
    	s.show();
    }

    @FXML
    void doRimuovi(ActionEvent event) {
    	lbnAvvisi.setText("");
    	Archetipo a=tvArchetipi.getSelectionModel().getSelectedItem();
    	if(a!=null) {
    		tipi.remove(a);
    	//	tvArchetipi.getItems().remove(a);
    		System.out.println(tipi.size());
    		tvGiocatori.getItems().removeAll(tvGiocatori.getItems());
    	}
    }

    @FXML
    void initialize() {
        assert lbnGiocatore1 != null : "fx:id=\"lbnGiocatore1\" was not injected: check your FXML file 'Cedi.fxml'.";
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
    
    
    public void setModel(Model m, Squadre squadra) {
    	this.model=m;
    	lbnAvvisi.setText("");
    	String testo="";
    	if(model.selezionati().size()==1)
    		testo=model.selezionati().get(0).getNome();
    	else {
    	for(int i=0;i<model.selezionati().size()-1;i++) {
    		testo=testo+""+model.selezionati().get(i).getNome()+", ";
    	}
    	testo=testo+""+model.selezionati().get(model.selezionati().size()-1).getNome();
    	}
    	lbnGiocatore1.setText(testo);
    	
    	List<String> archetipi=new ArrayList<String>();
		archetipi.add("Scorer");
		archetipi.add("Assistman");
		archetipi.add("Uomo squadra");
		archetipi.add("Tiratore da 3");
		archetipi.add("Tiratore da 2");
		archetipi.add("Rimbalzista");
		boxCaratteristiche.getItems().addAll(archetipi);
		
		if(model.getLivelloSalaryCap(squadra)<=model.getLimiteSalariale()) {
		double p=model.getLivelloSalaryCap(squadra);
		lbnSalary.setText(""+String.format("%.3f ",(p/1000000)));
		lbnSalary.setTextFill(Color.color(0.5,0.8,0 ));} 
		
		if(model.getLivelloSalaryCap(squadra)>model.getLimiteSalariale()) {
			lbnSalary.setText(""+model.getLivelloSalaryCap(squadra));
			double p=model.getLivelloSalaryCap(squadra);
			lbnSalary.setText(""+String.format("%.3f ",(p/1000000)));
			lbnSalary.setTextFill(Color.color(1, 0, 0));}
		
		spazioSalariale=model.getSpazioSalaryCap(squadra);
	//	model.listaScorer(spazioSalariale);
		File fileImm=new File("im/generalManager3.jpg");
		Image imm=new Image(fileImm.toURI().toString());
		spazioImm.setImage(imm);
		roster=model.getRoster(model.getSquadraSelezionata()).size();
    }
}

