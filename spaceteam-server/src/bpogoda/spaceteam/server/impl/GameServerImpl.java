package bpogoda.spaceteam.server.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.server.GameServerManager;
import bpogoda.spaceteam.server.CaptainGameServer;
import bpogoda.spaceteam.server.CrewGameServer;
import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.command.factory.RandomCommandFactory;
import bpogoda.spaceteam.view.ServerWindow;

public class GameServerImpl implements CrewGameServer, CaptainGameServer {

	private GameState currentGameState = GameState.INITIALIZING;

	private List<Command> randomCommandPool = new ArrayList<>();

	private Command currentCommand;

	int connectedCrewPlayers = 0;
	int connectedCaptainPlayers = 0;

	int currentTeamScore = 0;

	private static final int SCORE_BONUS_FOR_SUCCESS = 1;
	private static final int SCORE_PENALTY_FOR_FAILURE = 1;

	private ServerWindow serverWindow;

	public GameServerImpl(ServerWindow serverWindow) {
		this.serverWindow = serverWindow;
	}

	@Override
	public boolean connectCrew(CrewType crewType, List<Command> randomCommands) throws RemoteException {
		System.out.println("DBG: Crew connected: " + crewType);

		System.out.println("DBG: Adding commands to the pool");
		randomCommandPool.addAll(randomCommands);

		System.out.println("DBG: Shuffling the pool");
		Collections.shuffle(randomCommandPool);

		randomCommandPool.forEach(cmd -> System.out.println(cmd.getCommandMessage()));

		connectedCrewPlayers++;
		
		serverWindow.addCrewMemberToPlayerList(crewType);

		return true;
	}

	@Override
	public boolean connectCaptain() {
		System.out.println("DBG: Captain connected");

		serverWindow.addCaptainConnected();

		return true;
	}

	private void startGameIfAllPlayersConnected() {
		if (connectedCaptainPlayers == 1 && connectedCrewPlayers == 3) {
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

	@Override
	public GameState getState() throws RemoteException {
		return currentGameState;
	}

	@Override
	public GameState startGame() throws RemoteException {

		if (connectedCrewPlayers >= 1) {
			this.currentGameState = GameState.COMMAND_PHASE;
		}

		return this.currentGameState;
	}

	@Override
	public GameState sendCommand(Command command) {

		if (currentGameState == GameState.COMMAND_PHASE) {
			this.currentCommand = command;

			currentGameState = GameState.EXECUTION_PHASE;
		}

		return currentGameState;
	}

	@Override
	public Command getCurrentCommand() throws RemoteException {
		return currentCommand;
	}

	@Override
	public GameState onExecutedCorrectly() throws RemoteException {

		this.currentTeamScore += SCORE_BONUS_FOR_SUCCESS;

		currentGameState = GameState.COMMAND_PHASE;

		return currentGameState;
	}

	@Override
	public GameState onExecutedIncorrectly() throws RemoteException {

		this.currentTeamScore -= SCORE_PENALTY_FOR_FAILURE;

		currentGameState = GameState.COMMAND_PHASE;

		return currentGameState;
	}

	@Override
	public int getCurrentTeamScore() throws RemoteException {
		return currentTeamScore;
	}

	@Override
	public GameState endGame() throws RemoteException {
		currentGameState = GameState.ENDED;
		
		return currentGameState;
	}

}
