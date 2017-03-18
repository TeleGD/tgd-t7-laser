package db;

public class Person {
	
	private String name;
	private int scoreg1,scoreg2,scoreg3;
	
	public Person(String name){
		this.name=name;
		scoreg1=0;
		scoreg2=0;
		scoreg3=0;
	}
	
	public void setScore1(int score){
		this.scoreg1=score;
	}
	
	public void setScore2(int score){
		this.scoreg2=score;
	}
	
	public void setScore3(int score){
		this.scoreg3=score;
	}
	
	public int getScore1(){
		return this.scoreg1;
	}
	
	public int getScore2(){
		return this.scoreg2;
	}
	public int getScore3(){
		return this.scoreg3;
	}
	
	public String getName(){
		return name;
	}
}
