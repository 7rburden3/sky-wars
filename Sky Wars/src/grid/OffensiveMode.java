package grid;

public class OffensiveMode extends State{
	
	public OffensiveMode (Grid grid) {
		super(grid);
	}
	
	 @Override
	    public void changeMode() {
	        grid.changeState(new DefensiveMode(grid));	        
	        return;
	    }

}
