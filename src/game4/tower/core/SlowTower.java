package game4.tower.core;

import game4.world.Cell;

public class SlowTower extends RangeTower{
	private boolean slow;	
	private int slowX;
	
	public SlowTower(Cell cellule, int cost, String name, int rangeMin, int rangeMax){
		super(cellule, 10, "SlowTower", rangeMin, rangeMax);
	}
	
	public void action(){
		slow = true;
		slowX = 1;
		
	}
	
	public boolean getSlow(){
		return slow;
	}
	
	public int getSlowX(){
		return slowX;
	}

}
