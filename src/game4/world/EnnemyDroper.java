package game4.world;

import java.util.ArrayList;

public abstract class EnnemyDroper extends Ennemy {

	private ArrayList<String> contains;

	public EnnemyDroper(int speed, int pv, Cell cellule, ArrayList<String> contains) {
		super(speed, pv, cellule);
		this.contains = contains;
	}

	public ArrayList<String> getContains() {
		return contains;
	}

	public void setContains(ArrayList<String> contains) {
		this.contains = contains;
	}

	//TODO a la mort, creer des mobs correspondant a la liste contains


}
