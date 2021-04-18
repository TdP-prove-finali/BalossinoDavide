package it.polito.tdp.TE_impostazione.model;

import it.polito.tdp.TE_impostazione.db.SquadreDAO;

public class Test {

	public static void main(String[] args) {
		SquadreDAO squadreDao=new SquadreDAO();
		Squadre s=squadreDao.squadra("Charlotte Hornets");
		//System.out.println(squadreDao.roster(s));

		
		Model m=new Model();
		long inizio=System.currentTimeMillis();
		System.out.println(m.listaScorer(1000000, s).get(0));
		long fine=System.currentTimeMillis();
		System.out.println(fine-inizio);
	}

}
