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
		//initializeTables();
		Person p=search("JEROME");
		System.out.println(p.getScoreAtGame(3));

		/*
		ArrayList<Person> persons=getScoresForGame(3);
		for(Person p:persons){
			System.out.println(p.toString());
		}*/
	}

	
	public static Person search(String name){
		Connection c = null;
		Statement stmt = null;
		Person person = new Person(name);
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES WHERE name='"+name+"'");
			
			while (rs.next()){
				person.setScoreAtGame(rs.getInt(2),rs.getInt(3));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return person;

	}

	public static void updateScore(String name,int game,int score){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Score FROM SCORES WHERE name='"+name+"' AND Game="+game);
			if(rs.next()){
				if (score>rs.getInt(1)){
					stmt.executeUpdate("UPDATE SCORES SET Score="+score+" WHERE name='"+name+"' AND Game="+game);
				}
			}else {
				stmt.executeUpdate("INSERT INTO SCORES (Name,Game,Score) VALUES ('"+name+"',"+game+","+score+")");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public static ArrayList<Person> getScoreForGame(int gameIndex){
		ArrayList<Person> result = new ArrayList<Person>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES WHERE Game="+gameIndex+" ORDER BY Score DESC LIMIT 10");
			while (rs.next()){
				Person p=new Person(rs.getString(1));
				p.setScoreAtGame(rs.getInt(2),rs.getInt(3));
				result.add(p);
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

}