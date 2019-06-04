package grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Grid implements Serializable{


	ArrayList <Row> grid;	
	int mastershipRow;
	int mastershipColumn;
	final int ROW_COUNT = 4;
	final int COLUMN_COUNT = 4;
	Random random;
	ArrayList<EnemyShip> activeEnemyShips = new ArrayList<EnemyShip>();
	boolean gameOver;
	private State state;
	private boolean mode = false;
	final int DEFENCE_KILL = 2;
	final int OFFENCE_KILL = 3;


	public Grid() {
		
		this.grid =  new ArrayList<Row> ();		

		//call observer pattern
		BridgeWarning bw = new BridgeWarning();
		CIC c1 = new CIC ("Admiral");

		bw.alertCIC(c1);
		bw.setWarnings("Cylons reported in this sector");
		bw.notifyCIC();

		this.state = new DefensiveMode(this);
		setMode(true);

		for (int row = 0; row < ROW_COUNT; row++) {
			Row newRow = new Row(row);
			for (int column = 0; column < COLUMN_COUNT; column++) {
				Square square = new Square(column);
				newRow.addSquare(square);
			}
			grid.add(newRow);
		}
		MasterShip masterShip = new MasterShip();
		this.mastershipColumn = this.randomize(this.COLUMN_COUNT);
		this.mastershipRow = this.randomize(this.ROW_COUNT);
		this.grid.get(this.mastershipRow).theSquares.get(this.mastershipColumn).addMasterShip(masterShip);

		this.gameOver = false;

	}//end Grid
	
	//code required for State Pattern
	public void changeState(State state) {
		this.state = state;
	}	

	public State getState() {
		return state;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}

	public boolean isMode() {
		return mode;
	}

	public void addEnemyShip(EnemyShip enemyShip, int row, int col) {

		this.grid.get(col).theSquares.get(row).theShips.add(enemyShip);
		
	}

	//find square MasterShip is on

	public boolean isMasterShipOn(int row, int col) {
		for(Row tempRow : this.grid) {
			//find the row numbered row
			if (tempRow.getNumber() == row) {

				for(Square tempSquare : tempRow.getTheSquares()) {
					//find the square with number col
					if (tempSquare.getNumber() == col)
						//we have the correct square
						if(tempSquare.getTheShip() == null) {
							return false;
						} else {
							return true;
						}
				}
			}
		}
		return false;
	}

	//find square enemy ship(s) are on
	public boolean isEnemyShipOn(int row, int col) {
		for (Row tempRow : this.grid ) {
			if (tempRow.getNumber() == row) {

				for(Square tempSquare: tempRow.getTheSquares()) {
					if (tempSquare.getNumber() == col)
						if(tempSquare.getTheShips() == null) {
							return false;
						} else {
							return true;
						}
				}
			}
		}
		return false;
	}

	
	//33% chance to add enemy ship at 0,0
	public void createEnemyShip() {
		EnemyShip enemy;
		int random;
		Random randomGenerator = new Random();

		random = randomGenerator.nextInt(3);
		//random could be 0,1,2 so 33% chance.
		if(random ==0) {
			random = randomGenerator.nextInt(3);
			if(random==1) {
				enemy = new BattleStar();				
			} else if(random==2) {
				enemy = new BattleCruiser();				
			} else {
				enemy = new BattleShooter();				
			}
			//passes this to the other function
			this.addEnemyShip(enemy, 0, 0);
			this.activeEnemyShips.add(enemy);			
		}
		
	}//end createBaddie

	public ArrayList<Row> getGrid() {
		return this.grid;
	}

	public void setGrid(ArrayList<Row> grid) {
		this.grid = grid;
	}

	//Creates a random number for mastership column and row.
	public int randomize(int maxNum) {
		Random randomGenerator = new Random();		
		int random = randomGenerator.nextInt(maxNum);
		return random;
	}

	public ArrayList<Square> getNeighboursFor(int row, int col) {
		ArrayList<Square> theNeighbouringSquares = new ArrayList<Square>();

		//find square currently occupied
		for(Row tempRow : this.grid) {
			if(tempRow.getNumber() == row) {
				for(Square tempSquare : tempRow.getTheSquares()) {
					if(tempSquare.getNumber() == col) {
						//adds square to left if does not go out of bounds
						if(row !=0) {
							theNeighbouringSquares.add(this.grid.get(row-1).theSquares.get(col));
						} 
						//adds square to right if does not go out of bounds
						if (row != ROW_COUNT-1) {
							theNeighbouringSquares.add(this.grid.get(row+1).theSquares.get(col));
						}
						//adds square above if does not go out of bounds
						if(col != 0) {
							theNeighbouringSquares.add(this.grid.get(row).theSquares.get(col-1));
						}
						//adds square below if does not go out of bounds
						if (col != COLUMN_COUNT-1) {
							theNeighbouringSquares.add(this.grid.get(row).theSquares.get(col+1));
						}
						//adds square top left
						if(row !=0 && col !=0) {
							theNeighbouringSquares.add(this.grid.get(row-1).theSquares.get(col-1));
						}
						//adds square top right
						if(row !=ROW_COUNT-1 && col !=0) {
							theNeighbouringSquares.add(this.grid.get(row+1).theSquares.get(col-1));
						}
						//adds square bottom left
						if (row !=0 && col !=COLUMN_COUNT-1) {
							theNeighbouringSquares.add(this.grid.get(row-1).theSquares.get(col+1));
						}
						//adds square bottom right
						if (row !=ROW_COUNT-1 && col !=COLUMN_COUNT-1) {
							theNeighbouringSquares.add(this.grid.get(row+1).theSquares.get(col+1));
						}
					}
				}
			}
		}
		return theNeighbouringSquares;
	}

	//move MasterShip- takes parameters of current position of mastership
	public void moveMasterShip(int row, int col) {
	ArrayList<Square>nextSquare = getNeighboursFor(row, col);
	random = new Random();

		MasterShip masterShip = this.grid.get(row).theSquares.get(col).getTheShip();		
		this.grid.get(row).theSquares.get(col).removeMasterShip();		
		Square randomSquare = nextSquare.get(random.nextInt(nextSquare.size()-1));
		randomSquare.addMasterShip(masterShip);
		this.mastershipColumn =  randomSquare.number;
		int newRowNum = 0;
		for(Row tempRow: this.grid) {
			if(tempRow.theSquares.contains(randomSquare)) {
				newRowNum = tempRow.number;
				break;
			}
		}
		this.mastershipRow = newRowNum;
	}

	public void moveAllShips() {
		if(this.activeEnemyShips.size() >0) {
			for(EnemyShip enemyShip: this.activeEnemyShips) {
				moveEnemyShip(enemyShip);
			}	
		}
	}

	public void moveEnemyShip(EnemyShip enemyShip) {

		int tempEnemyColumn=0;
		int tempEnemyRow=0;

		for (Row tempRow : this.grid) {
			for (Square square : tempRow.theSquares) {
				if(square.theShips.contains(enemyShip)) {

					tempEnemyColumn = square.number;
					tempEnemyRow = tempRow.number;
					break;
				}
			}
		}
		moveEnemyShip(enemyShip, tempEnemyRow, tempEnemyColumn);		
	}

	private void moveEnemyShip(EnemyShip enemyShip, int row, int col) {

		ArrayList<Square>nextSquare = getNeighboursFor(row, col);
		random = new Random();

		this.grid.get(row).theSquares.get(col).theShips.remove(enemyShip);		
		Square randomSquare = nextSquare.get(random.nextInt(nextSquare.size()-1));
		randomSquare.addEnemyShip(enemyShip);

	}
	
	public void checkMasterShip() {

		//check whether any kills occur
		//mastership starts in defensive mode

		Square tempSquare = this.grid.get(this.mastershipRow).theSquares.get(this.mastershipColumn);
		ArrayList<EnemyShip> enemyShipsOnSquare = tempSquare.theShips;

		if(mode == true) {
			if (enemyShipsOnSquare.size() >= DEFENCE_KILL) {
				tempSquare.removeMasterShip();				
				JOptionPane.showMessageDialog(null, "GAME OVER!!!" + "\nYou were killed by two enemy ships!");
				this.gameOver = true;
				System.exit(0);
			} else if (enemyShipsOnSquare.size() < DEFENCE_KILL) {
				for(EnemyShip enemyShip: enemyShipsOnSquare) {
					this.activeEnemyShips.remove(enemyShip);
					tempSquare.removeEnemyShip(enemyShip);					
					this.gameOver = false;
				}
			}

			} else if(mode == false) {				
				if (enemyShipsOnSquare.size()>= OFFENCE_KILL) {
					tempSquare.removeMasterShip();					
					JOptionPane.showMessageDialog(null, "GAME OVER!!!" + "\nYou were killed by three enemy ships!");
					this.gameOver = true;
					System.exit(0);
				} else if (enemyShipsOnSquare.size() < OFFENCE_KILL) {
					for(EnemyShip enemyShip: enemyShipsOnSquare) {
						this.activeEnemyShips.remove(enemyShip);
						tempSquare.removeEnemyShip(enemyShip);							
						this.gameOver = false;
					}
				}			
			}
		
	}	



	public void runGame() {
			
			moveMasterShip(this.mastershipRow, this.mastershipColumn);				
			moveAllShips();
			createEnemyShip();			
			checkMasterShip();
					
	}

	/*

	public int getMastershipRow() {
		return mastershipRow;
	}

	public void setMastershipRow(int mastershipRow) {
		this.mastershipRow = mastershipRow;
	}

	public int getMastershipColumn() {
		return mastershipColumn;
	}

	public void setMastershipColumn(int mastershipColumn) {
		this.mastershipColumn = mastershipColumn;
	}*/


}
