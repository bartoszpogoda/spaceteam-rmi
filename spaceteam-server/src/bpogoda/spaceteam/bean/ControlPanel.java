package bpogoda.spaceteam.bean;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import bpogoda.spaceteam.bean.event.TextChanged;
import bpogoda.spaceteam.bean.event.TextChangedListener;
import bpogoda.spaceteam.server.CrewType;
import bpogoda.spaceteam.bean.event.ButtonToggled;
import bpogoda.spaceteam.bean.event.ButtonToggledListener;
import bpogoda.spaceteam.bean.event.CommandExecutedButtonPressed;
import bpogoda.spaceteam.bean.event.CommandExecutedButtonPressedListener;
import bpogoda.spaceteam.bean.event.SliderChanged;
import bpogoda.spaceteam.bean.event.SliderChangedListener;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Bean properties

	private String sliderDeviceName;

	private Integer sliderDeviceMin;

	private Integer sliderDeviceMax;

	private String toggleDeviceName;

	private String toggleDeviceBtnLabel;

	private String textDeviceName;

	private String currentCommand;

	private CrewType crewType;

	private String[] possibleTextCommands;

	// Event listeners

	private List<TextChangedListener> textChangedListeners = new ArrayList<>();

	private List<ButtonToggledListener> buttonToggledListeners = new ArrayList<>();

	private List<SliderChangedListener> sliderChangedListeners = new ArrayList<>();

	private List<CommandExecutedButtonPressedListener> commandExecutedButtonPressedListeners = new ArrayList<>();

	private JButton buttons[];
	private JTextField tfTextDevice;

	private JPanel panelSliderDevice;

	private JSlider sliderSliderDevice;

	private JLabel lblSliderDeviceValue;

	private JLabel lblTitle;

	private JPanel panelToggleDevice;

	private JToggleButton tglbtnToggleDevice;

	private JPanel panelTextDevice;

	private JButton btnActionDone;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblTeamScore;
	private JLabel lblCommanderSays;
	private JPanel panel_2;
	private JLabel lblCurrentCommand;
	private JLabel label;
	private JPanel panel_3;
	private JLabel lblYoureInThe;

	public ControlPanel() {
		setLayout(null);

		panelSliderDevice = new JPanel();
		panelSliderDevice.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Slider Device",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSliderDevice.setBounds(12, 125, 426, 66);
		add(panelSliderDevice);

		sliderSliderDevice = new JSlider();
		panelSliderDevice.add(sliderSliderDevice);

		lblSliderDeviceValue = new JLabel("New label");
		panelSliderDevice.add(lblSliderDeviceValue);

		panelToggleDevice = new JPanel();
		panelToggleDevice
				.setBorder(new TitledBorder(null, "Toggle Device", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelToggleDevice.setBounds(12, 204, 426, 58);
		add(panelToggleDevice);

		tglbtnToggleDevice = new JToggleButton("New toggle button");
		panelToggleDevice.add(tglbtnToggleDevice);

		panelTextDevice = new JPanel();
		panelTextDevice
				.setBorder(new TitledBorder(null, "Text Device", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTextDevice.setBounds(12, 275, 426, 74);
		add(panelTextDevice);

		tfTextDevice = new JTextField();
		panelTextDevice.add(tfTextDevice);
		tfTextDevice.setColumns(10);

		btnActionDone = new JButton("Commander, the command has been executed!");
		btnActionDone.setBounds(12, 362, 426, 25);
		add(btnActionDone);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(449, 13, 139, 374);
		add(panel_1);
		panel_1.setLayout(new GridLayout(4, 2, 0, 0));

		lblNewLabel = new JLabel("Team score:");
		panel_1.add(lblNewLabel);

		lblTeamScore = new JLabel("50");
		lblNewLabel.setLabelFor(lblTeamScore);
		panel_1.add(lblTeamScore);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(12, 13, 438, 39);
		add(panel_2);

		lblCommanderSays = new JLabel("Commander says: \"");
		panel_2.add(lblCommanderSays);

		lblCurrentCommand = new JLabel("Welcome, my team!");
		panel_2.add(lblCurrentCommand);

		label = new JLabel("\"");
		panel_2.add(label);

		panel_3 = new JPanel();
		panel_3.setBounds(12, 60, 426, 39);
		add(panel_3);

		lblYoureInThe = new JLabel("You're in the:  ");
		panel_3.add(lblYoureInThe);

		lblTitle = new JLabel("Engine steering room");
		panel_3.add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

		registerUIListeners();
	}

	private void registerUIListeners() {
		sliderSliderDevice.addChangeListener(ev -> {
			sliderChangedListeners
					.forEach(l -> l.sliderChanged(new SliderChanged(this, ((JSlider) ev.getSource()).getValue())));
		});

		btnActionDone.addActionListener(ev -> {
			commandExecutedButtonPressedListeners
					.forEach(l -> l.commandExecutedButtonPressed(new CommandExecutedButtonPressed(this)));
		});
	}

	@Override
	public void paint(Graphics g) {

		// Slider device
		if (sliderDeviceName == null || sliderDeviceName.isEmpty()) {
			panelSliderDevice.setEnabled(false);
			sliderSliderDevice.setEnabled(false);
			lblSliderDeviceValue.setEnabled(false);
			setTitledBorder(panelSliderDevice, "Disabled");

		} else {
			setTitledBorder(panelSliderDevice, sliderDeviceName);

			if (sliderDeviceMin != null && sliderDeviceMax != null) {
				sliderSliderDevice.setMinimum(sliderDeviceMin);
				sliderSliderDevice.setMaximum(sliderDeviceMax);
			}
		}

		// Toggle device
		if (toggleDeviceName == null || toggleDeviceName.isEmpty() || toggleDeviceBtnLabel == null
				|| toggleDeviceBtnLabel.isEmpty()) {
			panelToggleDevice.setEnabled(false);
			setTitledBorder(panelToggleDevice, "Disabled");
			tglbtnToggleDevice.setEnabled(false);

		} else {
			setTitledBorder(panelToggleDevice, toggleDeviceName);
			tglbtnToggleDevice.setText(toggleDeviceBtnLabel);
		}

		// Text device
		if (textDeviceName == null || textDeviceName.isEmpty()) {
			panelTextDevice.setEnabled(false);
			setTitledBorder(panelTextDevice, "Disabled");
			tfTextDevice.setEnabled(false);

		} else {
			setTitledBorder(panelTextDevice, textDeviceName);
		}

		super.paint(g);
	}

	private void setTitledBorder(JPanel panel, String title) {
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), title, TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
	}

	public synchronized void addButtonPressedListener(TextChangedListener l) {
		textChangedListeners.add(l);
	}

	public synchronized void removeButtonPressedListener(TextChangedListener l) {
		textChangedListeners.remove(l);
	}

	public synchronized void addButtonToggledListener(ButtonToggledListener l) {
		buttonToggledListeners.add(l);
	}

	public synchronized void removeButtonToggledListener(ButtonToggledListener l) {
		buttonToggledListeners.remove(l);
	}

	public synchronized void addSliderChangedListener(SliderChangedListener l) {
		sliderChangedListeners.add(l);
	}

	public synchronized void removeSliderChangedListener(SliderChangedListener l) {
		sliderChangedListeners.remove(l);
	}

	public synchronized void addCommandExecutedButtonPressedListener(CommandExecutedButtonPressedListener l) {
		commandExecutedButtonPressedListeners.add(l);
	}

	public synchronized void removeCommandExecutedButtonPressedListener(CommandExecutedButtonPressedListener l) {
		commandExecutedButtonPressedListeners.remove(l);
	}

	public String getToggleDeviceName() {
		return toggleDeviceName;
	}

	public void setToggleDeviceName(String toggleDeviceName) {
		this.toggleDeviceName = toggleDeviceName;
	}

	public String getToggleDeviceBtnLabel() {
		return toggleDeviceBtnLabel;
	}

	public void setToggleDeviceBtnLabel(String toggleDeviceBtnLabel) {
		this.toggleDeviceBtnLabel = toggleDeviceBtnLabel;
	}

	public String getSliderDeviceName() {
		return sliderDeviceName;
	}

	public void setSliderDeviceName(String sliderDeviceName) {
		this.sliderDeviceName = sliderDeviceName;
	}

	public Integer getSliderDeviceMin() {
		return sliderDeviceMin;
	}

	public void setSliderDeviceMin(Integer sliderDeviceMin) {
		this.sliderDeviceMin = sliderDeviceMin;
	}

	public Integer getSliderDeviceMax() {
		return sliderDeviceMax;
	}

	public void setSliderDeviceMax(Integer sliderDeviceMax) {
		this.sliderDeviceMax = sliderDeviceMax;
	}

	public String getTextDeviceName() {
		return textDeviceName;
	}

	public void setTextDeviceName(String textDeviceName) {
		this.textDeviceName = textDeviceName;
	}

	public void setCurrentCommand(String currentCommand) {
		this.currentCommand = currentCommand;
		lblCurrentCommand.setText(currentCommand);
	}

	public CrewType getCrewType() {
		return crewType;
	}

	public void setCrewType(CrewType crewType) {
		this.crewType = crewType;
	}

	public String[] getPossibleTextCommands() {
		return possibleTextCommands;
	}

	public void setPossibleTextCommands(String[] possibleTextCommands) {
		this.possibleTextCommands = possibleTextCommands;
	}

}
