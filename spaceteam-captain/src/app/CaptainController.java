package app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import bpogoda.spaceteam.server.CaptainGameServer;
import bpogoda.spaceteam.server.GameServerManager;
import bpogoda.spaceteam.server.command.Command;

public class CaptainController {

	private CaptainWindow captainWindow;
	
	private CaptainGameServer gameServerStub;

	public void setCaptainWindow(CaptainWindow captainWindow) {
		this.captainWindow = captainWindow;
	}


	public void connectAndStart() {
		if(captainWindow != null) {
			try {
				Registry registry = LocateRegistry.getRegistry(GameServerManager.REGISTRY_PORT);
				gameServerStub = (CaptainGameServer) registry.lookup(GameServerManager.REGISTRY_STUB_NAME);
				
				if(gameServerStub.connectCaptain()) {
					System.out.println("Connected!");
				} else {
					System.err.println("Couldn't connect");
				}
			} catch(Exception e) {
				System.err.println("Couldn't connect the crew: " + e.getMessage());
				e.printStackTrace();
			}
			
			try {
				start();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("First satisfy dependencies on: captainWindow");
		}
	}
	
	private void start() throws RemoteException {
		captainWindow.setVisible(true);
		
//		List<Command> commands = gameServerStub.getCommands(5);
		captainWindow.updateCommands(gameServerStub.getAllCommands());
	}


	public void sendCommand(Command command) {
		System.out.println("To send: " + command.getCommandMessage());
	}

}
