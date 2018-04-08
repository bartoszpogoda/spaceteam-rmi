package bpogoda.spaceteam.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.impl.GameState;

public interface CaptainGameServer extends Remote {
	boolean connectCaptain() throws RemoteException;
	
	GameState startGame() throws RemoteException;
	
	List<Command> getCommands(int number) throws RemoteException;
	
	List<Command> getAllCommands() throws RemoteException;

	GameState getState() throws RemoteException;

	GameState sendCommand(Command command) throws RemoteException;
}
