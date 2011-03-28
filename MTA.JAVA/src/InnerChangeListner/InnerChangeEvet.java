package InnerChangeListner;

public class InnerChangeEvet{
	private Object source;
	private String message;
	
	public InnerChangeEvet (Object source, String message) {
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
