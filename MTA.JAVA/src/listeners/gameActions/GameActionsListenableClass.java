package listeners.gameActions;

import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class GameActionsListenableClass extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<GameActionEventListener> listeners;
	
	public GameActionsListenableClass() {
		listeners = new ArrayList<GameActionEventListener>();
	}


	public void addGameActionsListener (GameActionEventListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void removeListener(GameActionEventListener listener) {
		listeners.remove(listener);
	}

	protected void fireEvent (String message) {
		for (GameActionEventListener listener : listeners) {
			listener.eventHappened(new GameActionEvent(this, message));
		}
	}
	protected void fireEvent(GameActionEvent e)
	{
		for (GameActionEventListener listener : listeners) {
			listener.eventHappened(e);
		}
		
	}


}
