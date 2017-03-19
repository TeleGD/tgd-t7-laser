package game3.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Copter extends Rectangle{
	
	private Image image0;
	private Image image1;
	
	private static float width = 120;
	private static float height = 60;
	
	private float goToX;
	private float goToY;
	private boolean isFlying;
	private int timer;
	private int nb = 0;
	private float alpha = 0.02f;
	public boolean toDestroy = false;
	
	public boolean isFlying() {
		return isFlying;
	}
	
	public static Sound sound;

	public boolean isToDestroy() {
		return toDestroy;
	}

	public Copter(){
		super(50, 50, width, height);
		if(sound==null){
			try {
				sound=new Sound("son/Copter.ogg");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		leave();
		
		timer = (int) ((Math.random() + 1) * 600);
		
		this.x = goToX;
		this.y = goToY;
		
		try {
			image0=new Image("Images/TowerBlocks/Copter/copter1.png").getScaledCopy((int)width, (int)height);
			image1=new Image("Images/TowerBlocks/Copter/copter2.png").getScaledCopy((int)width, (int)height);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
	public void start(){
		majGoTo();
		isFlying = true;
		sound.loop();
	}
	
	private void majGoTo() {
		goToX = (float) (Main.hauteur * Math.random());
		goToY = (float) (Main.longueur * Math.random());
	}

	public void stop(){
		isFlying = false;
		leave();
	}
	
	private void leave() {
		goToX = Math.random() < 0.5 ? -Main.longueur-250 : Main.longueur+250;
		goToY = (float) (Main.hauteur * Math.random());
	}

	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// Rendering
		nb ++;
		if(nb%10 <= 5){
			g.drawImage(image0,x,y);
		} else {
			g.drawImage(image1,x,y);
		}
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		// Updating
		x = lerp(x,goToX, alpha);
		y = lerp(y, goToY, alpha);
		if( (x - goToX) < 5 && (y - goToY) < 5 ){
			if(isFlying){
				majGoTo();
			}else{
				if(x>Main.longueur || x<0){
					Decor.getCopters().remove(this);
					sound.stop();
				}
				
			}
		}
		if(nb > timer){
			stop();
		}
	}
	
	private float lerp(float point1, float point2, float alpha)
	{
	    return point1 + alpha * (point2 - point1);
	}
	
	
	
}
