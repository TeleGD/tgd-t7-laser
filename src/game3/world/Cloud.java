package game3.world;

public class Cloud extends SkyElements{

	private int numberImageCloud;
	
	public Cloud(int posX, int posY, int width, int height,int numberImageCloud) {
		super(posX,posY,width,height);
		this.numberImageCloud=numberImageCloud;
	}

	public int getNumberImageCloud() {
		return numberImageCloud;
	}

	public void setNumberImageCloud(int numberImageCloud) {
		this.numberImageCloud = numberImageCloud;
	}
	
}
