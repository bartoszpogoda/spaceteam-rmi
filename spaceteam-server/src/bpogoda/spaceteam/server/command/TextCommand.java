package bpogoda.spaceteam.server.command;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;

public class TextCommand extends Command {

	private String targetText;

	public TextCommand(CrewType targetCrew, String textDeviceName, String targetText) {
		super(targetCrew, String.format("Please set the %s value in %s", targetText, textDeviceName));

		this.targetText = targetText;
	}

	@Override
	public boolean wasExecuted(CrewType crew, ControlPanel controlPanel) {
		if (isTargetCrew(crew) == false) {
			return false;
		}

		// TODO properly executed check logic

		return false;
	}

}
