package game4.tower.core;

import game4.world.Cell;

public class DommageTowerLevel1 extends DommageRangeTower{

	public DommageTowerLevel1(Cell cellule, int cost, String name, int rangeMin, int rangeMax,
			int dommage2){
		super(cellule, 5, "Dommage Tower Level 1", 0, 3,	5);
	}

	public void action(){
		super.action();
	}

}
