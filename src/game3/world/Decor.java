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
	private Image[] cloudImages=new Image[4];
	private Image imageStar;
	private static ArrayList<Copter> copters=new ArrayList<>();
	public Decor() throws SlickException
	{
		this.height=0;
		this.compteur=0;
		this.listSkyElements=new ArrayList<SkyElements>();
		this.background =new Image("./Images/TowerBlocks/DecorBase.png");

		for(int i=0;i<cloudImages.length;i++){
			cloudImages[i]=new Image("./Images/TowerBlocks/SkyElements/cloud"+(i+1)+".png");
		}
		imageStar=new Image("Images/TowerBlocks/SkyElements/star2.png");
		red=169;
		green=217;
		blue=199;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException 
	{
		this.compteur+=1;
		if(this.height>=arg0.getHeight()+500){
			this.endTown=true;
		}
		Random r = new Random();
		if(changeHeigth )
		{
			
			if(height<=1000+arg0.getHeight() && (compteur%350==0 || compteur==1)){
				
				int numberCloud= r.nextInt(2);
				for(int i =0;i<=numberCloud;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY=0;
					int width=100 + r.nextInt(400);
					int heigth=100 + r.nextInt(50);
					int numberImageCloud=r.nextInt(cloudImages.length);
					
					this.listSkyElements.add(new Cloud(posX,posY,width,heigth,numberImageCloud,cloudImages[i].getScaledCopy(width, heigth)));
				}
				changeHeigth=false;
			}
			else if(height>=1000+arg0.getHeight() && compteur%30==0)
			{
				
				int numberStar= Math.min((height-10)/1000,15);
				for(int i =0;i<=numberStar;i++){
					int posX=0 + r.nextInt(arg0.getWidth() - 0);
					int posY=0;
					int hasard=r.nextInt(15)+5;
					this.listSkyElements.add(new Star(posX,posY,imageStar.getScaledCopy(hasard,hasard)));
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
		
		if(compteur %180==0 && r.nextInt(5)==1){
			copters.add(new Copter());
			copters.get(copters.size()-1).start();
		}
		
		for(int i=0;i<copters.size();i++){
			copters.get(i).update(arg0, arg1,arg2);
		}
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
		
		for(int i=0;i<copters.size();i++){
			copters.get(i).render(arg0, arg1, g);
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.changeHeigth=true;
	}

	public static ArrayList<Copter> getCopters() {
		// TODO Auto-generated method stub
		return copters;
	}
	
	
}
