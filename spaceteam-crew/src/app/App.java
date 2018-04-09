package app;

import bpogoda.spaceteam.server.CrewType;
import game.GameController;
import game.factory.GameControllerFactory;

public class App {

	private static final CrewType CREW_TYPE = CrewType.EngineCrew;

	public static void main(String[] args) {

		App app = new App();
		
		SelectCrewTypeFrame selectCrewTypeFrame = new SelectCrewTypeFrame();
		selectCrewTypeFrame.setApp(app);
		selectCrewTypeFrame.setVisible(true);

	}

	public void enterGame(CrewType crewType) {

		GameController gameController = GameControllerFactory.getInstance().create(crewType);

		gameController.connectAndStart();

	}

}
