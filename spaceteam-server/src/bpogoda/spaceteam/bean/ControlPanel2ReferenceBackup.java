package bpogoda.spaceteam.bean;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import bpogoda.spaceteam.bean.event.ButtonToggled;
import bpogoda.spaceteam.bean.event.ButtonToggledListener;
import bpogoda.spaceteam.bean.event.SliderChanged;
import bpogoda.spaceteam.bean.event.SliderChangedListener;

public class ControlPanel2ReferenceBackup extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Bean properties

	private String sliderPanelTitle;

	private Integer sliderMin;

	private Integer sliderMax;

	private String togglePanelTitle;

	private String toggleButtonLabel;

	private String buttonPanelTite;

	private String[] buttonLabels;

	// Event listeners

	private List<TextChangedListener> buttonPressedListeners = new ArrayList<>();

	private List<ButtonToggledListener> buttonToggledListeners = new ArrayList<>();

	private List<SliderChangedListener> sliderChangedListeners = new ArrayList<>();

	// Swing controls

	private JToggleButton tglbtn;

	private JPanel panelSlider;

	private JSlider slider;

	private JLabel lblSliderValueLabel, lblSliderValue, lblButtons, lblSlider, lblToggle;

	private JButton btn1, btn2, btn3, btn4;

	private JButton buttons[];

	private JPanel panelButtons;

	public ControlPanel2ReferenceBackup() {
		setLayout(new GridLayout(0, 2, 0, 0));

		lblToggle = new JLabel("New label");
		add(lblToggle);

		tglbtn = new JToggleButton("New toggle button");
		add(tglbtn);

		lblSlider = new JLabel("New label");
		add(lblSlider);

		panelSlider = new JPanel();
		add(panelSlider);
		panelSlider.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		slider = new JSlider();
		panelSlider.add(slider);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		slider.addChangeListener(change -> {
			lblSliderValue.setText(Integer.toString(slider.getValue()));
		});

		lblSliderValueLabel = new JLabel("Value: ");
		panelSlider.add(lblSliderValueLabel);

		lblSliderValue = new JLabel(Integer.toString(slider.getValue()));
		panelSlider.add(lblSliderValue);

		lblButtons = new JLabel("New label");
		add(lblButtons);

		panelButtons = new JPanel();
		add(panelButtons);
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btn1 = new JButton("New button");
		panelButtons.add(btn1);

		btn2 = new JButton("New button");
		panelButtons.add(btn2);

		btn3 = new JButton("New button");
		panelButtons.add(btn3);

		btn4 = new JButton("New button");
		panelButtons.add(btn4);

		buttons = new JButton[] { btn1, btn2, btn3, btn4 };

		registerUIListeners();
	}

	private void registerUIListeners() {
		Arrays.stream(buttons).forEach(btn -> btn.addActionListener((ev) -> {
			buttonPressedListeners
					.forEach(l -> l.textChanged(new TextChanged(this, ((JButton) ev.getSource()).getText())));
		}));

		tglbtn.addActionListener(ev -> {
			buttonToggledListeners.forEach(l -> l.buttonToggled(new ButtonToggled(this, ((JToggleButton) ev.getSource()).isSelected())));
		});
		
		slider.addChangeListener(ev -> {
			sliderChangedListeners.forEach(l -> l.sliderChanged(new SliderChanged(this, ((JSlider) ev.getSource()).getValue())));
		});
	}

	@Override
	public void paint(Graphics g) {

		// Slider panel
		if (sliderPanelTitle == null || sliderPanelTitle.isEmpty()) {
			lblSlider.setEnabled(false);
			panelSlider.setEnabled(false);
		} else {
			lblSlider.setText(sliderPanelTitle);

			if (sliderMin != null && sliderMax != null) {
				slider.setMinimum(sliderMin);
				slider.setMaximum(sliderMax);
			}
		}

		// Toggle panel
		if (togglePanelTitle == null || togglePanelTitle.isEmpty() || toggleButtonLabel == null
				|| toggleButtonLabel.isEmpty()) {
			lblToggle.setVisible(false);
			tglbtn.setVisible(false);
		} else {
			lblToggle.setText(togglePanelTitle);
			tglbtn.setText(toggleButtonLabel);

			lblToggle.setVisible(true);
			tglbtn.setVisible(true);
		}

		// Buttons panel
		if (buttonLabels != null && buttonPanelTite != null && !buttonPanelTite.isEmpty() && buttonLabels.length > 0) {

			int i = 0;
			for (; i < buttonLabels.length; i++) {
				buttons[i].setText(buttonLabels[i]);
				buttons[i].setVisible(true);
			}

			for (; i < buttons.length; i++) {
				buttons[i].setVisible(false);
			}

			lblButtons.setText(buttonPanelTite);
			lblButtons.setVisible(true);
			panelButtons.setVisible(true);
		} else {
			lblButtons.setVisible(false);
			panelButtons.setVisible(false);
		}

		super.paint(g);
	}

	public synchronized void addButtonPressedListener(TextChangedListener l) {
		buttonPressedListeners.add(l);
	}

	public synchronized void removeButtonPressedListener(TextChangedListener l) {
		buttonPressedListeners.remove(l);
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

	public String getSliderPanelTitle() {
		return sliderPanelTitle;
	}

	public void setSliderPanelTitle(String sliderPanelTitle) {
		this.sliderPanelTitle = sliderPanelTitle;
	}

	public String getTogglePanelTitle() {
		return togglePanelTitle;
	}

	public void setTogglePanelTitle(String togglePanelTitle) {
		this.togglePanelTitle = togglePanelTitle;
	}

	public String[] getButtonLabels() {
		return buttonLabels;
	}

	public void setButtonLabels(String[] buttonLabels) {
		this.buttonLabels = buttonLabels;
	}

	public String getButtonPanelTite() {
		return buttonPanelTite;
	}

	public void setButtonPanelTite(String buttonPanelTite) {
		this.buttonPanelTite = buttonPanelTite;
	}

	public String getToggleButtonLabel() {
		return toggleButtonLabel;
	}

	public void setToggleButtonLabel(String toggleButtonLabel) {
		this.toggleButtonLabel = toggleButtonLabel;
	}

	public Integer getSliderMin() {
		return sliderMin;
	}

	public void setSliderMin(Integer sliderMin) {
		this.sliderMin = sliderMin;
	}

	public Integer getSliderMax() {
		return sliderMax;
	}

	public void setSliderMax(Integer sliderMax) {
		this.sliderMax = sliderMax;
	}

}
