package team;

import bpogoda.spaceteam.bean.ControlPanel;
import game.GameController;

public interface CrewWindow {
	
	void setGameController(GameController gameController);
	
	void setControlPanel(ControlPanel controlPanel);

	void setVisible(boolean b);	
}
