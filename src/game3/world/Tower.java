package game3.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Tower extends Rectangle{

	ArrayList<Block> blocks;
	private boolean needDefile;
	private int cpt;
	
	public Tower(float x, float y, Block initialBlock) {
		super(x, y, 0, 0);
		blocks = new ArrayList<Block>();
		addBlock(initialBlock);
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// Rendering
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).render(arg0, arg1, arg2);
		}
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// Updating
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).update(arg0, arg1, arg2);
			if (needDefile==true){
				blocks.get(i).setY(blocks.get(i).getY()+1);
			}
		}
		if(needDefile){
			cpt+=1;
			World3.getDecor().setHeight(World3.getDecor().getHeight()+1);
		}
		if(cpt==blocks.get(blocks.size()-1).getHeight()){
			needDefile=false;
		}
		
	}
	
	public void addBlock(Block initialBlock) {
		blocks.add(initialBlock);		
	}

	public float getTopY(){
		return(blocks.get(blocks.size()-1).getY());
	}
	public Block getTop(){
		return(blocks.get(blocks.size()-1));
	}
	
	public void isSuccess(Block block){
			block.setSpeedX(0);
			block.setSpeedY(0);
			block.setAccelY(0);
			blocks.add(block);
			block.setY(getTopY()-5);
			block.setIsDroping(false);
			World3.notifyStackedBlock();
			World3.getPendulum().notifyStackedBlock();
			cpt=0;
			this.needDefile =true;
	}

	
	public boolean intersects(Shape shape){
		System.out.println(getTop().intersects(shape));
		return getTop().intersects(shape);
	}
	
}