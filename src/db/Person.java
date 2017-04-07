package db;

import java.util.ArrayList;
import java.util.HashMap;

public class Person {
	
	private String name;
	private HashMap<Integer, Integer> scores;
	
	public Person(String name){
		this.name=name;
		scores=new HashMap<Integer,Integer>();
	}
	
	
	
	public String getName(){
		return name;
	}

	public void setScoreAtGame(int indexGame, int score) {
		scores.put(indexGame, score);
	}

	public int getScoreAtGame(int indexGame) {
		if(scores.containsKey(indexGame))return scores.get(indexGame);
		return 0;
	}
	
	
	@Override
	public String toString(){
		String s=name+": ";
		s+=scores.toString();
		return s;
	}



	public ArrayList<Integer> getGamesPlayed() {
		ArrayList<Integer> gamePlayed=new ArrayList<Integer>();
		for(Integer i:scores.keySet()){
			gamePlayed.add(i);
		}
		return gamePlayed;
	}
}
