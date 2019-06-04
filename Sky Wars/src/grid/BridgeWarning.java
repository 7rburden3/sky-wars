package grid;

import java.util.ArrayList;

public class BridgeWarning implements Observable {
	
	private String warnings;
	private ArrayList<CIC> theCIC = new ArrayList<CIC>();

	
	public void alertCIC(CIC c) {
		this.theCIC.add(c);
		
	}

	
	public void notifyCIC() {
		for (CIC tempCIC : this.theCIC) {
			tempCIC.update(warnings);
		}
		
		
	}


	public String getWarnings() {
		return warnings;
	}


	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

}
