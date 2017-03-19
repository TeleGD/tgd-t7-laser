package game4.world;

public class Ennemy {
	private int speedX;
	private int speedY;
	private int pv;
	private int x;
	private int y;
	
	public Ennemy(){
		x = 100;
		y = 100;
		pv = 100;
	}
	
	public void move(){
		speedX = 0;
		speedY = 0;
		
		//TODO phase de test pour savoir si il y a des tours pour que l'ennemi s'avance ou pas
	}
	
	public void pvDecrease(int degat){
		pv = pv - degat;
	}
	
	public int getPv(){
		return pv;
	}
	
	public void update(int degat){
		pvDecrease(degat);
		move();
	}
	
	
}
