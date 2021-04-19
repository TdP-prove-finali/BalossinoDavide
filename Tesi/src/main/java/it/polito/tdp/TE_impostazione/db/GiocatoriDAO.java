package it.polito.tdp.TE_impostazione.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.TE_impostazione.model.Giocatore;
import it.polito.tdp.TE_impostazione.model.Squadre;

public class GiocatoriDAO {
	
	SquadreDAO squadreDao= new SquadreDAO(); 
	
	public List<Giocatore> getGiocatore(String nome) {
		String sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE  n.Player LIKE ? AND n.Player=t.Player AND n.Player=s.Player";
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, "%"+nome+"%");
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Squadre sp= squadreDao.squadra(rs.getString("Team"));
				Giocatore g= new Giocatore(rs.getString("Player"),sp ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"), 0.0f, 0.0f, 0.0f );
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
	
	public List<Giocatore> getListaGiocatori(Squadre s, Integer spazioSalariale){
		String sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Team<>? AND s.Games_Played>=15 AND n.Salary<=?";
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, s.getNome());
			st.setInt(2, spazioSalariale);
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Squadre sp= squadreDao.squadra(rs.getString("Team"));
				Giocatore g= new Giocatore(rs.getString("Player"),sp ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),0.0f,0.0f,0.0f );
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
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return -1.0f;
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
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return -1.0f;
	}
	
	public List<Giocatore> getListaGiocatoriAccessibiliPunti(Integer spazioSalariale, Squadre s){
		String sql="SELECT DISTINCT n.Player, t.Age, n.Team,n.Salary,n.Pos,t.VORP,t.BPM,s.Games_Played,s.Games_Started,s.Minutes_Played,s.Field_Goals_Made,s.Field_Goals_Attempted,s.Field_Goal_Percentage,s.3Point_Made,s.3Point_Attempted,s.3Point_Percentage,s.2Point_Made,s.2Point_Attempted,s.2Point_Percentage,s.Offensive_Rebounds,s.Difensive_Rebounds,s.Total_Rebounds,s.Assist,s.Steals,s.Turnovers,s.Points,s.Blocks,s.INJ "
				+ "FROM  nba_2k AS n, statpergame AS s, statistiche20 AS t "
				+ "WHERE n.Player=t.Player AND n.Player=s.Player AND n.Salary<=? AND n.Team<>? AND s.Games_Played>=15 "
				+ "ORDER BY s.Points desc";
		List<Giocatore> result=new ArrayList<Giocatore>();
		
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, spazioSalariale);
			st.setString(2, s.getNome());
			ResultSet rs= st.executeQuery();
			float media=getMediaPunti();
			
			if(rs.next()) {
				Squadre sp= squadreDao.squadra(rs.getString("Team"));
				Giocatore g= new Giocatore(rs.getString("Player"),sp ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),0.0f,0.0f,0.0f );
				result.add(g);
			}
			while(rs.next()) {
				Squadre sp= squadreDao.squadra(rs.getString("Team"));
				Giocatore g= new Giocatore(rs.getString("Player"),sp ,rs.getInt("Age"), rs.getInt("Salary"), rs.getString("Pos"), rs.getInt("Games_Played"), rs.getInt("Games_Started"), rs.getFloat("Minutes_Played"), rs.getFloat("Field_Goals_Made"),rs.getFloat("Field_Goals_Attempted"), rs.getString("Field_Goal_Percentage"),rs.getFloat("3Point_Made"),rs.getString("3Point_Percentage") ,rs.getFloat("3Point_Attempted"), rs.getFloat("2Point_Made"), rs.getString("2Point_Percentage") ,rs.getFloat("2Point_Attempted"), rs.getFloat("Offensive_Rebounds"),rs.getFloat("Difensive_Rebounds"),rs.getFloat("Total_Rebounds"),rs.getFloat("Assist"),rs.getFloat("Steals"),rs.getFloat("Turnovers"),rs.getFloat("Points"), rs.getFloat("BPM"), rs.getFloat("VORP"),rs.getFloat("Blocks"),rs.getString("INJ"),0.0f,0.0f,0.0f );
				if(g.getPoints()+media>=result.get(0).getPoints()-media)
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
}
