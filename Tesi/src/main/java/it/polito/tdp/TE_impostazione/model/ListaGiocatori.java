package it.polito.tdp.TE_impostazione.model;

import java.util.ArrayList;
import java.util.List;

public class ListaGiocatori {
	
	private List<Giocatore> lista;
	
	
	public void aggiungiGiocatore(Giocatore g) {
		lista.add(g);
	}
	
	public void aggiungiLista(List<Giocatore> l) {
		lista=new ArrayList<Giocatore>(l);
	}

}
