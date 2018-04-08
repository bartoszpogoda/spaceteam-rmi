package bpogoda.spaceteam.server;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bpogoda.spaceteam.server.impl.GameServerImpl;

public class GameServerManager {
	
	public static final String REGISTRY_STUB_NAME = "GameServer";
	
	public static final int REGISTRY_PORT = 1099;
	
	Registry registry;
	
	public GameServerManager() {
		try {
			registry = LocateRegistry.createRegistry(REGISTRY_PORT);
		} catch (RemoteException e) {
			System.err.println("Couldn't get registry.");
		}
	}
	
	public void startServer() throws RemoteException, AlreadyBoundException {

		GameServerImpl server = new GameServerImpl();
		CrewGameServer stub = (CrewGameServer) UnicastRemoteObject.exportObject(server, 0);

		// Bind the remote object's stub in the registry
		registry.bind(REGISTRY_STUB_NAME, stub);
		
		System.out.println("Game server bound to the registry.");
	}
	
	public void stopServer() throws AccessException, RemoteException, NotBoundException {
		
		registry.unbind(REGISTRY_STUB_NAME);

		System.out.println("Game server unbound from the registry.");
		
	}
}
