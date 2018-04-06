package bpogoda.spaceteam.server.command.factory;

import java.util.ArrayList;
import java.util.List;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.command.SliderCommand;
import bpogoda.spaceteam.server.command.ToggleCommand;

public class RandomCommandFactory {

	public List<Command> generate(int numberOfCommands, ControlPanel controlPanel, CrewType crewType) {

		List<Command> randomCommands = new ArrayList<>();

		// TODO: Generate commands based on panel set up
		
		// Generate toggle commands
		String togglePanelTitle = controlPanel.getTogglePanelTitle();		
		
		
		// temp mock
		randomCommands.add(new SliderCommand(crewType));
		randomCommands.add(new ToggleCommand(crewType));

		return randomCommands;
	}
}
