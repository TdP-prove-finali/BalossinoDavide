package it.polito.tdp.TE_impostazione;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
import javafx.scene.input.KeyEvent;

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
    private TextField txtMaxSalary;
    
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
    private Label lbnGiocatoriDaAcquistare;

    @FXML
    private Button btnCercaAcquisti;

    @FXML
    private ChoiceBox<String> boxCaratteristiche;
    
    private  File fileEst = new File("im/easternlogo.png");
    private Image estlogo=new Image((fileEst.toURI().toString())); //mettere private
    private File fileWest = new File("im/westernlogo.png");
    private Image westlogo= new Image((fileWest.toURI().toString()));
    private File fileHawks = new File("im/Hawks.png");
    private Image hawks=new Image(fileHawks.toURI().toString());
    private File fileNets = new File("im/Nets.png");
    private Image nets=new Image(fileNets.toURI().toString()); 
    private File fileCeltics = new File("im/Celtics.png");
    private Image celtics=new Image(fileCeltics.toURI().toString());
    private File fileHornets = new File("im/Hornets.png");
    private Image hornets=new Image(fileHornets.toURI().toString());
    private File fileBulls = new File("im/Bulls.png");
    private Image bulls=new Image(fileBulls.toURI().toString());
    private File fileCavs = new File("im/Cavs.png");
    private Image cavs=new Image(fileCavs.toURI().toString());
    private File fileMavs = new File("im/Mavs.png");
    private Image mavs=new Image(fileMavs.toURI().toString());
    private File fileNuggets = new File("im/Denver.png");
    private Image nuggets=new Image(fileNuggets.toURI().toString());
    private File filePistons = new File("im/Pistons.png");
    private Image pistons=new Image(filePistons.toURI().toString());
    private File fileWarriors = new File("im/Warriors.png");
    private Image warriors=new Image(fileWarriors.toURI().toString());
    private File fileRockets = new File("im/Rockets.png");
    private Image rockets=new Image(fileRockets.toURI().toString());
    private File fileClippers = new File("im/Clippers.png");
    private Image clippers=new Image(fileClippers.toURI().toString());
    private File filePacers = new File("im/Pacers.png");
    private Image pacers=new Image(filePacers.toURI().toString());
    private File fileLakers = new File("im/lakers.png");
    private Image lakers=new Image(fileLakers.toURI().toString());
    private File fileMemphis = new File("im/Memphis.png");
    private Image memphis=new Image(fileMemphis.toURI().toString());
    private File fileHeat = new File("im/heat.png");
    private Image heat=new Image(fileHeat.toURI().toString());
    private File fileBucks = new File("im/Bucks.png");
    private Image bucks=new Image(fileBucks.toURI().toString());
    private File fileTwolves = new File("im/Twolves.png");
    private Image twolves=new Image(fileTwolves.toURI().toString());
    private File filePelicans = new File("im/Pelicans.png");
    private Image pelicans=new Image(filePelicans.toURI().toString());
    private File fileKnicks = new File("im/Knicks.png");
    private Image knicks=new Image(fileKnicks.toURI().toString());
    private File fileOkc = new File("im/OKC.png");
    private Image okc=new Image(fileOkc.toURI().toString());
    private File fileMagic = new File("im/Magics.png");
    private Image magic=new Image(fileMagic.toURI().toString());
    private File filePhila = new File("im/Phila.png");
    private Image phila=new Image(filePhila.toURI().toString());
    private File fileSuns = new File("im/Suns.png");
    private Image suns=new Image(fileSuns.toURI().toString());
    private File fileBlazers = new File("im/Blazers.png");
    private Image blazers=new Image(fileBlazers.toURI().toString());
    private File fileKings = new File("im/Kings.png");
    private Image kings=new Image(fileKings.toURI().toString());
	private File fileSpurs = new File("im/Spurs.png");
	private Image spurs=new Image(fileSpurs.toURI().toString());
	private File fileRaptors = new File("im/Raptors.png");
	private Image raptors=new Image(fileRaptors.toURI().toString());
	private File fileJazz = new File("im/UtahJazz.png");
	private Image jazz=new Image(fileJazz.toURI().toString());
	private File fileWizard = new File("im/Wizards.png");
	private Image wizard=new Image(fileWizard.toURI().toString());
	
	private Squadre squadraScelta; //DA AZZERARE 
	

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
    	
    	List<Giocatore> g=model.selezionati();
    	String s="";
    	if(g.size()>0) {
    	for(int i=0;i<g.size()-1;i++)
    		s=s+""+g.get(i).getNome()+"\n";
    	s=s+""+g.get(g.size()-1).getNome();
    	Giocatore1.setText(s);
    	}
    	
    }
    
    @FXML
    void doCercaLista(ActionEvent event) throws IOException {
    	if(model.selezionati().size()>0) {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Cedi.fxml"));
    	Parent root=loader.load();
    	CediController controller= loader.getController();
    	
    	Scene scene= new Scene(root);
    	scene.getStylesheets().add("/styles/Styles.css");
    	
    	Model ml=new Model();
    	for(Giocatore g:model.selezionati()) {
    		ml.aggiungiDaCedere(g);
    	}
    	ml.setSquadraSelezionata(squadraScelta);
    	controller.setModel(ml,squadraScelta);
    	
    	Stage s=new Stage();
    	s.setTitle("Cedi Giocatori");
    	s.setScene(scene);
    	s.setX(+565.0);
    	s.setY(+500.0);
    	s.show(); }
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
    		
    		squadraScelta=scelta;
    		hbox2.setDisable(false);
    	//	model.setSquadraScelta(squadraScelta);
    		txtGiocatore.clear();
    		tvCerca.setItems(null);
    		lbnRicercaGiocatore.setText("");
    		txtMaxSalary.clear();
    		bxGuardia.setSelected(true);
    		bxForward.setSelected(true);
    		bxCentro.setSelected(true);
    		boxCaratteristiche.setValue(null);
    		model.riazzeraModel();
    		Giocatore1.setText("");
    	}
    }

    @FXML
    void doConfermaTipo(ActionEvent event) {
    	tvCerca.setItems(null);
    	lbnRicercaGiocatore.setText("");
    	String g_cerca=txtGiocatore.getText();
    	String s_max=txtMaxSalary.getText();
    	Integer salaryMax=0;
    	
    	if(s_max.length()>0) {
    		try {
    			salaryMax=Integer.parseInt(s_max);
    			
    		} catch(NumberFormatException e) {
    			lbnRicercaGiocatore.setText("Salario massimo non valido");
    			return;
    		}
    	}
    	
    	if(bxGuardia.isSelected()==false && bxForward.isSelected()==false && bxCentro.isSelected()==false) {
    		lbnRicercaGiocatore.setText("Scegliere un ruolo");
    		return;
    	}
    	
    	if(g_cerca.length()==0 && boxCaratteristiche.getValue()==null){ //////DA AGGIUNGERE CONDIZIONE SU SCELTA ARCHETIPO
    		lbnRicercaGiocatore.setText("Scegliere giocatore");
    		return;
    	}
    	
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
		
    	if(g_cerca.length()!=0) {	
    		ObservableList<Giocatore> giocatore=FXCollections.observableArrayList(model.getGiocatore(g_cerca,salaryMax,ruolo));
    		if(giocatore.size()==0) {
    			lbnRicercaGiocatore.setText("Non esistente");
    			return;
    		}
    		tvCerca.setItems(giocatore);
    		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
    		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
    		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
    		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		return;
    	}
    	
    	if(g_cerca.length()==0 && boxCaratteristiche.getValue()!=null) {
    		String tipo=boxCaratteristiche.getValue();
    		if(tipo.equals("Scorer")) {
    			ObservableList<Giocatore> gliScorer=FXCollections.observableArrayList(model.getListaOrdinataScorer(ruolo, squadraScelta,salaryMax));
    			tvCerca.setItems(gliScorer);
        		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
        		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		}
    		if(tipo.equals("Assistman")) {
    			ObservableList<Giocatore> gliAssist=FXCollections.observableArrayList(model.getListaOrdinataAssist(ruolo, squadraScelta,salaryMax));
    			tvCerca.setItems(gliAssist);
        		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
        		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		}
    		if(tipo.equals("Rimbalzista")) {
    			ObservableList<Giocatore> iRimbalzi=FXCollections.observableArrayList(model.getListaOrdinataRimbalzi(ruolo, squadraScelta,salaryMax));
    			tvCerca.setItems(iRimbalzi);
        		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
        		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		}
    		if(tipo.equals("Tiratore da 3")) {
    			ObservableList<Giocatore> iTiratoriDa3=FXCollections.observableArrayList(model.getListaOrdinataTiratoriDa3(ruolo, squadraScelta,salaryMax));
    			tvCerca.setItems(iTiratoriDa3);
        		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
        		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		}
    		if(tipo.equals("Tiratore da 2")) {
    			ObservableList<Giocatore> iTiratoriDa2=FXCollections.observableArrayList(model.getListaOrdinataTiratoriDa2(ruolo, squadraScelta,salaryMax));
    			tvCerca.setItems(iTiratoriDa2);
        		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
        		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		}
    		if(tipo.equals("Uomo squadra")) {
    			ObservableList<Giocatore> gliUominiSquadra=FXCollections.observableArrayList(model.getListaOrdinataUominiSquadra(ruolo, squadraScelta,salaryMax));
    			tvCerca.setItems(gliUominiSquadra);
        		tbNomeCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,String>("nome"));
        		tcPointCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("points"));
        		tcAssistCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("assist"));
        		tcReboundCerca.setCellValueFactory(new PropertyValueFactory<Giocatore,Float>("trimb"));
    		}
    		
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
    void doCercaAcquisti(ActionEvent event) {

    }
    
    @FXML
    void premuto(KeyEvent event) {
    	String n=event.getCharacter();
    	String s=txtMaxSalary.getText();
    	if(n.matches("[0-9]")){
    		String g=Pattern.compile("\\D").matcher(s).replaceAll("");
    	if((g.length()-1)%3==0 && g.length()!=1) { 
    		txtMaxSalary.setText(s.substring(0,s.length()-1)+"."+""+s.charAt(s.length()-1));
    		txtMaxSalary.end();
    	}
    }
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
        assert Giocatore1 != null : "fx:id=\"Giocatore1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaLista != null : "fx:id=\"btnCercaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStatisticheSx != null : "fx:id=\"btnStatisticheSx\" was not injected: check your FXML file 'Scene.fxml'.";
        assert immEst != null : "fx:id=\"immEst\" was not injected: check your FXML file 'Scene.fxml'.";
        assert immWest != null : "fx:id=\"immWest\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGiocatore != null : "fx:id=\"txtGiocatore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lbnRicercaGiocatore != null : "fx:id=\"lbnRicercaGiocatore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMaxSalary != null : "fx:id=\"txtMaxSalary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAcquista != null : "fx:id=\"btnAcquista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lbnGiocatoriDaAcquistare != null : "fx:id=\"lbnGiocatoriDaAcquistare\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaAcquisti != null : "fx:id=\"btnCercaAcquisti\" was not injected: check your FXML file 'Scene.fxml'.";
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
		immEst.setImage(estlogo);//new Image(file.toURI().toString()));
		
		immWest.setImage(westlogo);
		
		File file3 = new File("im/nbalogo.png");
		immNBA.setImage(new Image(file3.toURI().toString()));
		
		boxSquadra.getItems().addAll(model.getNomiSquadre());
		
		List<String> archetipi=new ArrayList<String>();
		archetipi.add("Scorer");
		archetipi.add("Assistman");
		archetipi.add("Uomo squadra");
		archetipi.add("Tiratore da 3");
		archetipi.add("Tiratore da 2");
		archetipi.add("Rimbalzista");
		boxCaratteristiche.getItems().addAll(archetipi);
		hbox2.setDisable(true);
		
		
	}
    
    
    
}

