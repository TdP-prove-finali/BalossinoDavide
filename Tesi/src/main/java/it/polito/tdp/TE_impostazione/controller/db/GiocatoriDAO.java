package it.polito.tdp.TE_impostazione.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.TE_impostazione.controller.model.Archetipo;
import it.polito.tdp.TE_impostazione.controller.model.Giocatore;
import it.polito.tdp.TE_impostazione.controller.model.Squadre;

public class GiocatoriDAO {
	
	private SquadreDAO squadreDao= new SquadreDAO(); 

	
	private List<Squadre> getTutteSquadre() {
		List<Squadre> tutteSquadre=new ArrayList<Squadre>();
		if(tutteSquadre==null || tutteSquadre.size()==0) {
			tutteSquadre=squadreDao.tutteSquadre();
		}
		return tutteSquadre;
	}
	
	private Squadre getSquadra(String team) {
		Squadre sq=null;
		for(Squadre s: getTutteSquadre()) {
			if(s.getNome().equals(team)) {
				sq=s;
				break;}
		}
		return sq;
	}
	
	public List<Giocatore> getGiocatore(String nome, Integer salaryMax, String posizione) {
		String campi[]=posizione.split("-");
		String sql="";
		
			if(campi.length==1)
				sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE  n.Player LIKE ? AND n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Pos LIKE ?";
			if(campi.length==2)
				sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE  n.Player LIKE ? AND n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND (n.Pos LIKE ? OR n.Pos LIKE ?)";
			if(campi.length==3)
				sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE  n.Player LIKE ? AND n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?)";
		
			
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, "%"+nome+"%");
			if(salaryMax!=0) 
				st.setInt(2, salaryMax);
			else
				st.setInt(2,getStipendioMassimo());
			
			if(campi.length==1) 
				st.setString(3, "%"+posizione+"%");
				if(campi.length==2) {
					st.setString(3, "%"+campi[0]+"%");
					st.setString(4,"%"+campi[1]+"%" );
				}
				if(campi.length==3) {
					st.setString(3, "%"+campi[0]+"%");
					st.setString(4,"%"+campi[1]+"%" );
					st.setString(5, "%"+campi[2]+"%");
				}
			
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	/*
	public List<Giocatore> getListaGiocatori(Squadre s, Integer spazioSalariale){
		String sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Team<>? AND s.Games_Played>=15 AND n.Salary<=?";
		List<Giocatore> result=new ArrayList<Giocatore>();
		Float mediaPunti=getMediaPunti();
		Float mediaSalary=getMediaSalary();
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, s.getNome());
			st.setInt(2, spazioSalariale);
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Squadre sp= squadreDao.squadra(rs.getString("Team"));
				Giocatore g= new Giocatore(rs.getString("Player"),sp ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),0.0f,0.0f,0.0f );
				if(g.getEta()<25) {
					g.setPesoScorer(g.getPesoScorer()+mediaPunti/2);
				}
				if(g.getEta()>30) {
					g.setPesoScorer(g.getPesoScorer()-mediaPunti/2);
				}
				if(g.getSalary()<mediaSalary) {
					g.setPesoScorer(g.getPesoScorer()+mediaPunti/2);
				}
				if(g.getSalary()>20*Math.pow(10, 6)) {
					g.setPesoScorer(g.getPesoScorer()-mediaPunti/2);
				}
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	} */
	 
	
	public Float getMediaPunti() {
		String sql="SELECT AVG (s.Points) "
				+ "FROM nba_2k AS n, statpergame AS s "
				+ "WHERE n.Player=s.Player";
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			
			if(rs.next()) {
				Float result=rs.getFloat("AVG (s.Points)");
				rs.close();
				st.close();
				conn.close();
				return result;
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return -1.0f;
	}
	
	public Float getMediaAssist() {
		String sql="SELECT AVG (s.Assist) "
				+ "FROM nba_2k AS n, statpergame AS s "
				+ "WHERE n.Player=s.Player";
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			if(rs.next()) {
				Float result=rs.getFloat("AVG (s.Assist)");
				rs.close();
				st.close();
				conn.close();
				return result;
			}
			rs.close();
			st.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return -1.0f;
	}
	
	public Float getMediaRimbalzi() {
		String sql="SELECT AVG (s.Total_Rebounds) "
				+ "FROM nba_2k AS n, statpergame AS s "
				+ "WHERE n.Player=s.Player ";
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			
			if(rs.next()) {
				Float result=rs.getFloat("AVG (s.Total_Rebounds)");
				rs.close();
				st.close();
				conn.close();
				return result;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return -1.0f;
	}
	
	public Float getPercentualeDa3() {
		String sql="SELECT AVG(s.3Point_Percentage) AS perc "
				+ "FROM statpergame AS s";
		Float result=0f;
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			if(rs.next()) 
			    result=rs.getFloat("perc");
			rs.close();
			st.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public Float getPercentualeDa2() {
		String sql="SELECT AVG(s.2Point_Percentage) AS perc "
				+ "FROM statpergame AS s";
		Float result=0f;
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			if(rs.next()) 
			    result=rs.getFloat("perc");
			rs.close();
			st.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public Float getMediaVORP() {
		String sql="SELECT AVG(s.VORP) AS med "
				+ "FROM statistiche20 AS s";
		Float result=0f;
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			if(rs.next()) 
			    result=rs.getFloat("med");
			rs.close();
			st.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	
	
	public Float getMediaSalary() {
		String sql="SELECT AVG (n.Salary) "
				+ "FROM nba_2k AS n, statpergame AS s "
				+ "WHERE n.Player=s.Player";
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			
			if(rs.next()) {
				Float result=rs.getFloat("AVG (n.Salary)");
				rs.close();
				st.close();
				conn.close();
				return result;
			}
			rs.close();
			st.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return -1.0f;
	}
	
	public Integer getStipendioMassimo() {
		String sql="SELECT MAX(Salary) "
				+ "FROM nba_2k";
		Integer max=0;
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs= st.executeQuery();
			
			if(rs.next()) {
				max=rs.getInt("MAX(Salary)");
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return max;
	}
	
	public List<Giocatore> getListaGiocatoriAccessibiliPunti(Integer spazioSalariale, Squadre s, String posizione){
		String campi[]=posizione.split("-");
		String sql="";
		if(campi.length==1)
		sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ?";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
					+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
					+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?)";
		if(campi.length==3)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
					+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
					+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?)";
		
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, spazioSalariale);
			st.setString(2, s.getNome());
			if(campi.length==1) 
				st.setString(3, "%"+posizione+"%");
				if(campi.length==2) {
					st.setString(3, "%"+campi[0]+"%");
					st.setString(4,"%"+campi[1]+"%" );
				}
				if(campi.length==3) {
					st.setString(3, "%"+campi[0]+"%");
					st.setString(4,"%"+campi[1]+"%" );
					st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			float mediaPunti=getMediaPunti();
			Float mediaSalary=getMediaSalary();
			
			while(rs.next()) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")),rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				if(g.getEta()<25) {
					g.setPesoScorer(g.getPesoScorer()+mediaPunti/2);
				}
				if(g.getEta()>30) {
					g.setPesoScorer(g.getPesoScorer()-mediaPunti/2);
				}
				if(g.getSalary()<mediaSalary) {
					g.setPesoScorer(g.getPesoScorer()+mediaPunti/2);
				}
				if(g.getSalary()>20*Math.pow(10, 6)) {
					g.setPesoScorer(g.getPesoScorer()-mediaPunti/2);
				}
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public List<Giocatore> getListaGiocatoriAccessibiliAssist(Integer spazioSalariale, Squadre s, String posizione){
		String campi[]=posizione.split("-");
		String sql="";
		if(campi.length==1) {
		sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ?"; }
		if(campi.length==2) {
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
					+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
					+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?)";
		}
		if(campi.length==3) {
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
					+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
					+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?)";
		}
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, spazioSalariale);
			st.setString(2, s.getNome());
			if(campi.length==1)
			st.setString(3, "%"+posizione+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
			}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
			}
			ResultSet rs= st.executeQuery();
			float mediaAssist=getMediaAssist();
			Float mediaSalary=getMediaSalary();
			
			while(rs.next()) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				if(g.getEta()<25) {
					g.setPesoAssist(g.getPesoAssist()+mediaAssist);
				}
				if(g.getEta()>30) {
					g.setPesoAssist(g.getPesoAssist()-mediaAssist);
				}
				if(g.getSalary()<mediaSalary) {
					g.setPesoAssist(g.getPesoAssist()+mediaAssist);
				}
				if(g.getSalary()>20*Math.pow(10, 6)) {
					g.setPesoAssist(g.getPesoAssist()-mediaAssist);
				}
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	public List<Giocatore> getListaGiocatoriAccessibiliRimbalzi(Integer spazioSalariale, Squadre s, String posizione){
		String campi[]=posizione.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ?";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?)";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?)";
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, spazioSalariale);
			st.setString(2, s.getNome());
			if(campi.length==1)
				st.setString(3, "%"+posizione+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			float mediaRimbalzi=getMediaRimbalzi();
			Float mediaSalary=getMediaSalary();
			
			while(rs.next()) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				if(g.getEta()<25) {
					g.setPesoRimbalzi(g.getPesoRimbalzi()+mediaRimbalzi/2);
				}
				if(g.getEta()>30) {
					g.setPesoRimbalzi(g.getPesoRimbalzi()-mediaRimbalzi/2);
				}
				if(g.getSalary()<mediaSalary) {
					g.setPesoRimbalzi(g.getPesoRimbalzi()+mediaRimbalzi/2);
				}
				if(g.getSalary()>20*Math.pow(10, 6)) {
					g.setPesoRimbalzi(g.getPesoRimbalzi()-mediaRimbalzi/2);
				}
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public List<Giocatore> getListaGiocatoriAccessibiliTiratoriDa3(Integer spazioSalariale, Squadre s, String posizione){
		String campi[]=posizione.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.3Point_Attempted>2 AND s.Games_Played>=15 AND n.Pos LIKE ?";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.3Point_Attempted>2 AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?)";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.3Point_Attempted>2 AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?)";
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, spazioSalariale);
			st.setString(2, s.getNome());
			if(campi.length==1)
				st.setString(3, "%"+posizione+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			float percentualeMedia=getPercentualeDa3();
			Float mediaSalary=getMediaSalary();
			
			while(rs.next()) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				if(g.getEta()<25) {
					g.setPesoTiratoreDa3(g.getPesoTiratoreDa3()+percentualeMedia/4);
				}
				if(g.getEta()>30) {
					g.setPesoTiratoreDa3(g.getPesoTiratoreDa3()-percentualeMedia/4);
				}
				if(g.getSalary()<mediaSalary) {
					g.setPesoTiratoreDa3(g.getPesoTiratoreDa3()+percentualeMedia/4);
				}
				if(g.getSalary()>20*Math.pow(10, 6)) {
					g.setPesoTiratoreDa3(g.getPesoTiratoreDa3()-percentualeMedia/4);
				}
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public List<Giocatore> getListaGiocatoriAccessibiliTiratoriDa2(Integer spazioSalariale, Squadre s, String posizione){
		String campi[]=posizione.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.2Point_Attempted3 AND s.Games_Played>=15 AND n.Pos LIKE ?";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.2Point_Attempted>3 AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?)";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.2Point_Attempted>3 AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?)";
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, spazioSalariale);
			st.setString(2, s.getNome());
			if(campi.length==1)
				st.setString(3, "%"+posizione+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			float percentualeMedia=getPercentualeDa3();
			Float mediaSalary=getMediaSalary();
			
			while(rs.next()) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				if(g.getEta()<25) {
					g.setPesoTiratoreDa2(g.getPesoTiratoreDa2()+percentualeMedia/6);
				}
				if(g.getEta()>30) {
					g.setPesoTiratoreDa2(g.getPesoTiratoreDa2()-percentualeMedia/6);
				}
				if(g.getSalary()<mediaSalary) {
					g.setPesoTiratoreDa2(g.getPesoTiratoreDa2()+percentualeMedia/6);
				}
				if(g.getSalary()>20*Math.pow(10, 6)) {
					g.setPesoTiratoreDa2(g.getPesoTiratoreDa2()-percentualeMedia/6);
				}
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public List<Giocatore> getListaGiocatoriAccessibiliUomoSquadra(Integer spazioSalariale, Squadre s, String posizione){
		String campi[]=posizione.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ?";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?)";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND (n.Salary<=? OR n.Salary<=898310 OR n.Salary=2564753 OR n.Salary=1445697 OR n.Salary=1620564 OR n.Salary=1678854 OR n.Salary=1737145 OR n.Salary=1882867 OR n.Salary=2028594 OR n.Salary=2174318 OR n.Salary=2320044 OR n.Salary=2331593) AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?)";
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, spazioSalariale);
			st.setString(2, s.getNome());
			if(campi.length==1)
				st.setString(3, "%"+posizione+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			float mediaVORP=getMediaVORP();
			Float mediaSalary=getMediaSalary();
			
			while(rs.next()) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				if(g.getEta()<25) {
					g.setPesoUomoSquadra(g.getPesoUomoSquadra()+mediaVORP);
				}
				if(g.getEta()>30) {
					g.setPesoUomoSquadra(g.getPesoUomoSquadra()-mediaVORP);
				}
				if(g.getSalary()<mediaSalary) {
					g.setPesoUomoSquadra(g.getPesoUomoSquadra()+mediaVORP);
				}
				if(g.getSalary()>20*Math.pow(10, 6)) {
					g.setPesoUomoSquadra(g.getPesoUomoSquadra()-mediaVORP);
				}
				result.add(g);
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	
	public List<Giocatore> getScorers(String ruolo, Squadre s, Integer salaryMax){
		String campi[]=ruolo.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ? "
		        + "ORDER BY s.Points desc";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.Points desc";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.Points desc";
		
	
		List<Giocatore> result=new ArrayList<Giocatore>();


		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			if(salaryMax!=0) 
				st.setInt(1, salaryMax);
			else
				st.setInt(1,getStipendioMassimo());
			
			st.setString(2, s.getNome());
			
			if(campi.length==1)
				st.setString(3, "%"+ruolo+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
			    }
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			int i=0;
			
			while(rs.next() && i<50) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				result.add(g);
				i++;
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
		}
	
	public List<Giocatore> getAssist(String ruolo, Squadre s, Integer salaryMax){
		String campi[]=ruolo.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ? "
		        + "ORDER BY s.Assist desc";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.Assist desc";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.Assist desc";
		
	
		List<Giocatore> result=new ArrayList<Giocatore>();


		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			if(salaryMax!=0) 
				st.setInt(1, salaryMax);
			else
				st.setInt(1,getStipendioMassimo());
			st.setString(2, s.getNome());
			
			if(campi.length==1)
				st.setString(3, "%"+ruolo+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			int i=0;
			
			while(rs.next() && i<50) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				result.add(g);
				i++;
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
		}
	
	public List<Giocatore> getRimbalzisti(String ruolo, Squadre s,Integer salaryMax){
		String campi[]=ruolo.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ? "
		        + "ORDER BY s.Total_Rebounds desc";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.Total_Rebounds desc";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.Total_Rebounds desc";
		
	
		List<Giocatore> result=new ArrayList<Giocatore>();


		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			if(salaryMax!=0) 
				st.setInt(1, salaryMax);
			else
				st.setInt(1,getStipendioMassimo());
			st.setString(2, s.getNome());
			
			if(campi.length==1)
				st.setString(3, "%"+ruolo+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			int i=0;
			
			while(rs.next() && i<50) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				result.add(g);
				i++;
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
		}
	
	public List<Giocatore> getTiratoriDa3(String ruolo, Squadre s,Integer salaryMax){
		String campi[]=ruolo.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.3Point_Attempted>2 AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ? "
		        + "ORDER BY s.3Point_Percentage desc";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.3Point_Attempted>2 AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.3Point_Percentage desc";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.3Point_Attempted>2 AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.3Point_Percentage desc";
		
	
		List<Giocatore> result=new ArrayList<Giocatore>();


		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			if(salaryMax!=0) 
				st.setInt(1, salaryMax);
			else
				st.setInt(1,getStipendioMassimo());
			st.setString(2, s.getNome());
			
			if(campi.length==1)
				st.setString(3, "%"+ruolo+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			int i=0;
			
			while(rs.next() && i<50) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				result.add(g);
				i++;
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
		}
	
	public List<Giocatore> getTiratoriDa2(String ruolo, Squadre s,Integer salaryMax){
		String campi[]=ruolo.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.2Point_Attempted>3 AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ? "
		        + "ORDER BY s.2Point_Percentage desc";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.2Point_Attempted>3 AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.2Point_Percentage desc";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND s.2Point_Attempted>3 AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY s.2Point_Percentage desc";
		
	
		List<Giocatore> result=new ArrayList<Giocatore>();


		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			if(salaryMax!=0) 
				st.setInt(1, salaryMax);
			else
				st.setInt(1,getStipendioMassimo());
			st.setString(2, s.getNome());
			
			if(campi.length==1)
				st.setString(3, "%"+ruolo+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			int i=0;
			
			while(rs.next() && i<50) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				result.add(g);
				i++;
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
		}
	
	public List<Giocatore> getUominiSquadraOrdinati(String ruolo, Squadre s,Integer salaryMax){
		String campi[]=ruolo.split("-");
		String sql="";
		if(campi.length==1)
		  sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND n.Pos LIKE ? "
		        + "ORDER BY t.VORP desc";
		if(campi.length==2)
			sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY t.VORP desc";
		if(campi.length==3)
		 sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ,n.Height,n.Weight "
						+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
						+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 AND (n.Pos LIKE ? OR n.Pos LIKE ? OR n.Pos LIKE ?) "
						+ "ORDER BY t.VORP desc";
		
	
		List<Giocatore> result=new ArrayList<Giocatore>();


		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			if(salaryMax!=0) 
				st.setInt(1, salaryMax);
			else
				st.setInt(1,getStipendioMassimo());
			st.setString(2, s.getNome());
			
			if(campi.length==1)
				st.setString(3, "%"+ruolo+"%");
			if(campi.length==2) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				}
			if(campi.length==3) {
				st.setString(3, "%"+campi[0]+"%");
				st.setString(4,"%"+campi[1]+"%" );
				st.setString(5, "%"+campi[2]+"%");
				}
			ResultSet rs= st.executeQuery();
			int i=0;
			
			while(rs.next() && i<50) {
				Giocatore g= new Giocatore(rs.getString("Player"),getSquadra(rs.getString("Team")) ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),rs.getString("Weight"),rs.getString("Height") );
				result.add(g);
				i++;
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
		}
	
	
}


