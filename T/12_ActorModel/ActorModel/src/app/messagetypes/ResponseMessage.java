package app.messagetypes;

import library.Actor;
import library.Message;

public class ResponseMessage extends Message {
	private int calculatedNumber;

	public ResponseMessage(Actor sender, int c) {
		super(sender);
		calculatedNumber = c;
	}
	
	public int getCalculatedNumber() {
		return calculatedNumber;
	}
}
