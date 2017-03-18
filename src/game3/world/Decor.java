package game3.world;


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
		Random r = new Random();
		if((compteur==1 || compteur%100==0))
		{
			
			this.listSkyElements=new ArrayList<SkyElements>();
			if(height<=128){
				
				int numberCloud= 2 + r.nextInt(8-2);
				for(int i =0;i<=numberCloud;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY=0 + r.nextInt(arg0.getHeight()/2+height - 0);
					int width=5 + r.nextInt(200 - 5);
					int heigth=5 + r.nextInt(200 - 5);
					this.listSkyElements.add(new Cloud(posX,posY,width,heigth));

				}
			}
			else
			{
				
				int numberStar= 30 + r.nextInt(50-30);
				for(int i =0;i<=numberStar;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY=0 + r.nextInt(arg0.getHeight()/2+height - 0);
					this.listSkyElements.add(new Star(posX,posY));
				}
			}
		}
		
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if(height<255)
		{
			g.setBackground(new Color(0,255-height,255-height));
		}
		
		g.setColor(Color.green);
		g.fillRect(0, arg0.getHeight()-20+this.height, arg0.getWidth(),20 );

		for(SkyElements se : listSkyElements)
		{
			if(se instanceof Star)
			{
				g.setColor(Color.yellow);
			}
			else if(se instanceof Cloud)
			{
				g.setColor(Color.white);
			}
			g.fillRect(se.getPosX(), se.getPosY(), se.getWidth(), se.getHeight());
		}
	}
}
