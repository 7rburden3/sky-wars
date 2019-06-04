package grid;

import java.util.ArrayList;
import java.io.Serializable;

public class Row implements Serializable{
	ArrayList <Square> theSquares = new ArrayList <Square> ();
	int number;
	
	public Row(int num) {
		setNumber(num);
	}
	
	public Row() {}

	public void addSquare(Square theSq) {
		this.theSquares.add(theSq);
	}	
	
	public ArrayList<Square> getTheSquares() {
		return theSquares;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}	

}
