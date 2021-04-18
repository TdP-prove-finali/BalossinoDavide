package it.polito.tdp.TE_impostazione.model;

public class Giocatore {
	private String nome;
	private Squadre squadra;
	private Integer eta;
	private Integer salary;
	private String posizione;
	private Integer games_played;
	private Integer games_started;
	private Float minutes_pergame;
	private Float fg_made;
	private Float fg_attemp;
	private String fg_perc;
	private Float p3_made;
	private String p3_perce;
	private Float p3_attemp;
	private Float p2_made;
	private String p2_perce;
	private Float p2_attemp;	
//	Float ft_made;
//	Float ft_perce;
//	Float ft_attemp;
	private Float orimb;
	private Float drimb;
	private Float trimb;
	private Float assist;
	private Float steal;
	private Float turnovers;
	//Float fouls;
	private Float points;
	private Float plus;
	private Float vorp;
	private Float blocks;
	private String inj;
	
	private Float pesoScorer;
	private Float pesoAssist;
	private Float pesoRimbalzi;
	
	public Giocatore(String nome, Squadre squadra, Integer eta, Integer salary, String posizione, Integer games_palyed,
			Integer games_started, Float minutes_pergame, Float fg_made, Float fg_attemp, String fg_perc, Float p3_made,
			String p3_perce, Float p3_attemp, Float p2_made, String p2_perce, Float p2_attemp, Float orimb, Float drimb,
			Float trimb, Float assist, Float steal, Float turnovers, Float points, Float plus, Float vorp, Float blocks,
			String inj, Float pesoScorer, Float pesoAssist, Float pesoRimbalzi) {
		super();
		this.nome = nome;
		this.squadra = squadra;
		this.eta = eta;
		this.salary = salary;
		this.posizione = posizione;
		this.games_played = games_palyed;
		this.games_started = games_started;
		this.minutes_pergame = minutes_pergame;
		this.fg_made = fg_made;
		this.fg_attemp = fg_attemp;
		this.fg_perc = fg_perc;
		this.p3_made = p3_made;
		this.p3_perce = p3_perce;
		this.p3_attemp = p3_attemp;
		this.p2_made = p2_made;
		this.p2_perce = p2_perce;
		this.p2_attemp = p2_attemp;
		this.orimb = orimb;
		this.drimb = drimb;
		this.trimb = trimb;
		this.assist = assist;
		this.steal = steal;
		this.turnovers = turnovers;
		this.points = points;
		this.plus = plus;
		this.vorp = vorp;
		this.blocks = blocks;
		this.inj = inj;
		this.pesoScorer=pesoScorer;
		this.pesoAssist=pesoAssist;
		this.pesoRimbalzi=pesoRimbalzi;
		setPesoScorer(points);
		setPesoAssist(assist);
		setPesoRimbalzi(trimb);
	}
	public String getNome() {
		return nome;
	}
	public Squadre getSquadra() {
		return squadra;
	}
	public Integer getEta() {
		return eta;
	}
	public Integer getSalary() {
		return salary;
	}
	public String getPosizione() {
		return posizione;
	}
	public Integer getGames_played() {
		return games_played;
	}
	public Integer getGames_started() {
		return games_started;
	}
	public Float getMinutes_pergame() {
		return minutes_pergame;
	}
	public Float getFg_made() {
		return fg_made;
	}
	public Float getFg_attemp() {
		return fg_attemp;
	}
	public String getFg_perc() {
		return fg_perc;
	}
	public Float getP3_made() {
		return p3_made;
	}
	public String getP3_perce() {
		return p3_perce;
	}
	public Float getP3_attemp() {
		return p3_attemp;
	}
	public Float getP2_made() {
		return p2_made;
	}
	public String getP2_perce() {
		return p2_perce;
	}
	public Float getP2_attemp() {
		return p2_attemp;
	}
	public Float getOrimb() {
		return orimb;
	}
	public Float getDrimb() {
		return drimb;
	}
	public Float getTrimb() {
		return trimb;
	}
	public Float getAssist() {
		return assist;
	}
	public Float getSteal() {
		return steal;
	}
	public Float getTurnovers() {
		return turnovers;
	}
	public Float getPoints() {
		return points;
	}
	public Float getPlus() {
		return plus;
	}
	public Float getVorp() {
		return vorp;
	}
	public Float getBlocks() {
		return blocks;
	}
	public String getInj() {
		return inj;
	}
	
	
	public Float getPesoScorer() {
		return pesoScorer;
	}
	public void setPesoScorer(Float pesoScorer) {
		this.pesoScorer = pesoScorer;
	}
	public Float getPesoAssist() {
		return pesoAssist;
	}
	public void setPesoAssist(Float pesoAssist) {
		this.pesoAssist = pesoAssist;
	}
	public Float getPesoRimbalzi() {
		return pesoRimbalzi;
	}
	public void setPesoRimbalzi(Float pesoRimbalzi) {
		this.pesoRimbalzi = pesoRimbalzi;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eta == null) ? 0 : eta.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((squadra == null) ? 0 : squadra.hashCode());
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
		Giocatore other = (Giocatore) obj;
		if (eta == null) {
			if (other.eta != null)
				return false;
		} else if (!eta.equals(other.eta))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (squadra == null) {
			if (other.squadra != null)
				return false;
		} else if (!squadra.equals(other.squadra))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Giocatore [nome=" + nome + "]";
	}
	
	
	
}
