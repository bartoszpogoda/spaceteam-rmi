package bpogoda.spaceteam.bean.event;

import java.util.EventListener;

public interface ButtonPressedListener extends EventListener {
	public abstract void buttonPressed(ButtonPressed event);
}
