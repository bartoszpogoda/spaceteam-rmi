package app;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bpogoda.spaceteam.server.Server;
import team.engine.MainWindow;

public class App {

	public static void main(String[] args) {

		// RMI test

		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			Server stub = (Server) registry.lookup("Server");
			String response = stub.sayHello();
			System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}

		MainWindow mw = new MainWindow();
		mw.setVisible(true);
	}

}
