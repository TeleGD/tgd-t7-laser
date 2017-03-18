package game3.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Block extends Rectangle {

	float speedX;
	float speedY;
	
	public Block(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		arg2.drawRect(x, y, width, height);

	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		x++;
		y++;
	}

	// Getters and Setters =====================================================================
	
	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
}
