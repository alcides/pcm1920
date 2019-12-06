package library.messagetypes;

import library.Actor;
import library.Message;

public class ExceptionMessage extends SystemMessage {

	private Exception exception;
	
	public ExceptionMessage(Actor sender, Exception e) {
		super(sender);
		this.exception = e;
	}

	public Exception getException() {
		return exception;
	}

}
