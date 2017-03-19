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
	private Image background;
	private boolean changeHeigth=true;
	private int red;
	private int blue;
	private int green;
	
	public Decor() throws SlickException
	{
		this.height=0;
		this.compteur=0;
		this.listSkyElements=new ArrayList<SkyElements>();
		this.background =new Image("./Images/TowerBlocks/DecorBase.png");
		red=169;
		green=217;
		blue=199;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException 
	{
		this.compteur+=1;
		if(this.height>=arg0.getHeight()+2000){
			this.endTown=true;
		}
		Random r = new Random();
		if(changeHeigth )
		{
			
			if(height<=2000+arg0.getHeight() && (compteur%150==0 || compteur==1)){
				
				int numberCloud= 2 + r.nextInt(4-2);
				for(int i =0;i<=numberCloud;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY=0;
					int width=50 + r.nextInt(200 - 50);
					int heigth=50 + r.nextInt(200 - 50);
					int numberImageCloud=1 + r.nextInt(5 - 1);
					Image cloud= new Image("./Images/TowerBlocks/SkyElements/cloud"+numberImageCloud+".png");
					cloud = cloud.getScaledCopy(width, heigth);
					this.listSkyElements.add(new Cloud(posX,posY,width,heigth,numberImageCloud,cloud));
				}
				changeHeigth=false;
			}
			else if(height>=2000+arg0.getHeight() && compteur%30==0)
			{
				
				int numberStar= 5 + r.nextInt(15-5);
				for(int i =0;i<=numberStar;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY=0;
					Image star= new Image("./Images/TowerBlocks/SkyElements/star.png");
					star = star.getScaledCopy(20, 20);
					this.listSkyElements.add(new Star(posX,posY,star));
				}
				changeHeigth=false;
			}
			
		}
		ArrayList<SkyElements> listSkyElementsToRemove=new ArrayList<SkyElements>();
		if(changeHeigth){
			for(SkyElements se : listSkyElements)
			{
				se.setPosY((se.getPosY()+1));
				if(se.getPosY()>arg0.getHeight())
				{
					listSkyElementsToRemove.add(se);
				}
			}
		}
		listSkyElements.removeAll(listSkyElementsToRemove);
	}
		
	

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {

		if(this.endTown)
		{
			red+=-1;
			if(red<0)
			{
				red=0;
			}
			green+=-1;
			if(green<0)
			{
				green=0;
			}
			blue+=-1;
			if(blue<0)
			{
				blue=0;
			}
		}
		g.setBackground(new Color(red,green,blue));
		this.background = this.background.getScaledCopy(arg0.getWidth(),arg0.getHeight() );
		g.setColor(Color.white);
		g.drawImage(this.background, 0,0+height, null);
		
		for(SkyElements se : listSkyElements)
		{
			if(se instanceof Cloud){
				g.setColor(Color.white);
			}
			else if(se instanceof Star)
			{
				g.setColor(Color.yellow);
			}
			g.drawImage(se.getImage(), se.getPosX(), se.getPosY(), null);
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.changeHeigth=true;
	}
	
	
}
