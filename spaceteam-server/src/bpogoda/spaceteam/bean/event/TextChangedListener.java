package bpogoda.spaceteam.bean.event;

import java.util.EventListener;

public interface TextChangedListener extends EventListener {
	public abstract void textChanged(TextChanged event);
}
