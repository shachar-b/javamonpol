package listeners.gameActions;

public class GameActionEvent{
	private Object source;
	private String message;
	
	public GameActionEvent (Object source, String message) {
		this.source = source;
		this.message = message;
	}

	public Object getSource() {
		return source;
	}
	
	public String getMessage() {
		return message;
	}
}
