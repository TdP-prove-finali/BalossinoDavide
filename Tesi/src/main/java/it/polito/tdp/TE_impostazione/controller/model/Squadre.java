package it.polito.tdp.TE_impostazione.controller.model;

import java.util.ArrayList;
import java.util.List;

public class Squadre {
	private String abbr;
	private String nome;
	private String conference;
	
	public Squadre(String abbr, String nome, String conference) {
		super();
		this.abbr = abbr;
		this.nome = nome;
		this.conference = conference;
	}

	public String getAbbr() {
		return abbr;
	}

	public String getNome() {
		return nome;
	}

	public String getConference() {
		return conference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbr == null) ? 0 : abbr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Squadre other = (Squadre) obj;
		if (abbr == null) {
			if (other.abbr != null)
				return false;
		} else if (!abbr.equals(other.abbr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
	
	
}
