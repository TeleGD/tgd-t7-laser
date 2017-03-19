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
			World3.getPendulum().notifyStackedBlock();
	}

	
	public boolean intersects(Shape shape){
		if(getTop().intersects(shape)){
			System.out.println("block top= "+getTop().getX()+ "-"+getTop().getY());
			System.out.println("block shape = "+shape.getX()+ "-"+shape.getY());

		}
		return getTop().intersects(shape);
	}
	
}
