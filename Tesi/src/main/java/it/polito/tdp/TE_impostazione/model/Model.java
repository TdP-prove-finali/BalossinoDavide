package it.polito.tdp.TE_impostazione.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.TE_impostazione.db.SquadreDAO;

public class Model {
	
	private SquadreDAO squadreDAO;
	private List<Squadre> squadre;
	
	public Model() {
		squadreDAO=new SquadreDAO();
		squadre=new ArrayList<Squadre>();
	}
	
	public List<Squadre> getNomiSquadre(){
		return squadreDAO.nomiSquadre();
		
	}
}
