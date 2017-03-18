package db;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC {

	// CREATE TABLE when datas doesn't exist
	public static void initializeTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt = c.createStatement();


			String sql ="CREATE TABLE SCORES " + "(Name VARCHAR(10), Game INT,Score INT);";
			stmt.executeUpdate(sql);
			sql="INSERT INTO SCORES (Name,Game,Score) VALUES ('nicorb',1,2500),('nicorb',2,2500),('nicorb',3,2500)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}


	public static void main( String args[]){
		initializeTables();
	}

	public static Person search(String name){
		Connection c = null;
		Statement stmt = null;
		Person result = new Person(name);
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES WHERE name='"+name+"'");
			while (rs.next()){
				switch (rs.getInt(2)){
				case 1:
					result.setScore1(rs.getInt(3));
					break;
				case 2:
					result.setScore2(rs.getInt(3));
					break;
				case 3:
					result.setScore3(rs.getInt(3));
					break;
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;

	}

	public static void updateScore(String name,int game,int score){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Score FROM SCORES WHERE name='"+name+"' AND Game="+score);
			if (score>rs.getInt(1)){
				stmt.executeUpdate("UPDATE SCORES SET Score="+score+" WHERE name='"+name+"' AND Game="+game);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

}