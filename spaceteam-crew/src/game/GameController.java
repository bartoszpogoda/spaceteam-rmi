package game;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.server.CrewGameServer;
import bpogoda.spaceteam.server.GameServerManager;
import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.command.factory.RandomCommandFactory;
import team.CrewWindow;
import team.engine.EngineCrewWindow;

public class GameController {

	private CrewType crewType;

	private CrewWindow crewWindow;
	
	private ControlPanel controlPanel;

	public GameController(CrewType crewType, CrewWindow crewWindow) {
		this.crewType = crewType;
		this.crewWindow = crewWindow;
		
		this.crewWindow.setGameController(this);
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
		
		crewWindow.setControlPanel(controlPanel);
	}
	
	public void onCommandExecutedButtonPressed() {
		
		System.out.println("Command executed button was pressed");
		
	}

	public void connectAndStart() {
		if(controlPanel != null && crewWindow != null) {
			try {
				Registry registry = LocateRegistry.getRegistry(GameServerManager.REGISTRY_PORT);
				CrewGameServer stub = (CrewGameServer) registry.lookup(GameServerManager.REGISTRY_STUB_NAME);
				
				List<Command> generatedCommands = RandomCommandFactory.getInstance().generate(controlPanel, crewType);
				
				if(stub.connectCrew(crewType, generatedCommands)) {
					System.out.println("Connected!");
				} else {
					System.err.println("Couldn't connect");
				}
			} catch(Exception e) {
				System.err.println("Couldn't connect the crew: " + e.getMessage());
				e.printStackTrace();
			}
			
			crewWindow.setVisible(true);	
		} else {
			System.out.println("First satisfy dependencies on: controlPanel, crewWindow");
		}
		
	}
	

}
