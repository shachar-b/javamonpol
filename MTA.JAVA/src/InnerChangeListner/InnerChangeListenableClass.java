package InnerChangeListner;

import java.awt.List;
import java.util.ArrayList;

public abstract class InnerChangeListenableClass {
	
	 
	private ArrayList<InnerChangeEventListner> listeners;
	
	
	public InnerChangeListenableClass() {
		listeners = new ArrayList<InnerChangeEventListner>();
	}


	public void addInnerChangeEventListner (InnerChangeEventListner listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void removeListener(InnerChangeEventListner listener) {
		listeners.remove(listener);
	}

	protected void fireEvent (String message) {
		for (InnerChangeEventListner listener : listeners) {
			listener.eventHappened(new InnerChangeEvet(this, message));
		}
	}


}
