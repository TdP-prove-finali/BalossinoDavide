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
		System.out.println(m.trovaMiglioriGiocatori(scelti,s,6854709));
		long fine=System.currentTimeMillis();
    	System.out.println(fine-inizio);
		
	}

}
