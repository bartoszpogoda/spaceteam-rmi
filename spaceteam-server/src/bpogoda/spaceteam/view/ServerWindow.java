package bpogoda.spaceteam.view;

import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import bpogoda.spaceteam.server.GameServerManager;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private GameServerManager gameServerManager;

	private JButton btnStartServer;

	private JButton btnStopServer;

	public static void main(String args[]) {
		ServerWindow serverWindow = new ServerWindow("Game Server");
		serverWindow.setGameServerManager(new GameServerManager());

		serverWindow.setVisible(true);
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public ServerWindow(String title) throws HeadlessException {
		super(title);

		setSize(400, 200);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnStartServer = new JButton("Start server");
		panel.add(btnStartServer);
		
		btnStopServer = new JButton("Stop server");
		panel.add(btnStopServer);

		registerButtonHandlers();
		registerWindowClosingHandler();
	}

	private void registerButtonHandlers() {
		btnStartServer.addActionListener((ev) -> {
			try {
				gameServerManager.startServer();
			} catch (RemoteException | AlreadyBoundException e) {
				System.err.println("Couldn't start the server: " + e.getMessage());
			}
		});
		
		btnStopServer.addActionListener((ev) -> {
			try {
				gameServerManager.stopServer();
			} catch (RemoteException | NotBoundException e) {
				System.err.println("Couldn't stop the server: " + e.getMessage());
			}
		});
		
	}

	public void setGameServerManager(GameServerManager gameServerManager) {
		this.gameServerManager = gameServerManager;
	}

	private void registerWindowClosingHandler() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				if (gameServerManager != null) {
					try {
						gameServerManager.stopServer();
					} catch (RemoteException | NotBoundException e1) {
						System.err.println("Couldnt stop the server.");
					}
				}

				System.exit(0);
			}
		});
	}

}
