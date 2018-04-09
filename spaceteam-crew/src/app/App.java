package app;

import bpogoda.spaceteam.server.CrewType;
import game.GameController;
import game.factory.GameControllerFactory;

public class App {

	private static final CrewType CREW_TYPE = CrewType.EngineCrew;

	public static void main(String[] args) {

		// Todo select crew type
		CrewType crewType = CrewType.EngineCrew;

		GameController gameController = GameControllerFactory.getInstance().create(crewType);

		gameController.connectAndStart();
	}

}
