package game3.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	
	private boolean isDroping = false;
	private boolean isRealeased = false;
	private Image image;
	

	public Block(float x, float y, float width, float height,Image image) {
		super(x, y, width, height);
		this.image=image.getScaledCopy((int)width, (int)height);
	}
	
	public Block(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// Rendering
		g.setColor(Color.cyan);
		
		//g.rotate(Main.longueur/2, -100, -(float) (angle*180/Math.PI));
		g.drawRect(x, y, width, height);
		//g.rotate(getCenterY(), getCenterY(), (float) (angle*180/Math.PI));
		g.drawImage(image,x,y);
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		// Updating

		x += speedX*delta;
		y += speedY*delta;
		
		speedY+=accelY;
		
		System.out.println(this.y  +" / "+ this.accelY + " / " + this.speedY );
		if(isDroping){
			if( World3.getTower().intersects(this)){
				World3.getTower().isSuccess(this);
			}
		}
		
	}
	// Methods =================================================================================

	public void drop(float speedX,float speedY){
		successY = World3.getTower().getTopY();
		this.speedY = speedY;
		this.speedX = speedX;
		this.accelY= World3.GRAVITY;
		isDroping = true;
		isRealeased = true;

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
	
	public void setAccelY(float accelY) {
		this.accelY=accelY;
	}

	public Block cloneBlock() {
		Block block=new Block(x,y,width,height);
		block.setSpeedX(speedX);
		block.setSpeedY(speedY);
		return block;
	}

	public boolean isDroping() {
		// TODO Auto-generated method stub
		return isDroping;
	}

	public void setIsDroping(boolean b) {
		// TODO Auto-generated method stub
		this.isDroping = b;
	}

	public boolean isRealeased() {
		return isRealeased;
	}

	public void setRealeased(boolean isRealeased) {
		this.isRealeased = isRealeased;
	}
	
}
