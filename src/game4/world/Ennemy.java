package game4.world;

public abstract class Ennemy {
	private int speed;
	private int pv;
	private Cell cellule;
	private boolean destroyed;

	public Ennemy(int speed, int pv, Cell cellule) {
		super();
		this.speed = speed;
		this.pv = pv;
		this.cellule = cellule;
		this.destroyed=false;
	}

	public void move(){
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

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Cell getCellule() {
		return cellule;
	}

	public void setCellule(Cell cellule) {
		this.cellule = cellule;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
