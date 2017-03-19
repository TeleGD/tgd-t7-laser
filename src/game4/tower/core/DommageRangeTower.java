package game4.tower.core;

import game4.world.Cell;

public class DommageRangeTower extends RangeTower{
	
	private int dommage;

	public DommageRangeTower(Cell cellule, int cost, String name, int dommage, int rangeMin, int rangeMax,
			int dommage2) {
		super(cellule, cost, name, dommage, rangeMin, rangeMax);
		dommage = dommage2;
	}

	@Override
	public void action() {
		//TODO TAPER YEEEAAAAAHHHH		
	}

}
