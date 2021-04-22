package it.polito.tdp.TE_impostazione;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.TE_impostazione.model.Giocatore;
import it.polito.tdp.TE_impostazione.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StatController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbnGiocatore;

    @FXML
    private ImageView imm;

    @FXML
    private TextArea txtStat;

    @FXML
    void initialize() {
        assert lbnGiocatore != null : "fx:id=\"lbnGiocatore\" was not injected: check your FXML file 'stat.fxml'.";
        assert imm != null : "fx:id=\"imm\" was not injected: check your FXML file 'stat.fxml'.";
        assert txtStat != null : "fx:id=\"txtStat\" was not injected: check your FXML file 'stat.fxml'.";
        
    }
    
    public void setModel(Model m, Giocatore g) {
    	this.model=m;
    	File f=new File("im/Nba_stats.png");
    	imm.setImage(new Image(f.toURI().toString()));
    	txtStat.setEditable(false);
  //  	txtStat.setStyle("-fx-font-family: monospace");
    	riempiTextArea(g);
    }
    
    private void riempiTextArea(Giocatore g) {
    	
    	lbnGiocatore.setText(g.getNome());
    	
    	txtStat.setStyle("-fx-font-family: monospace");
    	StringBuilder sb=new StringBuilder();
    	sb.append(String.format("%-30s ", "Nome:"));
    	sb.append(String.format("%-30s\n", g.getNome()));
   // 	txtStat.appendText(sb.toString());
    	
    	sb.append(String.format("%-30s ", "Squadra:"));
    	sb.append(String.format("%-30s\n", g.getSquadra().getNome()));
    	sb.append(String.format("%-30s ", "Salary:"));
    	sb.append(String.format("%-30d\n", g.getSalary()));
    	sb.append(String.format("%-30s ", "Posizione:"));
    	sb.append(String.format("%-30s\n", g.getPosizione()));
    	sb.append(String.format("%-30s ", "Partite giocate:"));
    	sb.append(String.format("%-30d\n", g.getGames_played()));
    	sb.append(String.format("%-30s ", "Partite giocate da titolare:"));
    	sb.append(String.format("%-30d\n", g.getGames_started()));
    	sb.append(String.format("%-30s ", "Punti:"));
    	sb.append(String.format("%-30.2f\n", g.getPoints()));
    	sb.append(String.format("%-30s ", "Assist:"));
    	sb.append(String.format("%-30.2f\n", g.getAssist()));
    	sb.append(String.format("%-30s ", "Rimbalzi offensivi:"));
    	sb.append(String.format("%-30.2f\n", g.getOrimb()));
    	sb.append(String.format("%-30s ", "Rimbalzi difensivi:"));
    	sb.append(String.format("%-30.2f\n", g.getDrimb()));
    	sb.append(String.format("%-30s ", "Rimbalzi totali:"));
    	sb.append(String.format("%-30.2f\n", g.getTrimb()));
    	sb.append(String.format("%-30s ", "Minuti a partita:"));
    	sb.append(String.format("%-30.2f\n", g.getMinutes_pergame()));
    	sb.append(String.format("%-30s ", "Field Goals made:"));
    	sb.append(String.format("%-30.2f\n", g.getFg_made()));
    	sb.append(String.format("%-30s ", "Field Goals attempted:"));
    	sb.append(String.format("%-30.2f\n", g.getFg_attemp()));
    	sb.append(String.format("%-30s ", "Field Goals percentage:"));
    	String fgp=g.getFg_perc();
    	try {
    		Float pfg=Float.parseFloat(fgp);
    		sb.append(String.format("%-30.2f\n", pfg));
        } catch(NumberFormatException e) {
        	sb.append(String.format("%-30s\n", " _ "));
        }
    	
    	sb.append(String.format("%-30s ", "3-Point made:"));
    	sb.append(String.format("%-30.2f\n", g.getP3_made()));
    	sb.append(String.format("%-30s ", "3-Point attempted:"));
    	sb.append(String.format("%-30.2f\n", g.getP3_attemp()));
    	sb.append(String.format("%-30s ", "3-Point percentage:"));
    	String gp3=g.getP3_perce();
    	try {
    		Float p3=Float.parseFloat(gp3);
    		sb.append(String.format("%-30.2f\n", p3));
        } catch(NumberFormatException e) {
        	sb.append(String.format("%-30s\n", " _ "));
        }
    	
    	sb.append(String.format("%-30s ", "2-Point made:"));
    	sb.append(String.format("%-30.2f\n", g.getP2_made()));
    	sb.append(String.format("%-30s ", "2-Point attempted:"));
    	sb.append(String.format("%-30.2f\n", g.getP2_attemp()));
    	sb.append(String.format("%-30s ", "2-Point percentage:"));
    	String gp2=g.getP2_perce();
    	try {
    		Float p2=Float.parseFloat(gp2);
    		sb.append(String.format("%-30.2f\n", p2));
        } catch(NumberFormatException e) {
        	sb.append(String.format("%-30s\n", " _ "));
        }
    	    	
    	sb.append(String.format("%-30s ", "Palle rubate:"));
    	sb.append(String.format("%-30.2f\n", g.getSteal()));
    	sb.append(String.format("%-30s ", "Palle perse:"));
    	sb.append(String.format("%-30.2f\n", g.getTurnovers()));
    	sb.append(String.format("%-30s ", "Plus-Minus*:"));
    	sb.append(String.format("%-30.2f\n", g.getPlus()));
    	sb.append(String.format("%-30s ", "VORP**:"));
    	sb.append(String.format("%-30.2f\n", g.getVorp()));
    	sb.append(String.format("%-30s ", "Stoppate:"));
    	sb.append(String.format("%-30.2f\n", g.getBlocks()));
    	sb.append(String.format("%-30s ", "Infortunato***:"));
    	sb.append(String.format("%-30s\n", g.getInj()));
    	
    	
    	sb.append(String.format("\n\n\n%-200s\n", "*Plus-Minus= Indica il rendimento di un giocatore mediante il conteggio della differenza tra punti fatti e subiti dalla sua squadra durante la permanenza in campo del giocatore stesso"));
    	sb.append(String.format("%-200s\n", "**VORP= Acronimo di Value Over Replacement Player ed è una statistica dipendente dal Plus-Minus: si prende il valore di quest’ultima per creare un scala di valutazione univoca per tutti i giocatori"));
    	sb.append(String.format("%-200s\n", "***Infortunato= Indica se il giocatore avesse un infortunio tale da tenerlo fuori per tutta la stagione e non poter giocare alcuna partita"));
    	
    	
    	
    	txtStat.appendText(sb.toString());
    	txtStat.home();
    	
    	
    	
    }
}

