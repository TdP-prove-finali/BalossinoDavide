package it.polito.tdp.TE_impostazione.controller.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.TE_impostazione.controller.db.GiocatoriDAO;
import it.polito.tdp.TE_impostazione.controller.db.SquadreDAO;

public class Model {
	
	private SquadreDAO squadreDAO;
	private List<Squadre> squadre;
	
	private GiocatoriDAO giocatoriDao;
	private List<Giocatore> daCedere;
	private List<Giocatore> trovati;
	private Integer limiteSalariale;
	private List<Giocatore> lscorer;
	private List<Giocatore> lassist;
	private List<Giocatore> lrimbalzi;
	private List<Giocatore> lplus;
	private List<Giocatore> lda3;
	private List<Giocatore> lda2;
	private Squadre squadraSelezionata;
	private List<Giocatore> daAcquistare;
	
	public Model() {
		squadreDAO=new SquadreDAO();
		squadre=new ArrayList<Squadre>();
		giocatoriDao=new GiocatoriDAO();
		daCedere=new ArrayList<Giocatore>();
		trovati=new ArrayList<Giocatore>();
		limiteSalariale=109140000;
		daAcquistare=new ArrayList<Giocatore>();
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
	
	public List<Squadre> getTutteSquadre(){
		return squadreDAO.tutteSquadre();
	}

	public void setSquadraSelezionata(Squadre squadraSelezionata) {
		this.squadraSelezionata = squadraSelezionata;
	}

	public List<Giocatore> getRoster(Squadre s){
		return squadreDAO.roster(s);
	}
	
	public List<Giocatore> getGiocatore(String giocatore, Integer salaryMax, String ruolo){
		return giocatoriDao.getGiocatore(giocatore,salaryMax,ruolo);
	}
	
	public List<Giocatore> selezionati(){
		return daCedere;
	}
	
	public void aggiungiDaCedere(Giocatore g) {
		if(daCedere.size()<3) {
		daCedere.add(g);}
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
	
	private String ruoloFattoScorer;
	public List<Giocatore> listaScorer(Integer spazioSalariale, String posizione){
		
		if(ruoloFattoScorer==null || !ruoloFattoScorer.contains(posizione)) {
		lscorer=giocatoriDao.getListaGiocatoriAccessibiliPunti(spazioSalariale,squadraSelezionata,posizione);
		
		Collections.sort(lscorer,new Comparator<Giocatore>(){

			@Override
			public int compare(Giocatore o1, Giocatore o2) {
				return -Float.compare(o1.getPesoScorer(), o2.getPesoScorer());
			}	
			
		});
		ruoloFattoScorer=posizione;}
		return lscorer;
	}
	
	private String ruoloFattoAssist;
	public List<Giocatore> listaAssist(Integer spazioSalariale, String posizione){
		if(ruoloFattoAssist==null || !ruoloFattoAssist.contains(posizione)) {
			lassist=giocatoriDao.getListaGiocatoriAccessibiliAssist(spazioSalariale,squadraSelezionata,posizione);
			
			Collections.sort(lassist,new Comparator<Giocatore>(){

				@Override
				public int compare(Giocatore o1, Giocatore o2) {
					return -Float.compare(o1.getPesoAssist(), o2.getPesoAssist());
				}	
				
			});
			ruoloFattoAssist=posizione;}
			return lassist;
		}
	
	private String ruoloFattoRimbalzi;
	public List<Giocatore> listaRimbalzi(Integer spazioSalariale, String posizione){
		if(ruoloFattoRimbalzi==null || !ruoloFattoRimbalzi.contains(posizione)) {
			lrimbalzi=giocatoriDao.getListaGiocatoriAccessibiliRimbalzi(spazioSalariale,squadraSelezionata,posizione);
			
			Collections.sort(lrimbalzi,new Comparator<Giocatore>(){

				@Override
				public int compare(Giocatore o1, Giocatore o2) {
					return -Float.compare(o1.getPesoRimbalzi(), o2.getPesoRimbalzi());
				}	
				
			});
			ruoloFattoRimbalzi=posizione;}
			return lrimbalzi;
		}
	
	private String ruoloFattoTiratoriDa3;
	public List<Giocatore> listaTiratoriDa3(Integer spazioSalariale, String posizione){
		if(ruoloFattoTiratoriDa3==null || !ruoloFattoTiratoriDa3.contains(posizione)) {
			lda3=giocatoriDao.getListaGiocatoriAccessibiliTiratoriDa3(spazioSalariale,squadraSelezionata,posizione);
			
			Collections.sort(lda3,new Comparator<Giocatore>(){

				@Override
				public int compare(Giocatore o1, Giocatore o2) {
					return -Float.compare(o1.getPesoTiratoreDa3(), o2.getPesoTiratoreDa3());
				}	
				
			});
			ruoloFattoTiratoriDa3=posizione;}
			return lda3;
		}
	
	private String ruoloFattoTiratoriDa2;
	public List<Giocatore> listaTiratoriDa2(Integer spazioSalariale, String posizione){
		if(ruoloFattoTiratoriDa2==null || !ruoloFattoTiratoriDa2.contains(posizione)) {
			lda2=giocatoriDao.getListaGiocatoriAccessibiliTiratoriDa2(spazioSalariale,squadraSelezionata,posizione);
			
			Collections.sort(lda2,new Comparator<Giocatore>(){

				@Override
				public int compare(Giocatore o1, Giocatore o2) {
					return -Float.compare(o1.getPesoTiratoreDa2(), o2.getPesoTiratoreDa2());
				}	
				
			});
			ruoloFattoTiratoriDa2=posizione;}
			return lda2;
		}
	
	private String ruoloFattoUomoSquadra;
	public List<Giocatore> listaUominiSquadra(Integer spazioSalariale, String posizione){
		if(ruoloFattoUomoSquadra==null || !ruoloFattoUomoSquadra.contains(posizione)) {
			lplus=giocatoriDao.getListaGiocatoriAccessibiliUomoSquadra(spazioSalariale,squadraSelezionata,posizione);
			
			Collections.sort(lplus,new Comparator<Giocatore>(){

				@Override
				public int compare(Giocatore o1, Giocatore o2) {
					return -Float.compare(o1.getPesoUomoSquadra(), o2.getPesoUomoSquadra());
				}	
				
			});
			ruoloFattoUomoSquadra=posizione;}
			return lplus;
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
	

	public List<Giocatore> trovaMiglioriGiocatori(List<Archetipo> scelti, Integer spazioSalariale){
		
		trovati.clear();
		ruoloFattoRimbalzi=null;
		ruoloFattoAssist=null;
		ruoloFattoScorer=null;
		
		
		List<String> ruoli=new ArrayList<String>();
		List<String> ordine=new ArrayList<String>();
		for(Archetipo a:scelti) {
			if(a.getTipo().equals("Scorer")) {
				ruoli.add(a.getRuolo());
				ordine.add("Scorer");
			}
				
			if(a.getTipo().equals("Assistman")) {
				ruoli.add(a.getRuolo());
				ordine.add("Assistman");
			}
				
			if(a.getTipo().equals("Rimbalzista")) {
				ruoli.add(a.getRuolo());
				ordine.add("Rimbalzista");
			}
			
			if(a.getTipo().equals("Tiratore da 3")) {
				ruoli.add(a.getRuolo());
				ordine.add("Tiratore da 3");
			}
			
			if(a.getTipo().equals("Tiratore da 2")) {
				ruoli.add(a.getRuolo());
				ordine.add("Tiratore da 2");
			}
			
			if(a.getTipo().equals("Uomo squadra")) {
				ruoli.add(a.getRuolo());
				ordine.add("Uomo squadra");
			}
				
		}
		List<Giocatore> parziale=new ArrayList<Giocatore>();
		
		faiRicorsione(spazioSalariale,parziale,ruoli,ordine,0,ruoli.size());
		
		return trovati;
	}
	
	private void faiRicorsione(Integer spazioSalariale ,List<Giocatore> parziale, List<String> ruoli,List<String> ordine,Integer livello,Integer numero ) {
		if(parziale.size()==numero) {
			trovati.addAll(parziale);
			return;
		}
		
		while(livello<numero) {
			if(ordine.get(livello).equals("Scorer")) {
				for(Giocatore g:listaScorer(spazioSalariale,ruoli.get(livello))) {
					if(!parziale.contains(g)) {
						if(parziale.size()<numero && (g.getPosizione().contains(ruoli.get(livello)) || ruoli.get(livello).contains(g.getPosizione())) ) {
							parziale.add(g);
							faiRicorsione(spazioSalariale-g.getSalary(),parziale,ruoli,ordine,livello+1,numero);
							if(parziale.size()==numero) {
								break;
							}
						}
					}
			}
		}
			
			if(parziale.size()==numero) {
				break;
			}
			
			if(ordine.get(livello).equals("Assistman")) {
				for(Giocatore g:listaAssist(spazioSalariale,ruoli.get(livello))) {
					if(!parziale.contains(g)) {
						if(parziale.size()<numero && (g.getPosizione().contains(ruoli.get(livello)) || ruoli.get(livello).contains(g.getPosizione()))) {
							parziale.add(g);
							faiRicorsione(spazioSalariale-g.getSalary(),parziale,ruoli,ordine,livello+1,numero);
							if(parziale.size()==numero) {
								break;
							}
						}
					}
			}
		}
			if(parziale.size()==numero) {
				break;
			}
			
			if(ordine.get(livello).equals("Rimbalzista")) {
				for(Giocatore g:listaRimbalzi(spazioSalariale,ruoli.get(livello))) {
					if(!parziale.contains(g)) {
						if(parziale.size()<numero && (g.getPosizione().contains(ruoli.get(livello)) || ruoli.get(livello).contains(g.getPosizione()))) {
							parziale.add(g);
							faiRicorsione(spazioSalariale-g.getSalary(),parziale,ruoli,ordine,livello+1,numero);	
							if(parziale.size()==numero) {
								break;
							}
						}
					}
			}
		}
			
			if(parziale.size()==numero) {
				break;
			}
			
			if(ordine.get(livello).equals("Tiratore da 3")) {
				for(Giocatore g:listaTiratoriDa3(spazioSalariale,ruoli.get(livello))) {
					if(!parziale.contains(g)) {
						if(parziale.size()<numero && (g.getPosizione().contains(ruoli.get(livello)) || ruoli.get(livello).contains(g.getPosizione()))) {
							parziale.add(g);
							faiRicorsione(spazioSalariale-g.getSalary(),parziale,ruoli,ordine,livello+1,numero);	
							if(parziale.size()==numero) {
								break;
							}
						}
					}
			}
		}
			
			if(parziale.size()==numero) {
				break;
			}
			
			if(ordine.get(livello).equals("Tiratore da 2")) {
				for(Giocatore g:listaTiratoriDa2(spazioSalariale,ruoli.get(livello))) {
					if(!parziale.contains(g)) {
						if(parziale.size()<numero && (g.getPosizione().contains(ruoli.get(livello)) || ruoli.get(livello).contains(g.getPosizione()))) {
							parziale.add(g);
							faiRicorsione(spazioSalariale-g.getSalary(),parziale,ruoli,ordine,livello+1,numero);	
							if(parziale.size()==numero) {
								break;
							}
						}
					}
			}
		}
			
			if(parziale.size()==numero) {
				break;
			}
			
			if(ordine.get(livello).equals("Uomo squadra")) {
				for(Giocatore g:listaUominiSquadra(spazioSalariale,ruoli.get(livello))) {
					if(!parziale.contains(g)) {
						if(parziale.size()<numero && (g.getPosizione().contains(ruoli.get(livello)) || ruoli.get(livello).contains(g.getPosizione()))) {
							parziale.add(g);
							faiRicorsione(spazioSalariale-g.getSalary(),parziale,ruoli,ordine,livello+1,numero);	
							if(parziale.size()==numero) {
								break;
							}
						}
					}
			}
		}	
	}
	
	}
	
	public List<Giocatore> getListaOrdinataScorer(String ruolo,Squadre s,Integer salaryMax){
		return giocatoriDao.getScorers(ruolo, s,salaryMax);
	}
	
	public List<Giocatore> getListaOrdinataAssist(String ruolo,Squadre s, Integer salaryMax){
		return giocatoriDao.getAssist(ruolo, s,salaryMax);
	}
	
	public List<Giocatore> getListaOrdinataRimbalzi(String ruolo,Squadre s,Integer salaryMax){
		return giocatoriDao.getRimbalzisti(ruolo, s, salaryMax);
	}
	
	public List<Giocatore> getListaOrdinataTiratoriDa3(String ruolo,Squadre s,Integer salaryMax){
		return giocatoriDao.getTiratoriDa3(ruolo, s, salaryMax);
	}
	
	public List<Giocatore> getListaOrdinataTiratoriDa2(String ruolo,Squadre s,Integer salaryMax){
		return giocatoriDao.getTiratoriDa2(ruolo, s, salaryMax);
	}
	
	public List<Giocatore> getListaOrdinataUominiSquadra(String ruolo,Squadre s,Integer salaryMax){
		return giocatoriDao.getUominiSquadraOrdinati(ruolo, s, salaryMax);
	}
	
	public boolean giaDaAcquistare(Giocatore g) {
		if(daAcquistare.contains(g))
			return true;
		return false;
	}
	
	public void aggiungiDaAcquistare(Giocatore g) {
		if(daAcquistare.size()<3)
		daAcquistare.add(g);
	}
	
	public void rimuoviDaAcquistare(Giocatore g) {
		daAcquistare.remove(g);
	}
	
	public List<Giocatore> selezionatiDaAcquistare(){
		return daAcquistare;
	}
	
	private Integer getSalaryCapDegliAcquisti() {
		int result=0;
		for(Giocatore g: daAcquistare) {
			if(g.getSalary()>898310 && g.getSalary()!=2564753 && g.getSalary()!=1445697 && g.getSalary()!=1620564 && g.getSalary()!=1678854 && g.getSalary()!=1737145 && g.getSalary()!=1882867 && g.getSalary()!=2028594 && g.getSalary()!=2174318 && g.getSalary()!=2320044 && g.getSalary()!=2331593) {
				result+=g.getSalary();
			}
		}
	    System.out.println(""+result);
		return result;
	}
	
	public List<List<Giocatore>> trovaPossibilita(List<Giocatore> incedibili) {
		List <Giocatore> tot=getRoster(getSquadraSelezionata());
		Integer numeroGiocatoriARoster=tot.size();
		for(Giocatore g:incedibili) {
			if(tot.contains(g))
				tot.remove(g);
		}
		Double costo=(double)getSalaryCapDegliAcquisti();
		Integer livelloSalariale=getSalaryCap(getSquadraSelezionata());
		Integer spazioRosterMinimo=numeroGiocatoriARoster+daAcquistare.size()-17;
		if(spazioRosterMinimo<0)
			spazioRosterMinimo=0;
		Integer spazioRosterMassimo=numeroGiocatoriARoster+daAcquistare.size()-12;
		if(spazioRosterMassimo>3)
			spazioRosterMassimo=3;
		if((livelloSalariale+costo<=this.limiteSalariale && spazioRosterMinimo==0) || (costo==0 && spazioRosterMinimo==0)) {
			return null;}  
		
		else {
			if(livelloSalariale>=this.limiteSalariale) {
				costo=costo*100/125;
			}
			if(livelloSalariale<limiteSalariale) {
				costo=(livelloSalariale+costo)-limiteSalariale;
				costo=costo*100/125;
			}
			List<Giocatore> parziale=new ArrayList<Giocatore>();
			List<List<Giocatore>> results=new ArrayList<List<Giocatore>>();
			faiRicorsioneAcquisti(parziale,tot,0,spazioRosterMassimo,spazioRosterMinimo,costo,0,results);
			
			return results;
		}
		
	}
	
	
	
	private void faiRicorsioneAcquisti(List<Giocatore> parziale, List<Giocatore> tot,Integer livello,Integer nMax,Integer nMin,Double costo,Integer liberato,List<List<Giocatore>>results) {
		if(nMax==livello) {
			if(liberato>=costo && nMin<=livello) {
			if(controllaDoppioni(results,parziale)==false) {
				
				results.add(new ArrayList<Giocatore>(parziale));
				return;
			}
			else
				return;
		  }
			return;
		}
		
		if(liberato>=costo && livello>=nMin) {
			if(nMax>=livello) {
			if(controllaDoppioni(results,parziale)==false) {
				results.add(new ArrayList<Giocatore>(parziale));
				return; //per evitare ad esempio mi dia curry,curry+minimo,curry+minimo+minimo
			}
			}
		}
		
		
		
		for(Giocatore g:tot) {
			if(!parziale.contains(g)) {
				parziale.add(g);
				faiRicorsioneAcquisti(parziale,tot,livello+1,nMax,nMin,costo,liberato+g.getSalary(),results);
				parziale.remove(g);
			}
		}
		
		
		
		
	}
	
	
	private boolean controllaDoppioni(List<List<Giocatore>> liste,List<Giocatore> result) {
		int size=result.size();
		
		Collections.sort(result,new Comparator<Giocatore>(){
			@Override
			public int compare(Giocatore o1, Giocatore o2) {
				if(o1.getNome().equals(o2.getNome()))
					return -1;
				return o1.getNome().compareTo(o2.getNome());
			}	
		});

		for(List<Giocatore>l:liste) {
				
				Collections.sort(l,new Comparator<Giocatore>(){
					@Override
					public int compare(Giocatore o1, Giocatore o2) {
						if(o1.getNome().equals(o2.getNome()))
							return -1;
						return o1.getNome().compareTo(o2.getNome());
					}	
				});
			
			int flag=0;
			int a=l.size();
			if(a>size) {
				
			for(int i=0;i<size && flag==0;i++)
				if(!l.contains(result.get(i))) 
					flag=1;
				if(flag==0)
					return true;

		}
			else {
				flag=0;
				for(int i=0;i<a && flag==0;i++) {
					if(!result.contains(l.get(i)))
						flag=1;
				}
				if(flag==0)
					return true;
			}
	      
			
		}
		
		return false;
	}
	
	
	
	
	public void riazzeraModel() {
		if(daCedere!=null)
		daCedere.clear();
		squadraSelezionata=null;
		if(lassist!=null)
		lassist.clear();
		if(lscorer!=null)
		lscorer.clear();
		if(lrimbalzi!=null)
		lrimbalzi.clear();
		if(lda3!=null)
			lda3.clear();
		if(lda2!=null)
			lda2.clear();
		if(lplus!=null)
			lplus.clear();
		ruoloFattoScorer=null;
		ruoloFattoAssist=null;
		ruoloFattoRimbalzi=null;
		ruoloFattoUomoSquadra=null;
		ruoloFattoTiratoriDa3=null;
		ruoloFattoTiratoriDa2=null;
		if(daAcquistare!=null)
			daAcquistare.clear();
	}

	
}