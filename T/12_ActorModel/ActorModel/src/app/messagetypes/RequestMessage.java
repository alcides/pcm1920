package app.messagetypes;

import library.Actor;
import library.Message;

public class RequestMessage extends Message {
	private int number;
	
	public RequestMessage(Actor sender, int n) {
		super(sender);
		 number = n;
	}

	public int getNumber() {
		return number;
	}

}
