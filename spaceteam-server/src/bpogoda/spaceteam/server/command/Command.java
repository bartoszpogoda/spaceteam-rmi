package bpogoda.spaceteam.server.command;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;

public abstract class Command {
	
	private CrewType targetCrew;
	
	private String commandMessage;

	/**
	 * @param targetCrew
	 */
	public Command(CrewType targetCrew, String commandMessage) {
		super();
		this.targetCrew = targetCrew;
		this.commandMessage = commandMessage;
	}
	
	public abstract boolean wasExecuted(CrewType crew, ControlPanel controlPanel);
	
	public String getCommandMessage() {
		return commandMessage;
	}
	
	protected final boolean isTargetCrew(CrewType crew) {
		return this.targetCrew == crew;
	}
	
}
