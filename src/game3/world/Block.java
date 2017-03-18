package game3.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Block extends Rectangle {

	private float speedX;
	private float speedY;
	private float angle;
	private float successY;
	private float accelY;
	
	public Block(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// Rendering
		g.setColor(Color.cyan);
		
		//g.rotate(Main.longueur/2, -100, -(float) (angle*180/Math.PI));
		g.drawRect(x, y, width, height);
		//g.rotate(getCenterY(), getCenterY(), (float) (angle*180/Math.PI));

	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		// Updating
		x += speedX*delta;
		y += speedY*delta;
		
		speedY+=accelY;
	}

	// Methods =================================================================================

	public void drop(float speedX,float speedY){
		//successY = World3.getTower().gettopY();
		this.speedY = speedY;
		this.speedX = speedX;
		this.accelY= World3.GRAVITY;
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

	public void setAngle(float angle) {
		this.angle=angle;
	}

	public Block cloneBlock() {
		Block block=new Block(x,y,width,height);
		block.setSpeedX(speedX);
		block.setSpeedY(speedY);
		return block;
	}

	
	
}
