package grid;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SaveGame {
	
	Grid grid;
	final private String FILE_NAME = "saveGame.data";
	
	public SaveGame() {
		this.grid = new Grid();
		String input = JOptionPane.showInputDialog("Would you like to load the previous game? \nPress 1 to reload \nPress any other number to start a new game");
		int choice = Integer.parseInt(input);

		if (choice == 1) {
			
			deserialize();

		} 
	}
	
	
	
	
	
	public void serialize() {
		// create output stream
		try {
			FileOutputStream fos = new FileOutputStream(FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(this.grid);			
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deserialize() {
		// create input stream
		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			this.grid = (Grid) ois.readObject();			
			
			ois.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		}

	}

}
