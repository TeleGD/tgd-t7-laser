package game4.tower.core;

import game4.world.Cell;

public abstract class RangeTower extends Tower {

	private int rangeMin;
	private int rangeMax;


	public RangeTower(Cell cellule, int cost, String name, int rangeMin, int rangeMax) {
		super(cellule, cost, name);
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
	}

	public int getRangeMin() {
		return rangeMin;
	}
	public int getRangeMax() {
		return rangeMax;
	}
	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}
	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}





}
