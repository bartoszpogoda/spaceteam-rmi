package app;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.server.GameServer;
import bpogoda.spaceteam.server.GameServerManager;
import team.engine.MainWindow;

public class App {

	private static final CrewType CREW_TYPE = CrewType.EngineCrew;
	
	public static void main(String[] args) {

		try {
			// Connect to GameServer
			Registry registry = LocateRegistry.getRegistry(GameServerManager.REGISTRY_PORT);
			GameServer stub = (GameServer) registry.lookup(GameServerManager.REGISTRY_STUB_NAME);
			boolean response = stub.connectCrew(CREW_TYPE, null);
			System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}

		MainWindow mw = new MainWindow();
		mw.setVisible(true);
	}

}
