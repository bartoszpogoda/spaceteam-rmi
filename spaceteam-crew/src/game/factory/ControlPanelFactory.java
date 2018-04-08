package game.factory;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;

public class ControlPanelFactory {
	
	private ControlPanelFactory() {};
	
	private static ControlPanelFactory instance = null;
	
	public static ControlPanelFactory getInstance() {
		return instance == null ? instance = new ControlPanelFactory() : instance;
	}
	
	ControlPanel create(CrewType crewType) {
		
		ControlPanel controlPanel = new ControlPanel();
		
		if(crewType == CrewType.EngineCrew) {
			customizeForEngineCrew(controlPanel);
		}
		
		return controlPanel;
		
	}

	private void customizeForEngineCrew(ControlPanel controlPanel) {
		controlPanel.setSliderDeviceMax(11);
		controlPanel.setSliderDeviceMin(5);
		controlPanel.setToggleDeviceBtnLabel("Super boost");
		controlPanel.setToggleDeviceName("Extra features");
		controlPanel.setTextDeviceName("");
		controlPanel.setSliderDeviceName("Engine cooling level");
	}
	
}
