package app;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.server.CrewGameServer;
import bpogoda.spaceteam.server.GameServerManager;
import game.GameController;
import game.factory.GameControllerFactory;
import team.engine.EngineCrewWindow;

public class App {

	private static final CrewType CREW_TYPE = CrewType.EngineCrew;

	public static void main(String[] args) {

		// Todo select crew type
		CrewType crewType = CrewType.EngineCrew;

		GameController gameController = GameControllerFactory.getInstance().create(crewType);

		gameController.connectAndStart();
	}

}
