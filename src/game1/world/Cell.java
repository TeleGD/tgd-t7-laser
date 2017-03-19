package game1.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell{
	
	private boolean northWall,southWall,westWall,eastWall;
	private int i,j;
	private boolean partOfTheMaze;
	private boolean itsATrap;
	private boolean isFinalCell;
	private boolean giveScore;
	private Image sprite;
	
	public Cell (int i, int j)
	{
		this.i = i;
		this.j = j;
		this.setNorthWall(true);
		this.setSouthWall(true);
		this.setEastWall(true);
		this.setWestWall(true);
		this.giveScore=false;
		//TODO LIGNE A CORRIGER PLUS TARD
		try {
			this.sprite = new Image("Images/Labyrinth/noWalls.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Cell (int i, int j, boolean east, boolean north, boolean south, boolean west){
		this.i = i;
		this.j = j;
		this.setNorthWall(north);
		this.setSouthWall(south);
		this.setEastWall(east);
		this.setWestWall(west);
		this.giveScore=false;
		try {
			autoSetSprite();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void autoSetSprite() throws SlickException {
	// Automatically selects the right sprite 
		if(eastWall){
			if(northWall){
				if(westWall) sprite = new Image("Images/Labyrinth/deadEnd_up.png");
				else if(southWall) sprite = new Image("Images/Labyrinth/deadEnd_right.png");
				else sprite = new Image("Images/Labyrinth/corner_left-down.png");
			}
			else{
				if(westWall){
					if(southWall) sprite = new Image("Images/Labyrinth/deadEnd_down.png");
					else sprite = new Image("Images/Labyrinth/vertical.png");
				}
				else{
					if(southWall) sprite = new Image("Images/Labyrinth/corner_left-up.png");
					else sprite = new Image("Images/Labyrinth/tJunction_right.png");
				}
			}
		}
		else{
			if(northWall){
				if(westWall){
					if(southWall) sprite = new Image("Images/Labyrinth/deadEnd_left.png");
					else sprite = new Image("Images/Labyrinth/corner_right-down.png");
				}
				else{
					if(southWall) sprite = new Image("Images/Labyrinth/horizontal.png");
					else sprite = new Image("Images/Labyrinth/tJunction_up.png");
				}
			}
			else{
				if(westWall){
					if(southWall) sprite = new Image("Images/Labyrinth/corner_right-up.png");
					else sprite = new Image("Images/Labyrinth/tJunction_left.png");
				}
				else{
					if(southWall) sprite = new Image("Images/Labyrinth/tJunction_down.png");
					else sprite = new Image("Images/Labyrinth/noWalls.png");
				}
			}
		}		
		
	}

	public boolean isWestWall() 
	{
		return westWall;
	}

	public void setWestWall(boolean westWall) 
	{
		this.westWall = westWall;
	}

	public boolean isNorthWall() 
	{
		return northWall;
	}

	public void setNorthWall(boolean northWall) 
	{
		this.northWall = northWall;
	}

	public boolean isSouthWall() 
	{
		return southWall;
	}

	public void setSouthWall(boolean southWall) 
	{
		this.southWall = southWall;
	}

	public boolean isEastWall() 
	{
		return eastWall;
	}

	public void setEastWall(boolean eastWall) 
	{
		this.eastWall = eastWall;
	}
	
	public boolean isItsATrap() {
		return itsATrap;
	}

	public void setItsATrap(boolean itsATrap) {
		this.itsATrap = itsATrap;
	}

	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
	{
		arg2.drawImage(sprite,j*64,i*64);
		if (itsATrap){
			arg2.setColor(Color.red);
			arg2.fillRect(j*62, i*62, 60, 60);
		}
		if (isFinalCell){
			arg2.setColor(Color.cyan);
			arg2.fillRect(j*62, i*62, 60, 60);
		}
	}

	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (this.equals(World1.getPlayer())){
			if (giveScore){
				giveScore=false;
				World1.setScore(World1.getScore()+1);
			}
			if (isFinalCell){
				System.exit(0);
			}
			if (itsATrap){
				System.exit(0);
			}
		}
	}
	
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public boolean isPartOfTheMaze() {
		return partOfTheMaze;
	}

	public void setPartOfTheMaze(boolean partOfTheMaze) {
		this.partOfTheMaze = partOfTheMaze;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public boolean isFinalCell() {
		return isFinalCell;
	}

	public void setFinalCell(boolean isFinalCell) {
		this.isFinalCell = isFinalCell;
	}

	public boolean isGiveScore() {
		return giveScore;
	}

	public void setGiveScore(boolean giveScore) {
		this.giveScore = giveScore;
	}
	

}
