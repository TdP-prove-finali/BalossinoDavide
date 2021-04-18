package it.polito.tdp.TE_impostazione.model;

public class Archetipo {
	
	private String tipo;
	private String ruolo;
	
	public Archetipo(String tipo, String ruolo) {
		super();
		this.tipo = tipo;
		this.ruolo = ruolo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getRuolo() {
		return ruolo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Archetipo other = (Archetipo) obj;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	

}
