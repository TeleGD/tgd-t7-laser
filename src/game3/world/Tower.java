package game3.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
		for (Block block : blocks) {
			block.render(arg0, arg1, arg2);
		}
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// Updating
		for (Block block : blocks) {
			block.update(arg0, arg1, arg2);
		}
	}
	
	public void addBlock(Block initialBlock) {
		blocks.add(initialBlock);		
	}

	public float getTopY(){
		return(blocks.get(blocks.size()).getY());
	}
	
	public void success(Block block){
		
	}

}
