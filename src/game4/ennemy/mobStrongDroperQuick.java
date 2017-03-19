package game4.ennemy;

import java.util.ArrayList;

import game4.world.Cell;
import game4.world.EnnemyDroper;

public class mobStrongDroperQuick extends EnnemyDroper{

	public mobStrongDroperQuick(Cell cellule) {
		super(1,300,cellule, new ArrayList<String>());
		for (int i = 0; i < 4; i++) {
			this.getContains().add("mobQuickWeak");
		}
	}
}
