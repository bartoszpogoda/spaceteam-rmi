package bpogoda.spaceteam.server.command;

import bpogoda.spaceteam.server.CrewType;

public abstract class Command {
	
	private CrewType targetCrew;

	/**
	 * @param targetCrew
	 */
	public Command(CrewType targetCrew) {
		super();
		this.targetCrew = targetCrew;
	}
	
}
