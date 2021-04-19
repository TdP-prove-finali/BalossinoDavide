package it.polito.tdp.TE_impostazione.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private List<Giocatore> trovati;
	private Integer limiteSalariale;
	private List<Giocatore> lscorer;
	private List<Giocatore> lassist;
	private List<Giocatore> lrimbalzi;
	private List<Giocatore> lplus;
	private List<Giocatore> lda3;
	private List<Giocatore> lda2;
	private Squadre squadraSelezionata;
	
	public Model() {
		squadreDAO=new SquadreDAO();
		squadre=new ArrayList<Squadre>();
		giocatoriDao=new GiocatoriDAO();
		daCedere=new HashSet<Giocatore>();
		trovati=new ArrayList<Giocatore>();
		limiteSalariale=109140000;
	}
	
	public List<Squadre> getNomiSquadre(){
		return squadreDAO.nomiSquadre();
	}
	
	
	public Integer getLimiteSalariale() {
		return limiteSalariale;
	}
	
	public Squadre getSquadraSelezionata() {
		return squadraSelezionata;
	}

	public void setSquadraSelezionata(Squadre squadraSelezionata) {
		this.squadraSelezionata = squadraSelezionata;
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
	
	public Integer getSalaryCap(Squadre s) {
		return squadreDAO.getSalaryCap(s); 
	}
	
	public List<Giocatore> listaScorer(Integer spazioSalariale){
		Float media=giocatoriDao.getMediaPunti();
		lscorer=giocatoriDao.getListaGiocatori(squadraSelezionata,spazioSalariale);
		
		for(Giocatore g:lscorer) {
			if(g.getEta()<25) {
				g.setPesoScorer(g.getPesoScorer()+media/2);
			}
			if(g.getEta()>30) {
				g.setPesoScorer(g.getPesoScorer()-media/2);
			}
			if(g.getSalary()<giocatoriDao.getMediaSalary()) {
				g.setPesoScorer(g.getPesoScorer()+media/2);
			}
			if(g.getSalary()>20*Math.pow(10, 6)) {
				g.setPesoScorer(g.getPesoScorer()-media/2);
			}
		}
		
		Collections.sort(lscorer,new Comparator<Giocatore>(){

			@Override
			public int compare(Giocatore o1, Giocatore o2) {
				return -Float.compare(o1.getPesoScorer(), o2.getPesoScorer());
			}	
			
		});
		if(squadraSelezionata!=null)
		System.out.println(squadraSelezionata.getNome());
		else
			System.out.println("AAAAa");
		return lscorer;
	}
	public List<Giocatore> listaAssist(){
		return null;
	}
	public List<Giocatore> listaRimbalzi(){
		return null;
	}
	public List<Giocatore> listaPlusMinus(){
		return null;
	}
	public List<Giocatore> listaTiratorida3(){
		return null;
	}
	public List<Giocatore> listaTiratorida2(){
		return null;
	}
	
	public Integer getLivelloSalaryCap(Squadre s) { //SPAZIO SALARIALE A LIVELLO LUXURY TAX, TOGLIENDO SOLO STIPENDI DEI GIOCATORI DA CEDERE
		Integer result=getSalaryCap(s);
		for(Giocatore g:selezionati()) {
    		result=result-g.getSalary();
    	}
    	return result;
	}
	
	public Integer getSpazioSalaryCap(Squadre s) { //SPAZIO SALARIALE CONSIDERANDO IL 125% DELLO STIPENDIO, A LIVELLO TRADE
		Integer spazioSalariale=0;
		for(Giocatore g:selezionati()) {
			spazioSalariale=g.getSalary()*125/100;
		}
		if(getLivelloSalaryCap(s)+spazioSalariale<limiteSalariale)
			spazioSalariale=limiteSalariale-getLivelloSalaryCap(s);
		return spazioSalariale;
	}
	
	public List<Giocatore> trovaMiglioriGiocatori(List<Archetipo> scelti, Squadre s, Integer spazioSalariale){
		if(trovati!=null)
		trovati.clear();
		
		int nscorer=0,nassist=0,nrimb=0;
		List<String> ruoli=new ArrayList<String>();
		for(Archetipo a:scelti) {
			//String ruoli[]=a.getRuolo().split("/");
			if(a.getTipo().equals("Scorer"))
				nscorer++;
			if(a.getTipo().equals("Assistman"))
				nassist++;
			if(a.getTipo().equals("Rimbalzista"))
				nrimb++;
			ruoli.add(a.getRuolo());
		}
		List<Giocatore> parziale=new ArrayList<Giocatore>();
		
	
		faiRicorsione(spazioSalariale,s,parziale,ruoli,scelti.size());
		
		return trovati;
	}
	
	private void faiRicorsione(Integer spazioSalariale, Squadre s ,List<Giocatore> parziale, List<String> ruoli, Integer numero ) {
		if(parziale.size()==numero) {
			trovati.addAll(parziale);
			return;
		}
		
		for(Giocatore g:listaScorer(spazioSalariale)) {
			if(!parziale.contains(g)) {
				if(parziale.size()<numero) {
				if(ruoli.get(parziale.size()).contains(g.getPosizione()) || g.getPosizione().contains(ruoli.get(parziale.size()))) {
					if(g.getSalary()<=spazioSalariale || g.getSalary()<=898310) { //MINIMO SALARIALE
						parziale.add(g);
						faiRicorsione(spazioSalariale-g.getSalary(),s,parziale,ruoli,numero);
					}
				}
			}
		}
	  }
	}
}
