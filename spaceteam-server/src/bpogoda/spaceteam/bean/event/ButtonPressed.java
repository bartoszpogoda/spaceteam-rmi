package bpogoda.spaceteam.bean.event;

import java.util.EventObject;

public class ButtonPressed extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String buttonTitle;
	
	public ButtonPressed(Object source, String buttonTitle) {
		super(source);
		
		this.buttonTitle = buttonTitle;
	}
	
	public String getButtonTitle() {
		return buttonTitle;
	}

}
