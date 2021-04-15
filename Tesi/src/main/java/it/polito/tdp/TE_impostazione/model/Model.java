package it.polito.tdp.TE_impostazione.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.TE_impostazione.db.GiocatoriDAO;
import it.polito.tdp.TE_impostazione.db.SquadreDAO;

public class Model {
	
	private SquadreDAO squadreDAO;
	private List<Squadre> squadre;
	private GiocatoriDAO giocatoriDao;
	private Set<Giocatore> daCedere;
	
	public Model() {
		squadreDAO=new SquadreDAO();
		squadre=new ArrayList<Squadre>();
		giocatoriDao=new GiocatoriDAO();
		daCedere=new HashSet<Giocatore>();
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
	
	public Set<Giocatore> selezionati(){
		return daCedere;
	}
	
	public Integer aggiungiDaCedere(Giocatore g) {
		if(daCedere.size()<3) {
		daCedere.add(g);
		return daCedere.size();}
		else
			return -1;
	}
	
	public boolean giaDaCedere(Giocatore g) {
		if(daCedere.contains(g))
			return true;
		return false;
	}
	
	public void rimuoviDaCedere(Giocatore g) {
		daCedere.remove(g);
	}
}
