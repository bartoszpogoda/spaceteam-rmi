package team.engine;

import javax.swing.JFrame;

import bpogoda.spaceteam.bean.ControlPanel;

public class EngineCrewWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EngineCrewWindow() {
		getContentPane().setLayout(null);
		
		ControlPanel controlPanel = new ControlPanel();
		controlPanel.setSliderDeviceMax(11);
		controlPanel.setSliderDeviceMin(5);
		controlPanel.setToggleDeviceBtnLabel("Super boost");
		controlPanel.setToggleDeviceName("Extra features");
		controlPanel.setTextDeviceName("");
		controlPanel.setSliderDeviceName("Engine cooling level");
		controlPanel.setBounds(12, 13, 599, 405);
		getContentPane().add(controlPanel);
//		
//		controlPanel.addButtonPressedListener(ev -> {
//			System.out.println(ev.getButtonTitle());
//		});
//		
		controlPanel.addSliderChangedListener(ev -> {
			System.out.println("Slider new value: " + ev.getNewValue());
		});
		
		controlPanel.addCommandExecutedButtonPressedListener(ev -> {;
			System.out.println("Command executed!");
		});
//		
//		controlPanel.addButtonToggledListener(ev -> {
//			System.out.println("Toggle new state: " + ev.getNewState());
//		});
	}
}
