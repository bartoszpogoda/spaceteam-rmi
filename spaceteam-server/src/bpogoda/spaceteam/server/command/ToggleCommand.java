package bpogoda.spaceteam.server.command;

import java.io.Serializable;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;

public class ToggleCommand extends Command{

	private boolean targetState;
	
	public ToggleCommand(CrewType targetCrew, String toggleButtonName, boolean targetState) {

		super(targetCrew, String.format("Turn the %s %s", toggleButtonName, targetState ? "on" : "off"));
		
		this.targetState = targetState;
	}

	@Override
	public boolean wasExecuted(CrewType crew, ControlPanel controlPanel) {
		System.out.println("Elo");
		if(!isTargetCrew(crew)) {
			return false;
		}
		
		return controlPanel.getToggleDeviceValue() == targetState;
	}

}
