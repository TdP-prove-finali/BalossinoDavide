package it.polito.tdp.TE_impostazione;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ImageView immNBA;
    
    @FXML
    private Button btnStatisticheSx;
    
    @FXML
    private TextField txtGiocatore;

    @FXML
    private Label lbnRicercaGiocatore;
    
    @FXML
    private HBox hbox2;
    
    @FXML
    private Button btnStatisticheDx;

    @FXML
    private Button btnReset;

    @FXML
    private ChoiceBox<Squadre> boxSquadra;

    @FXML
    private Button btnConferma;
    
    @FXML
    private TableView<Giocatore> tvRoster;

    @FXML
    private TableColumn<Giocatore, String > tbNomeRoster;
    
    @FXML
    private TableColumn<Giocatore, Float> tbPointsRoster;

    @FXML
    private TableColumn<Giocatore, Float> tbAssistsRoster;

    @FXML
    private TableColumn<Giocatore, Float> tbReboundsRoster;

    @FXML
    private Button btnCedi;
    
    @FXML
    private Label Giocatore1;

    @FXML
    private Label Giocatore2;

    @FXML
    private Label Giocatore3;

    @FXML
    private Button btnCercaLista;

    @FXML
    private ImageView immEst;


    @FXML
    private Label lbSquadra;
    
    @FXML
    private ImageView immWest;

    @FXML
    private Button btnAcquista;
    
    @FXML
    private TableView<Giocatore> tvCerca;
    
    @FXML
    private CheckBox bxGuardia;

    @FXML
    private CheckBox bxForward;

    @FXML
    private CheckBox bxCentro;

    @FXML
    private TableColumn<Giocatore, String> tbNomeCerca;
    
    @FXML
    private TableColumn<Giocatore, Float> tcPointCerca;

    @FXML
    private TableColumn<Giocatore, Float> tcAssistCerca;

    @FXML
    private TableColumn<Giocatore, Float> tcReboundCerca;

    @FXML
    private Button btnDettagli;

    @FXML
    private ChoiceBox<String> boxCaratteristiche;
    
    private  File fileEst = new File("im/easternlogo.png");
    Image estlogo=new Image((fileEst.toURI().toString())); //mettere private
	File fileWest = new File("im/westernlogo.png");
	Image westlogo= new Image((fileWest.toURI().toString()));
	File fileHawks = new File("im/Hawks.png");
	Image hawks=new Image(fileHawks.toURI().toString());
	File fileNets = new File("im/Nets.png");
	Image nets=new Image(fileNets.toURI().toString()); 
	File fileCeltics = new File("im/Celtics.png");
	Image celtics=new Image(fileCeltics.toURI().toString());
	File fileHornets = new File("im/Hornets.png");
	Image hornets=new Image(fileHornets.toURI().toString());
	File fileBulls = new File("im/Bulls.png");
	Image bulls=new Image(fileBulls.toURI().toString());
	File fileCavs = new File("im/Cavs.png");
	Image cavs=new Image(fileCavs.toURI().toString());
	File fileMavs = new File("im/Mavs.png");
	Image mavs=new Image(fileMavs.toURI().toString());
	File fileNuggets = new File("im/Denver.png");
	Image nuggets=new Image(fileNuggets.toURI().toString());
	File filePistons = new File("im/Pistons.png");
	Image pistons=new Image(filePistons.toURI().toString());
	File fileWarriors = new File("im/Warriors.png");
	Image warriors=new Image(fileWarriors.toURI().toString());
	File fileRockets = new File("im/Rockets.png");
	Image rockets=new Image(fileRockets.toURI().toString());
	File fileClippers = new File("im/Clippers.png");
	Image clippers=new Image(fileClippers.toURI().toString());
	File filePacers = new File("im/Pacers.png");
	Image pacers=new Image(filePacers.toURI().toString());
	File fileLakers = new File("im/lakers.png");
	Image lakers=new Image(fileLakers.toURI().toString());
	File fileMemphis = new File("im/Memphis.png");
	Image memphis=new Image(fileMemphis.toURI().toString());
	File fileHeat = new File("im/heat.png");
	Image heat=new Image(fileHeat.toURI().toString());
	File fileBucks = new File("im/Bucks.png");
	Image bucks=new Image(fileBucks.toURI().toString());
	File fileTwolves = new File("im/Twolves.png");
	Image twolves=new Image(fileTwolves.toURI().toString());
	File filePelicans = new File("im/Pelicans.png");
	Image pelicans=new Image(filePelicans.toURI().toString());
	File fileKnicks = new File("im/Knicks.png");
	Image knicks=new Image(fileKnicks.toURI().toString());
	File fileOkc = new File("im/OKC.png");
	Image okc=new Image(fileOkc.toURI().toString());
	File fileMagic = new File("im/Magics.png");
	Image magic=new Image(fileMagic.toURI().toString());
	File filePhila = new File("im/Phila.png");
	Image phila=new Image(filePhila.toURI().toString());
	File fileSuns = new File("im/Suns.png");
	Image suns=new Image(fileSuns.toURI().toString());
	File fileBlazers = new File("im/Blazers.png");
	Image blazers=new Image(fileBlazers.toURI().toString());
	File fileKings = new File("im/Kings.png");
	Image kings=new Image(fileKings.toURI().toString());
	File fileSpurs = new File("im/Spurs.png");
	Image spurs=new Image(fileSpurs.toURI().toString());
	File fileRaptors = new File("im/Raptors.png");
	Image raptors=new Image(fileRaptors.toURI().toString());
	File fileJazz = new File("im/UtahJazz.png");
	Image jazz=new Image(fileJazz.toURI().toString());
	File fileWizard = new File("im/Wizards.png");
	Image wizard=new Image(fileWizard.toURI().toString());
	
	
	

    @FXML
    private Button btnConfermaTipo;

    @FXML
    void doAcquista(ActionEvent event) {

    }
    
    @FXML
    void doStatisticheSx(ActionEvent event) throws IOException {
    	Giocatore g=tvRoster.getSelectionModel().getSelectedItem();
    	if(g!=null) {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Stat.fxml"));
    	Parent root=loader.load();
    	StatController controller= loader.getController();
    	
    	Scene scene= new Scene(root);
    	scene.getStylesheets().add("/styles/Styles.css");
    	
    	Model ml=new Model();
    	controller.setModel(ml,g);
    	
    	Stage s=new Stage();
    	s.setTitle("Statistiche avanzate");
    	s.setScene(scene);
    	s.setX(+565.0);
    	s.setY(+500.0);
    	s.show();
    	}
    	
    	
    }

    @FXML
    void doCedi(ActionEvent event) {
    	Giocatore g=tvRoster.getSelectionModel().getSelectedItem();
    	if(g!=null) {
    		boolean b=model.giaDaCedere(g);
    		if(!b) {
    			model.aggiungiDaCedere(g);
    		    setLabelDaCedere();
    		    return;
    		}
    		else {
    			model.rimuoviDaCedere(g);
    			setLabelDaCedere();
    			return;
    		}
    	}
    }
    
    private void setLabelDaCedere() {
    	Giocatore1.setText("");
    	Giocatore2.setText("");
    	Giocatore3.setText("");
    	
    	Set<Giocatore> g=model.selezionati();
    	if(g.size()==1) {
    		for(Giocatore gi: g) {
    		Giocatore1.setText(gi.getNome());
    		}
    		return;
    	}
    	if(g.size()==2) {
    		for(Giocatore gi: g) {
    		if(Giocatore1.getText().equals(""))
    		Giocatore1.setText(gi.getNome());
    		if(Giocatore2.getText().equals("") && gi.getNome().equals(Giocatore1.getText())==false)
        		Giocatore2.setText(gi.getNome());
    		}
    		return;
    	}
    	if(g.size()==3) {
    		for(Giocatore gi: g) {
    		if(Giocatore1.getText().equals(""))
    		Giocatore1.setText(gi.getNome());
    		if(Giocatore2.getText().equals("") && gi.getNome().equals(Giocatore1.getText())==false)
        		Giocatore2.setText(gi.getNome());
    		if(Giocatore3.getText().equals("") && gi.getNome().equals(Giocatore1.getText())==false && gi.getNome().equals(Giocatore2.getText())==false)
        		Giocatore3.setText(gi.getNome());
    		}
    		return;
    	}
    	if(g.size()==0) {
    		Giocatore2.setText("");
    		Giocatore3.setText("");
    		Giocatore1.setText("");
    		return;
    	}
    }
    
    @FXML
    void doCercaLista(ActionEvent event) {
    	
    }

    @FXML
    void doConferma(ActionEvent event) {
    	lbSquadra.setText("");
    	
    	
    	
    	
    	//impostazione delle immagini a seguito della scelta della squadra
    	Squadre scelta=boxSquadra.getValue();
    	if(scelta==null) {
    		lbSquadra.setText("Selezionare una squadra");
    		return;
    	}
    	else {
    		if(scelta.getNome().equals("Atlanta Hawks")) {//1
    			
    			immWest.setImage(hawks);
    			immEst.setImage(estlogo);
    			//immWest.setImage(new Image("im/Hawks.png"));
    		}
            
    		if(scelta.getNome().equals("Brooklyn Nets")) {//2
    			immWest.setImage(nets);
    			immEst.setImage(estlogo);
    		}
    		
    		if(scelta.getNome().equals("Boston Celtics")) {//3
    			immWest.setImage(celtics);
    			immEst.setImage(estlogo);
    		}

    		if(scelta.getNome().equals("Charlotte Hornets")) {
    			immWest.setImage(hornets);
    			immEst.setImage(estlogo);
    		}//4
    		
    		if(scelta.getNome().equals("Chicago Bulls")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(bulls);
    		}//5
    		
    		if(scelta.getNome().equals("Cleveland Cavaliers")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(cavs);
    		}//6
    		if(scelta.getNome().equals("Dallas Mavericks")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(mavs);
    		}//7
    		if(scelta.getNome().equals("Denver Nuggets")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(nuggets);
    		}//8
    		if(scelta.getNome().equals("Detroit Pistons")) {
    			immWest.setImage(pistons);
    			immEst.setImage(estlogo);
    		}//9
    		if(scelta.getNome().equals("Golden State Warriors")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(warriors);
    		}//10
    		if(scelta.getNome().equals("Houston Rockets")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(rockets);
    		}//11
    		if(scelta.getNome().equals("Indiana Pacers")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(pacers);
    		}//12
    		if(scelta.getNome().equals("Los Angeles Clippers")) {
    			immEst.setImage(clippers);
    			immWest.setImage(westlogo);
    		}//13
    		if(scelta.getNome().equals("Los Angeles Lakers")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(lakers);
    		}//14
    		if(scelta.getNome().equals("Memphis Grizzlies")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(memphis);
    		}//15
    		if(scelta.getNome().equals("Miami Heat")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(heat);
    		}//16
    		if(scelta.getNome().equals("Milwaukee Bucks")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(bucks);
    		}//17
    		if(scelta.getNome().equals("Minnesota Timberwolves")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(twolves);
    		}//18
    		if(scelta.getNome().equals("New Orleans Pelicans")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(pelicans);
    		}//19
    		if(scelta.getNome().equals("New York Knicks")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(knicks);
    		}//20
    		if(scelta.getNome().equals("Oklahoma City Thunder")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(okc);
    		}//21
    		if(scelta.getNome().equals("Orlando Magic")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(magic);
    		}//22
    		if(scelta.getNome().equals("Philadelphia 76ers")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(phila);
    		}//23
    		if(scelta.getNome().equals("Phoenix Suns")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(suns);
    		}//24
    		if(scelta.getNome().equals("Portland Trail Blazers")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(blazers);
    		}//25
    		if(scelta.getNome().equals("Sacramento Kings")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(kings);
    		}//26
    		if(scelta.getNome().equals("San Antonio Spurs")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(spurs);
    		}//27
    		if(scelta.getNome().equals("Toronto Raptors")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(raptors);
    		}//28
    		if(scelta.getNome().equals("Utah Jazz")) {
    			immWest.setImage(westlogo);
    			immEst.setImage(jazz);
    		}//29
    		if(scelta.getNome().equals("Washington Wizards")) {
    			immEst.setImage(estlogo);
    			immWest.setImage(wizard);
    		}//30
    		
    		
    		ObservableList<Giocatore> roster=FXCollections.observableArrayList(model.getRoster(scelta));
    		tvRoster.setItems(roster);
    		tbNomeRoster.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
    		tbPointsRoster.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
    		tbAssistsRoster.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
    		tbReboundsRoster.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		
    	}
    }

    @FXML
    void doConfermaTipo(ActionEvent event) {
    	tvCerca.setItems(null);
    	lbnRicercaGiocatore.setText("");
    	String g_cerca=txtGiocatore.getText();
    	
    	if(g_cerca.length()==0 || (bxGuardia.isSelected()==false && bxForward.isSelected()==false && bxCentro.isSelected()==false)){ //////DA AGGIUNGERE CONDIZIONE SU SCELTA ARCHETIPO
    		lbnRicercaGiocatore.setText("Scegliere giocatore");
    	}
    	else {
    		
    		ObservableList<Giocatore> giocatore=FXCollections.observableArrayList(model.getGiocatore(g_cerca));
    		if(giocatore.size()==0) {
    			lbnRicercaGiocatore.setText("Non esistente");
    		}
    		tvCerca.setItems(giocatore);
    		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
    		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
    		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
    		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		
    	}
    }
    
    

    @FXML
    void doDettagli(ActionEvent event) throws IOException {
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
    void doStatisticheDx(ActionEvent event) throws IOException {
    	Giocatore g=tvCerca.getSelectionModel().getSelectedItem();
    	if(g!=null) {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Stat.fxml"));
    	Parent root=loader.load();
    	StatController controller= loader.getController();
    	
    	Scene scene= new Scene(root);
    	scene.getStylesheets().add("/styles/Styles.css");
    	
    	Model ml=new Model();
    	controller.setModel(ml,g);
    	
    	Stage s=new Stage();
    	s.setTitle("Statistiche avanzate");
    	s.setScene(scene);
    	s.setX(+565.0);
    	s.setY(+500.0);
    	s.show();
    	}
    }

    
    
 //   @FXML
  //  void doPosizione(ActionEvent event) {
    	
  //  }
    
    
  /* @FXML
   	void doGuardia(ActionEvent event) {
	//   mnPosizione.setOnShowing(mnPosizione);
	    mnPosizione.show();
   } */
   	
    
    
    
    @FXML
    void doReset(ActionEvent event) {

    }

   

    @FXML
    void initialize() {
    	 assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
         assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnConferma != null : "fx:id=\"btnConferma\" was not injected: check your FXML file 'Scene.fxml'.";
         assert lbSquadra != null : "fx:id=\"lbSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
         assert immNBA != null : "fx:id=\"immNBA\" was not injected: check your FXML file 'Scene.fxml'.";
         assert hbox2 != null : "fx:id=\"hbox2\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tvRoster != null : "fx:id=\"tvRoster\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tbNomeRoster != null : "fx:id=\"tbNomeRoster\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tbPointsRoster != null : "fx:id=\"tbPointsRoster\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tbAssistsRoster != null : "fx:id=\"tbAssistsRoster\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tbReboundsRoster != null : "fx:id=\"tbReboundsRoster\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnCedi != null : "fx:id=\"btnCedi\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnStatisticheSx != null : "fx:id=\"btnStatisticheSx\" was not injected: check your FXML file 'Scene.fxml'.";
         assert immEst != null : "fx:id=\"immEst\" was not injected: check your FXML file 'Scene.fxml'.";
         assert immWest != null : "fx:id=\"immWest\" was not injected: check your FXML file 'Scene.fxml'.";
         assert txtGiocatore != null : "fx:id=\"txtGiocatore\" was not injected: check your FXML file 'Scene.fxml'.";
         assert lbnRicercaGiocatore != null : "fx:id=\"lbnRicercaGiocatore\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnAcquista != null : "fx:id=\"btnAcquista\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnStatisticheDx != null : "fx:id=\"btnStatisticheDx\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnConfermaTipo != null : "fx:id=\"btnConfermaTipo\" was not injected: check your FXML file 'Scene.fxml'.";
         assert boxCaratteristiche != null : "fx:id=\"boxCaratteristiche\" was not injected: check your FXML file 'Scene.fxml'.";
         assert bxGuardia != null : "fx:id=\"bxGuardia\" was not injected: check your FXML file 'Scene.fxml'.";
         assert bxForward != null : "fx:id=\"bxForward\" was not injected: check your FXML file 'Scene.fxml'.";
         assert bxCentro != null : "fx:id=\"bxCentro\" was not injected: check your FXML file 'Scene.fxml'.";
         assert btnDettagli != null : "fx:id=\"btnDettagli\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tvCerca != null : "fx:id=\"tvCerca\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tbNomeCerca != null : "fx:id=\"tbNomeCerca\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tcPointCerca != null : "fx:id=\"tcPointCerca\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tcAssistCerca != null : "fx:id=\"tcAssistCerca\" was not injected: check your FXML file 'Scene.fxml'.";
         assert tcReboundCerca != null : "fx:id=\"tcReboundCerca\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model2) {
		this.model=model2;
		//File file = new File("im/easternlogo.png");
		immEst.setImage(estlogo);//new Image(file.toURI().toString()));
		
	//	File file2 = new File("im/westernlogo.png");
		immWest.setImage(westlogo);
		
		File file3 = new File("im/nbalogo.png");
		immNBA.setImage(new Image(file3.toURI().toString()));
		
		boxSquadra.getItems().addAll(model.getNomiSquadre());
		
		List<String> archetipi=new ArrayList<String>();
		archetipi.add("Scorer");
		archetipi.add("Assistman");
		archetipi.add("Stoppatore");
		archetipi.add("Uomo squadra");
		archetipi.add("Tiratore da 3");
		archetipi.add("Tiratore da 2");
		archetipi.add("Rimbalzista");
		boxCaratteristiche.getItems().addAll(archetipi);
		
		
		
	}
    
    
    
}

