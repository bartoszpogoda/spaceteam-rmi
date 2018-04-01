package bpogoda.spaceteam.server.impl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bpogoda.spaceteam.server.Server;

public class ServerImpl implements Server {

	public ServerImpl() {
	}

	@Override
	public String sayHello() throws RemoteException {
		return "Hello, world!";
	}

	public static void main(String args[]) {
		try {
			ServerImpl server = new ServerImpl();
			Server stub = (Server) UnicastRemoteObject.exportObject(server, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Server", stub);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
