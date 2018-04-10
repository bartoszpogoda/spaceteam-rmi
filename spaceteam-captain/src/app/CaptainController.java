package app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import bpogoda.spaceteam.server.CaptainGameServer;
import bpogoda.spaceteam.server.GameServerManager;
import bpogoda.spaceteam.server.command.Command;
import bpogoda.spaceteam.server.impl.GameState;

public class CaptainController {

	private CaptainWindow captainWindow;

	private CaptainGameServer gameServerStub;

	private GameState lastGameState;

	private int currentTeamScore;

	public void setCaptainWindow(CaptainWindow captainWindow) {
		this.captainWindow = captainWindow;
	}

	public void connectAndStart() {
		if (captainWindow != null) {
			try {
				Registry registry = LocateRegistry.getRegistry(GameServerManager.REGISTRY_PORT);
				gameServerStub = (CaptainGameServer) registry.lookup(GameServerManager.REGISTRY_STUB_NAME);

				if (gameServerStub.connectCaptain()) {
					System.out.println("Connected!");
				} else {
					System.err.println("Couldn't connect");
				}
			} catch (Exception e) {
				System.err.println("Couldn't connect the crew: " + e.getMessage());
				e.printStackTrace();
			}

			IntervalPooling intervalPooling = new IntervalPooling(this);
			intervalPooling.execute();

			captainWindow.setVisible(true);

		} else {
			System.out.println("First satisfy dependencies on: captainWindow");
		}
	}

	public void sendCommand(Command command) {
		if (lastGameState == GameState.COMMAND_PHASE) {
			try {
				lastGameState = gameServerStub.sendCommand(command);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			if (lastGameState == GameState.EXECUTION_PHASE) {
				System.out.println("Command sent");

				captainWindow.setSendCommandBtnEnabled(false);
			} else {
				System.out.println("Command not sent");
			}
		}
	}

	public void startGame() {
		try {

			lastGameState = gameServerStub.startGame();

			if (lastGameState == GameState.INITIALIZING) {
				System.out.println("couldnt start the game");
			} else if (lastGameState == GameState.COMMAND_PHASE) {
				System.out.println("Game started");
				captainWindow.updateCommands(gameServerStub.getAllCommands());

			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void poolFromServer() {

		try {
			GameState state = gameServerStub.getState();

			currentTeamScore = gameServerStub.getCurrentTeamScore();
			captainWindow.setCurrentTeamScore(currentTeamScore);

			System.out.println("Game state is now: " + state);

			if (state != lastGameState) {
				// game state changed

				System.out.println("Game state changed!");

				// TODO extract on game state changed to another method

				if (state == GameState.COMMAND_PHASE) {
					captainWindow.setSendCommandBtnEnabled(true);
				}

				this.lastGameState = state;
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void endGame() {
		try {

			this.lastGameState = gameServerStub.endGame();

			if (lastGameState == GameState.ENDED) {
				JOptionPane.showMessageDialog(null, "Game ended.");
			} else {
				JOptionPane.showMessageDialog(null, "Game couldn't be ended.");
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
