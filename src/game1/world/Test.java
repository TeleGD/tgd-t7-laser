package game1.world;
import org.newdawn.slick.SlickException;

public class Test {

	public static void main(String[] args) 
	{
		int random = (int)Math.random()*4;
		while (random == 0){
			System.out.println(random);
			random = (int)Math.floor((Math.random()*4));
		}
		System.out.println("fin");
	}

}
