package game4.tower.core;

import game4.world.Cell;

public abstract class Tower {
	private Cell cellule;
	private int cost;
	private String name;

	
	public Tower(Cell cellule, int cost, String name) {
		super();
		this.cellule = cellule;
		this.cost = cost;
		this.name = name;
	}

	public Cell getCellule() {
		return cellule;
	}

	public int getCost() {
		return cost;
	}

	public void setCellule(Cell cellule) {
		this.cellule = cellule;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void action();
}
