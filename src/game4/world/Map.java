package game4.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Map {
	private Cell[][] cells;
	private int taille=10;

	public Map(){
		cells = new Cell[taille][taille];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				cells[i][j]=new Cell(i,j);
			}
		}
		
		roadBis(taille-1,taille-3);
	}

	
	private void roadBis(int i,int j){
		int a=i;
		int b=j;
		while (a>0){
			cells[a][b].setRoad();
			if(b>0 && b<taille-1){
				if(!cells[a][b-1].isRoad() && !cells[a][b+1].isRoad()){
					double c=Math.random();
					if(a>0.2 && c<=0.4){
						b+=1;
					}else if(c<0.2){
						b-=1;
					}else{
						a-=1;
					}
				}else if(!cells[a][b-1].isRoad()){
					double c=Math.random();
					if(c>0.5){
						b-=1;
					}else{
						a-=1;
					}
				}else{
					double c=Math.random();
					if(c>0.5){
						b+=1;
					}else{
						a-=1;
					}
				}
			}else{
				a-=1;
			}
				/*else if (b<taille-1){
			}
				if(!cells[a][b+1].isRoad()){
					double c=Math.random();
					if(c>0.9){
						b+=1;
					}else{
						a-=1;
					}
				}else {a-=1;}
			}else {
				if(!cells[a][b-1].isRoad()){
					double c=Math.random();
					if(c>0.9){
						b-=1;
					}else{a-=1;}
				}
			}*/
		}
	}

	private void road(int i,int j){
		cells[i][j].setRoad();
		if(i==0){
			return;
		}
		if(j>0 && j<taille-1){
			if(!cells[i][j+1].isRoad() && !cells[i][j-1].isRoad()){
				double a=Math.random();
				if(a>0.15 && a<=0.3){
					road(i,j+1);
				}else if(a<0.15){
					road(i,j-1);
				}else{
					road(i-1,j);
				}
			}
			if(!cells[i][j-1].isRoad()){
				double a=Math.random();
				if (a>0.85){
					road(i,j-1);
				}else{
					road(i-1,j);
				}
			}
			if(!cells[i][j+1].isRoad() && cells[i][j-1].isRoad()){
				double a=Math.random();
				if (a>0.85){
					road(i,j+1);
				}else{
					road(i-1,j);
				}
			}
		}else if (j==0){
			if(!cells[i][j+1].isRoad()){
				double a=Math.random();
				if(a>0.85){
					road(i,j+1);
				}else{
					road(i-1,j);
				}
			}
		}else if (j==15){
			if(!cells[i][j-1].isRoad()){
				double a=Math.random();
				if(a>0.85){
					road(i,j-1);
				}else{
					road(i-1,j);
				}
			}
		}
	}

	
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		for (int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				cells[i][j].render(arg0, arg1, arg2);
			}
		}
	}
}
