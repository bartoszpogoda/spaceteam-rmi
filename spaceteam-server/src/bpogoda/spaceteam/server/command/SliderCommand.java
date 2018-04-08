package bpogoda.spaceteam.server.command;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;

public class SliderCommand extends Command {

	private int targetSliderValue;
	
	public SliderCommand(CrewType targetCrew, String sliderDeviceName, int targetSliderValue) {
		
		super(targetCrew, String.format("Adjust the %s to %d level", sliderDeviceName, targetSliderValue));
	
		this.targetSliderValue = targetSliderValue;
	}

	@Override
	public boolean wasExecuted(CrewType crew, ControlPanel controlPanel) {
		if(isTargetCrew(crew) == false) {
			return false;
		}
		
		// TODO properly executed check logic
		
		return false;
	}

}
