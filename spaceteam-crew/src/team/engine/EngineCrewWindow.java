package team.engine;

import javax.swing.JFrame;

import bpogoda.spaceteam.bean.ControlPanel;
import game.GameController;
import team.CrewWindow;

public class EngineCrewWindow extends JFrame implements CrewWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameController gameController;

	public EngineCrewWindow() {
		getContentPane().setLayout(null);

		
		//
		
		//
		// controlPanel.addButtonToggledListener(ev -> {
		// System.out.println("Toggle new state: " + ev.getNewState());
		// });
	}

	@Override
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void setControlPanel(ControlPanel controlPanel) {
		controlPanel.setBounds(12, 13, 599, 405);
		getContentPane().add(controlPanel);
		
		
		registerControlPanelEventHandlers(controlPanel);
	}

	private void registerControlPanelEventHandlers(ControlPanel controlPanel) {
		
		controlPanel.addCommandExecutedButtonPressedListener(ev -> {
			gameController.onCommandExecutedButtonPressed();
		});
		
	}
}
