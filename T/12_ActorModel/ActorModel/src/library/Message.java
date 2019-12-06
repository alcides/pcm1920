package library;

public abstract class Message {
	
	private Actor sender;
	
	protected Message(Actor sender) {
		this.sender = sender;
	}

	public Actor getSender() {
		return sender;
	}

}
