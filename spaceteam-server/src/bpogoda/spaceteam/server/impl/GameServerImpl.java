package bpogoda.spaceteam.server.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.server.CaptainGameServer;
import bpogoda.spaceteam.server.CrewGameServer;
import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.command.factory.RandomCommandFactory;

public class GameServerImpl implements CrewGameServer, CaptainGameServer {

	private GameState currentGameState = GameState.INITIALIZING;

	private List<Command> randomCommandPool = new ArrayList<>();
	
	int connectedCrewPlayers = 0;
	int connectedCaptainPlayers = 0;

	@Override
	public boolean connectCrew(CrewType crewType, List<Command> randomCommands) throws RemoteException {
		System.out.println("DBG: Crew connected: " + crewType);

		System.out.println("DBG: Adding commands to the pool");
		randomCommandPool.addAll(randomCommands);

		System.out.println("DBG: Shuffling the pool");
		Collections.shuffle(randomCommandPool);

		randomCommandPool.forEach(cmd -> System.out.println(cmd.getCommandMessage()));
		
		connectedCrewPlayers++;
		startGameIfAllPlayersConnected();
		
		return true;
	}

	@Override
	public boolean connectCaptain() {
		System.out.println("DBG: Captain connected");
		
		connectedCaptainPlayers++;
		startGameIfAllPlayersConnected();

		return true;
	}
	
	private void startGameIfAllPlayersConnected() {
		if(connectedCaptainPlayers == 1 && connectedCrewPlayers == 3) {
			currentGameState = GameState.COMMAND_PHASE;
		}
	}

	@Override
	public List<Command> getCommands(int number) {

		List<Command> commands = new ArrayList<>();

		for (int i = 0; i < number; i++) {
			commands.add(randomCommandPool.remove(0));
		}

		return commands;
	}

	@Override
	public List<Command> getAllCommands() throws RemoteException {
		List<Command> commands = new ArrayList<>(randomCommandPool);

		randomCommandPool.clear();
		
		return commands;
	}
}
