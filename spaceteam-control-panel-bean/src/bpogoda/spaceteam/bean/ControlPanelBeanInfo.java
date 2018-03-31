package bpogoda.spaceteam.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

// WindowBuilder seems to ignore BeanInfo descriptors.
public class ControlPanelBeanInfo extends SimpleBeanInfo {

	private final static Class<?> beanClass = ControlPanel.class;

	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor panelTitle = new PropertyDescriptor("sliderPanelTitle", beanClass);
			panelTitle.setDisplayName("Test");
			PropertyDescriptor rv[] = { panelTitle };
			
			return rv;
		} catch (IntrospectionException e) {
			throw new Error(e.toString() + " test");
		}
	}

}
