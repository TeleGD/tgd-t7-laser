package game2.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {

	public static final int NORMAL_TYPE = 0 ;
	public static final int MINE_TYPE = 1 ;
	public static final int BONUS_TYPE = 2 ;

	protected static Image NORMAL;
	protected static Image MINE;
	protected static Image BONUS;
	
	//Variables
	private int x;
	private int y;
	private Boolean contains;
	private Boolean deadly;
	private Boolean hasBonus;
	private boolean hasEnnemy;
	
	private int imageType;

	//Constructeur
	public Cell(int x, int y, Boolean c, Boolean d) throws SlickException{
		this.x=x;
		this.y=y;
		this.contains=c;
		this.deadly=d;
		this.hasBonus=false;
		this.hasEnnemy = false;
		

		if(NORMAL==null){
			NORMAL= new Image(World2.DIRECTORY_IMAGES+"Cell.png");
			MINE=new Image(World2.DIRECTORY_IMAGES+"Mine.png");
			BONUS=new Image(World2.DIRECTORY_IMAGES+"Bonus.png");
		}
	}
	
	
	//Getters et Setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Boolean getContains() {
		return contains;
	}
	public void setContains(Boolean contains) {
		this.contains = contains;
	}
	public Boolean getDeadly() {
		return deadly;
	}
	public void setDeadly(Boolean deadly) {
		this.deadly = deadly;
		if(this.imageType == MINE_TYPE)
			this.deadly = true;
	}
	public int getImageType() {
		return imageType;
	}

	public void setImageType(int imageType) {
		this.imageType = imageType;
	}

    public Image getImage() {
        if(imageType==MINE_TYPE)return MINE;
        else if(imageType==NORMAL_TYPE)return NORMAL;
        else if(imageType==BONUS_TYPE)return BONUS;
        return NORMAL;
    }
	public boolean isHasEnnemy() {
		return hasEnnemy;
	}
	public void setHasEnnemy(boolean hasEnnemy) {
		this.hasEnnemy = hasEnnemy;
	}
	public Boolean getHasBonus() {
		return hasBonus;
	}
	public void setHasBonus(Boolean hasBonus) {
		this.hasBonus = hasBonus;
	}
	
	
	
	//render et update
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		arg2.drawImage(getImage(),0,0);
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if(deadly && contains)
			World2.getPlayer().setLives(0);
		
		if(hasBonus && contains){
			World2.setScore(World2.getScore()+77);
			World2.getGrid().getCell(x, y).setHasBonus(false);
			World2.getGrid().getCell(x,y).setImageType(NORMAL_TYPE);
			World2.cat.play();
		}
			
	}
	
}