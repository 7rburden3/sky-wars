package grid;

import java.io.Serializable;
import java.util.ArrayList;

public class Square implements Serializable{
	ArrayList <EnemyShip> theShips = new ArrayList <EnemyShip> ();
	MasterShip theShip;
	int number;
	
	public Square(int num) {
		setNumber(num);
		
	}
	
	public Square() {}

	public ArrayList<EnemyShip> getTheShips() {
		return theShips;
	}
	
	public MasterShip getTheShip() {
		return theShip;
	}

	public void addEnemyShip(EnemyShip enemyShip) {
		this.theShips.add(enemyShip);
	}	
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void addMasterShip(MasterShip masterShip) {
		this.theShip = masterShip;
	}
	
	public void removeMasterShip() {
		this.theShip = null;
	}
	
	public void removeEnemyShips(ArrayList<EnemyShip> enemyShips) {
		this.theShips.removeAll(enemyShips);
		
	}

	public void removeEnemyShip(EnemyShip enemyShip) {
		this.theShips.remove(theShip);
		
	}
	

}
