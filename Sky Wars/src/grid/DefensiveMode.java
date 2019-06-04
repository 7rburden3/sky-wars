package grid;

public class DefensiveMode extends State {
	
	public DefensiveMode (Grid grid) {
		super(grid);
	}
	
	 @Override
	    public void changeMode() {
	        grid.changeState(new OffensiveMode(grid));	        
	        return;
	    }

}
