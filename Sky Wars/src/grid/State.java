package grid;

public abstract class State {

	public Grid grid;

	public State (Grid grid) {
		this.grid = grid;
	}

	public abstract void changeMode();

}
