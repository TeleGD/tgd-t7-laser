package game3.world;


import org.newdawn.slick.Image;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Decor {

	private int height;
	private int compteur;
	private ArrayList<SkyElements> listSkyElements;
	private boolean endTown=false;
	
	public Decor()
	{
		this.height=0;
		this.compteur=0;
		this.listSkyElements=new ArrayList<SkyElements>();
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException 
	{
		this.height+=1;
		this.compteur+=1;
		if(this.height>=arg0.getHeight()){
			this.endTown=true;
		}
		Random r = new Random();
		if((compteur==1 || compteur%100==0))
		{
			
			this.listSkyElements=new ArrayList<SkyElements>();
			if(height<=128+arg0.getHeight()){
				
				int numberCloud= 2 + r.nextInt(8-2);
				for(int i =0;i<=numberCloud;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY;
					if(arg0.getHeight()/2+height<arg0.getHeight()){
						posY=0 + r.nextInt(arg0.getHeight()/10+height - 0);
					}
					else
					{
						posY=0 + r.nextInt(arg0.getHeight() - 0);
					}
					int width=5 + r.nextInt(200 - 5);
					int heigth=5 + r.nextInt(200 - 5);
					int numberImageCloud=1 + r.nextInt(5 - 1);
					this.listSkyElements.add(new Cloud(posX,posY,width,heigth,numberImageCloud));

				}
			}
			else
			{
				
				int numberStar= 30 + r.nextInt(50-30);
				for(int i =0;i<=numberStar;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY;
					if(arg0.getHeight()/2+height<arg0.getHeight()){
						posY=0 + r.nextInt(arg0.getHeight()/2+height - 0);
					}
					else
					{
						posY=0 + r.nextInt(arg0.getHeight() - 0);
					}
					this.listSkyElements.add(new Star(posX,posY));
				}
			}
		}
		
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		int red;
		int blue;
		int green;
		if(!this.endTown)
		{
			red=0;
			green=217;
			blue=198;
			
		}
		else{
			red= 0-height+arg0.getHeight();
			if(red<0)
			{
				red=0;
			}
			green=217-height+arg0.getHeight();
			if(green<0)
			{
				green=0;
			}
			blue=198-height+arg0.getHeight();
			if(blue<0)
			{
				blue=0;
			}
		}
		g.setBackground(new Color(red,green,blue));
		Image backGround= new Image("./Images/TowerBlocks/DecorBase.png");
		backGround = backGround.getScaledCopy(arg0.getWidth(),arg0.getHeight() );
		g.drawImage(backGround, 0,0+height, null);

		for(SkyElements se : listSkyElements)
		{
			if(se instanceof Star)
			{
				g.setColor(Color.yellow);
				Image star= new Image("./Images/TowerBlocks/SkyElements/star.png");
				star = star.getScaledCopy(se.getWidth(), se.getHeight());
				g.drawImage(star, se.getPosX(), se.getPosY(), null);
			}
			
			
			else if(se instanceof Cloud)
			{
				g.setColor(Color.white);
				Image star= new Image("./Images/TowerBlocks/SkyElements/cloud"+((Cloud)se).getNumberImageCloud()+".png");
				star = star.getScaledCopy(se.getWidth(), se.getHeight());
				g.drawImage(star, se.getPosX(), se.getPosY(), null);
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
