package game.factory;

import bpogoda.spaceteam.server.CrewType;
import game.GameController;
import team.engine.EngineCrewWindow;

public class GameControllerFactory {

	private GameControllerFactory() {};
	
	private static GameControllerFactory instance = null;
	
	public static GameControllerFactory getInstance() {
		return instance == null ? instance = new GameControllerFactory() : instance;
	}
	
	public GameController create(CrewType crewType) {
		
		ControlPanelFactory controlPanelFactory = ControlPanelFactory.getInstance();

		GameController gameController = null;
		
		if (crewType == CrewType.EngineCrew) {
			gameController = new GameController(crewType, new EngineCrewWindow());
		}
		
		
		gameController.setControlPanel(controlPanelFactory.create(crewType));

		return gameController;
	}

}
