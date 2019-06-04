package grid;

import java.io.Serializable;

public abstract class EnemyShip implements Serializable{
	
	String enemyShip;
	
	public String getEnemyShip() {
		return this.enemyShip;		
	}//end get
	
	public void setEnemyShip(String enemyShip) {
		this.enemyShip=enemyShip;
	}//end set

}//end abstract class
