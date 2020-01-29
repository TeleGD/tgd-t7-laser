package games.t7Laser;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Grid {

	private World world;
	private int rows;
	private int columns;
	private Cell grid[][];

	private int maxRows; //limite horizontale
	private int maxCols;

	private List<Laser> laserList;
	private List<Ennemy> ennemyList;
	private int laserTimer;

	private int waveTimer;
	private int waveNumber;

	public Grid(World world, int r, int c) {
		this.world = world;
		maxRows = 20;
		maxCols = 20;
		rows = r;
		columns = c;
		grid = new Cell[maxRows][maxRows];
		for(int i = 0; i<maxRows; i++)//init row
			for(int j=0;j<maxCols;j++) //init cologne
				grid[i][j] = new Cell();

		laserList = new LinkedList<Laser>();
		laserTimer = 1;
		waveTimer = 200;
		waveNumber = 0;

		ennemyList = new LinkedList<Ennemy>();
	}

	public Cell getCell(int x, int y) {
		return grid[x][y];
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		for(int i = 0; i<this.rows; i++)//init row
			for(int j=0;j<this.columns;j++) //init cologne
				grid[i][j].getImage().draw(280+360-this.getColumns()*100*world.getRenderScale()/2+i*100*world.getRenderScale(),0+j*100*world.getRenderScale()+360-this.getColumns()*100*world.getRenderScale()/2,100*world.getRenderScale(),100*world.getRenderScale());

		for(Laser l : laserList)
			l.render(arg0, arg1, arg2);

		for(Ennemy e : ennemyList)
			e.render(arg0, arg1, arg2);
	}

	public int getWaveNumber() {
		return waveNumber;
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		if(waveNumber % 5 == 2 && waveTimer == 1)
			addEnnemy();

		laserTimer--;
		if(laserTimer <= 0){
			addLaser();
			laserTimer = Math.max(50-waveNumber*5, 0)+20;
		}
		try{
			for(Laser l : laserList)
				l.update(arg0, arg1, arg2);

			for(Ennemy e : ennemyList)
				e.update(arg0, arg1, arg2);
		}
		catch(Exception e){
			//System.out.println(e.getMessage());
		}

		for(int i = 0; i<this.rows; i++) { //init row
			for(int j=0;j<this.columns;j++) { //init cologne

					if(grid[i][j].getDeadly() && grid[i][j].getContains())
						world.getPlayer().setLives(0);

					if(grid[i][j].getHasBonus() && grid[i][j].getContains()){
						world.setScore(world.getScore()+77);
						grid[i][j].setHasBonus(false);
						grid[i][j].setImageType(Cell.NORMAL_TYPE);
						world.getCat().playAsSoundEffect(1, .3f, false);
					}
			}
		}

		waveTimer--;
		if(waveTimer == 0){
			if(rows+1 < maxRows)
				rows++;
			if(columns+1 < maxCols)
				columns++;
			waveNumber++;
			if(rows==maxRows){
				for(int i=0; i<5; i++){
					addMine();
				}
			}
			else{
				addMine();
			}
			waveTimer = 100;
			Random b1 = new Random();
			Random b2 = new Random();
			int rowB = b1.nextInt(this.rows);
			int columnB = b2.nextInt(this.columns);
			if(grid[rowB][columnB].getImageType()!=Cell.MINE_TYPE)
					addBonus(rowB,columnB);

			if(rows > 7)
				world.setRenderScale((float)720.0/(100*rows));
		}
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	//x y new position
	public boolean MovePlayer(int x, int y, Player p) {
		if(x < rows && y < columns && x >= 0 && y >= 0){
			//set new cell true
			grid[x][y].setContains(true);
			//set old cell false
			grid[p.getX()][p.getY()].setContains(false);

			return true;
		}
		return  false;
	}

	public boolean MoveEnnemy(int x, int y, Ennemy p) {
		if(x < rows && y < columns && x >= 0 && y >= 0 && !getCell(x, y).isHasEnnemy()){
			//set new cell true
			grid[x][y].setDeadly(true);
			grid[x][y].setHasEnnemy(true);
			//set old cell false
			grid[p.getX()][p.getY()].setDeadly(false);
			grid[p.getX()][p.getY()].setHasEnnemy(false);

			return true;
		}
		return  false;
	}

	private void addEnnemy() {
		Random r = new Random();
		int x = 0;
		int y = 0;
		do{
			x = r.nextInt(rows);
			y = r.nextInt(columns);
		}
		while(getCell(x, y).getDeadly() || getCell(x, y).getContains());

		ennemyList.add(new Ennemy(this.world,x,y));
	}

	private void addLaser() {
		Random r = new Random();
		boolean axe = r.nextBoolean();
		if(axe) //horizontal
			laserList.add(new Laser(this.world,0,r.nextInt(columns)));
		else //vertical

			laserList.add(new Laser(this.world,1,r.nextInt(rows)));
	}

	public void removeLaser(Laser l) {
		laserList.remove(l);
	}

	private void addMine() {
		Random r1 = new Random();
		Random r2 = new Random();
		int row = r1.nextInt(this.rows);
		int column = r2.nextInt(this.columns);
		if(!getCell(row, column).getContains()){
			this.grid[row][column].setDeadly(true);
			this.grid[row][column].setImageType(Cell.MINE_TYPE);
		}
	}

	private void addBonus(int row, int column) {
		this.grid[row][column].setDeadly(false);
		this.grid[row][column].setHasBonus(true);
		this.grid[row][column].setImageType(Cell.BONUS_TYPE);
	}

}
