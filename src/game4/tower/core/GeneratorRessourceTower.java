package game4.tower.core;

import game4.world.Cell;

public abstract class GeneratorRessourceTower extends Tower{

	private int giveRessource;

	public GeneratorRessourceTower(Cell cellule, int cost, String name, int giveRessource) {
		super(cellule, cost, name);
		this.giveRessource = giveRessource;
	}

	public int getGiveRessource() {
		return giveRessource;
	}

	public void setGiveRessource(int giveRessource) {
		this.giveRessource = giveRessource;
	}
	
	public void action(){
		World.setRessource(World.getRessource()+this.giveRessource);
	}
	
	
	
	
}
