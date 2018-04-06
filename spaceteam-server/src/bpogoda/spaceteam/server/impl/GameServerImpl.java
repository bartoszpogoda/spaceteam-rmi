package bpogoda.spaceteam.server.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.server.GameServer;
import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.command.factory.RandomCommandFactory;

public class GameServerImpl implements GameServer {

	private GameState currentGameState = GameState.INITIALIZING;
	
	private List<Command> randomCommandPool = new ArrayList<>();

	private RandomCommandFactory commandFactory;
	
	public GameServerImpl() {
		commandFactory = new RandomCommandFactory();
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Hello, world!";
	}

	@Override
	public boolean connectCrew(CrewType crewType, ControlPanel controlPanel) throws RemoteException {
		System.out.println("DBG: Crew connected: " + crewType);
		
		System.out.println("DBG: Generating random commands.");
		List<Command> randomCommands = commandFactory.generate(5, controlPanel, crewType);
		
		System.out.println("DBG: Adding commands to the pool");
		randomCommandPool.addAll(randomCommands);
		
		System.out.println("DBG: Shuffling the pool");
		Collections.shuffle(randomCommandPool);
		
		return true;
	}
}
