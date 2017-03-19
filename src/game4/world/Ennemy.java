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

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
