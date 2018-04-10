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
		} else if(crewType == CrewType.SteeringCrew) {
			customizeForSteeringCrew(controlPanel);
		} else if(crewType == CrewType.ArtilleryCrew) {
			customizeForArtilleryCrew(controlPanel);
		}
		
		return controlPanel;
		
	}

	private void customizeForEngineCrew(ControlPanel controlPanel) {
		controlPanel.setSliderDeviceMax(100);
		controlPanel.setSliderDeviceMin(10);
		controlPanel.setToggleDeviceBtnLabel("Super boost");
		controlPanel.setToggleDeviceName("Extra features");
		controlPanel.setTextDeviceName("");
		controlPanel.setSliderDeviceName("Engine cooling level");
		controlPanel.setRoomName("Engine room");
	}


	private void customizeForSteeringCrew(ControlPanel controlPanel) {
		controlPanel.setTextDeviceName("Direction");
		controlPanel.setPossibleTextCommands(new String[] {"E", "W", "N", "S"});
		controlPanel.setSliderDeviceName("");
		controlPanel.setRoomName("Steering room");
	}

	private void customizeForArtilleryCrew(ControlPanel controlPanel) {
		controlPanel.setSliderDeviceMax(150);
		controlPanel.setSliderDeviceMin(100);
		controlPanel.setToggleDeviceBtnLabel("Constant fire");
		controlPanel.setToggleDeviceName("Minigun A21/X5");
		controlPanel.setTextDeviceName("");
		controlPanel.setSliderDeviceName("Shield power");
		controlPanel.setRoomName("Artillery room");
	}
	
}
