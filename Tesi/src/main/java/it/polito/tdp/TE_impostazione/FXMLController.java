package it.polito.tdp.TE_impostazione;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.TE_impostazione.model.Model;
import it.polito.tdp.TE_impostazione.model.Squadre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private Button btnStatisticheDx;

    @FXML
    private Button btnReset;

    @FXML
    private ChoiceBox<Squadre> boxSquadra;

    @FXML
    private Button btnConferma;
    
    @FXML
    private TableView<?> tvRoster;

    @FXML
    private TableColumn<?, ?> tbNomeRoster;

    @FXML
    private Button btnCedi;

    @FXML
    private ImageView immEst;


    @FXML
    private Label lbSquadra;
    
    @FXML
    private ImageView immWest;

    @FXML
    private Button btnAcquista;
    
    @FXML
    private TableView<?> tvCerca;

    @FXML
    private TableColumn<?, ?> tbNomeCerca;

    @FXML
    private Button btnDettagli;

    @FXML
    private ChoiceBox<String> boxCaratteristiche;
    
    File fileEst = new File("im/easternlogo.png");
    Image estlogo=new Image((fileEst.toURI().toString()));
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
    void doStatisticheSx(ActionEvent event) {

    }

    @FXML
    void doCedi(ActionEvent event) {

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
    		
    	}
    }

    @FXML
    void doConfermaTipo(ActionEvent event) {

    }

    @FXML
    void doDettagli(ActionEvent event) {

    }
    
    @FXML
    void doStatisticheDx(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

   

    @FXML
    void initialize() {
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConferma != null : "fx:id=\"btnConferma\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCedi != null : "fx:id=\"btnCedi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert immEst != null : "fx:id=\"immEst\" was not injected: check your FXML file 'Scene.fxml'.";
       
        assert immWest != null : "fx:id=\"immWest\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAcquista != null : "fx:id=\"btnAcquista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDettagli != null : "fx:id=\"btnDettagli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxCaratteristiche != null : "fx:id=\"boxCaratteristiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConfermaTipo != null : "fx:id=\"btnConfermaTipo\" was not injected: check your FXML file 'Scene.fxml'.";

        
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
		
	}
    
    
    
}

