package listeners.gameActions;

public class GameActionEvent{
	private Object source;
	private String message;
	private int gameID;
	
	public GameActionEvent (Object source, String message, int gameID) {
		this.source = source;
		this.message = message;
		this.gameID = gameID;
	}

	public Object getSource() {
		return source;
	}
	
	public int getGameID()
	{
		return gameID;
	}

	public String getMessage() {
		return message;
	}
}
