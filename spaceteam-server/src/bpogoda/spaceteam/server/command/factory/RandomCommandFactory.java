package bpogoda.spaceteam.server.command.factory;

import java.util.ArrayList;
import java.util.List;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.command.SliderCommand;
import bpogoda.spaceteam.server.command.ToggleCommand;

public class RandomCommandFactory {

	public List<Command> generate(int numberOfCommands, ControlPanel controlPanel) {

		List<Command> randomCommands = new ArrayList<>();

		// TODO: Generate commands based on panel set up

		// temp mock
		randomCommands.add(new SliderCommand());
		randomCommands.add(new ToggleCommand());

		return randomCommands;
	}
}
