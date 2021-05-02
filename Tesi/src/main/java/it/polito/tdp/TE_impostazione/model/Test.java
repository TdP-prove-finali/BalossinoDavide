package it.polito.tdp.TE_impostazione.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.TE_impostazione.db.SquadreDAO;

public class Test {

	public static void main(String[] args) {
		SquadreDAO squadreDao=new SquadreDAO();
		Squadre s=squadreDao.squadra("Detroit Pistons");
		//System.out.println(squadreDao.roster(s));

		
		Model m=new Model();
		List<Archetipo> scelti=new ArrayList<Archetipo>();
		scelti.add(new Archetipo("Assistman","G"));
		scelti.add(new Archetipo("Assistman","C"));
		m.setSquadraSelezionata(s);
		long inizio=System.currentTimeMillis();
		System.out.println(m.trovaMiglioriGiocatori(scelti,6854709));
		long fine=System.currentTimeMillis();
    	System.out.println(fine-inizio);
		
	}

}

/*
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
} */





/*	for(Giocatore g:lscorer) {
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
} */
