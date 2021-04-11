package it.polito.tdp.TE_impostazione.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.TE_impostazione.db.GiocatoriDAO;
import it.polito.tdp.TE_impostazione.db.SquadreDAO;

public class Model {
	
	private SquadreDAO squadreDAO;
	private List<Squadre> squadre;
	private GiocatoriDAO giocatoriDao;
	
	public Model() {
		squadreDAO=new SquadreDAO();
		squadre=new ArrayList<Squadre>();
		giocatoriDao=new GiocatoriDAO();
	}
	
	public List<Squadre> getNomiSquadre(){
		return squadreDAO.nomiSquadre();
	}
	
	public List<Giocatore> getRoster(Squadre s){
		return squadreDAO.roster(s);
	}
	
	public List<Giocatore> getGiocatore(String giocatore){
		return giocatoriDao.getGiocatore(giocatore);
	}
}
