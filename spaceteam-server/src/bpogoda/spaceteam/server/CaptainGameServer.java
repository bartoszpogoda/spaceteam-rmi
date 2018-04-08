package bpogoda.spaceteam.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import bpogoda.spaceteam.server.command.Command;

public interface CaptainGameServer extends Remote {
	boolean connectCaptain() throws RemoteException;
	
	List<Command> getCommands(int number) throws RemoteException;
	
	List<Command> getAllCommands() throws RemoteException;
}
