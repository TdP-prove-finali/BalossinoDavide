package it.polito.tdp.TE_impostazione.model;

import it.polito.tdp.TE_impostazione.db.SquadreDAO;

public class Test {

	public static void main(String[] args) {
		SquadreDAO squadreDao=new SquadreDAO();
		Squadre s=squadreDao.squadra("Charlotte Hornets");
		System.out.println(squadreDao.roster(s));

	}

}
