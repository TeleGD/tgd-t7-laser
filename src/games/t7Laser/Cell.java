package games.t7Laser;

import org.newdawn.slick.Image;

import app.AppLoader;

public class Cell {

	public static final int NORMAL_TYPE = 0 ;
	public static final int MINE_TYPE = 1 ;
	public static final int BONUS_TYPE = 2 ;

	private Image NORMAL;
	private Image MINE;
	private Image BONUS;

	//Variables
	private Boolean contains;
	private Boolean deadly;
	private Boolean hasBonus;
	private boolean hasEnnemy;

	private int imageType;

	//Constructeur
	public Cell() {
		this.contains = false;
		this.deadly = false;
		this.hasBonus=false;
		this.hasEnnemy = false;

		if(NORMAL==null){
			NORMAL= AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Cell.png");
			MINE=AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Mine.png");
			BONUS=AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Bonus.png");
		}
	}

	//Getters et Setters
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

}
