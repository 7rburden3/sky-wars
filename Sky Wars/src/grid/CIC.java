package grid;

import javax.swing.JOptionPane;

public class CIC implements Alert, Warning {
	
	private String name;
	private String updates;

	public CIC(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}

	@Override
	public void update(String theWarning) {
		this.updates = theWarning;
		alert();

	}

	@Override
	public void alert() {
		String alerts = this.name + ", red alert!\n";
		alerts = alerts + this.updates;
		
		JOptionPane.showMessageDialog(null, alerts);

	}

}
