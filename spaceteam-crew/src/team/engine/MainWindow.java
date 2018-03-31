package team.engine;

import javax.swing.JFrame;

import bpogoda.spaceteam.bean.ControlPanel;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainWindow() {
		getContentPane().setLayout(null);
		
		ControlPanel controlPanel = new ControlPanel();
		controlPanel.setSliderMin(15);
		controlPanel.setSliderMax(100);
		controlPanel.setToggleButtonLabel("Active");
		controlPanel.setButtonPanelTite("elo");
		controlPanel.setButtonLabels(new String[] {"Add something", "Do stuff"});
		controlPanel.setTogglePanelTitle("Engine");
		controlPanel.setSliderPanelTitle("Cooling level");
		controlPanel.setBounds(61, 41, 525, 349);
		getContentPane().add(controlPanel);
		
		controlPanel.addButtonPressedListener(ev -> {
			System.out.println(ev.getButtonTitle());
		});
		
		controlPanel.addSliderChangedListener(ev -> {
			System.out.println("Slider new value: " + ev.getNewValue());
		});
		
		controlPanel.addButtonToggledListener(ev -> {
			System.out.println("Toggle new state: " + ev.getNewState());
		});
	}
}
