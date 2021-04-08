package it.polito.tdp.TE_impostazione.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.TE_impostazione.model.Squadre;


public class SquadreDAO {
	
	public List<Squadre> nomiSquadre(){
		
		String sql="SELECT * "
				+ "FROM teams";
		List<Squadre> s=new ArrayList<Squadre>();
		try {
			Connection conn=DBConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Squadre sq=new Squadre(rs.getString("abbreviation"),rs.getString("name"),rs.getString("conference"));
				s.add(sq);
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e); ////////////
		}
		
		return s;
	}

}
