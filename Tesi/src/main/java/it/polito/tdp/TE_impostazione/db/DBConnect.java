package it.polito.tdp.TE_impostazione.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	public static Connection getConnection() {
		String jdbcURL="jdbc:mysql://localhost/nba_stats?user=root&password=lebron";
		try {
			Connection conn=DriverManager.getConnection(jdbcURL);
			return conn;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
