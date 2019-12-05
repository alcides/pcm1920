package library.messagetypes;

import library.Actor;
import library.Message;

public abstract class SystemMessage extends Message {

	protected SystemMessage(Actor sender) {
		super(sender);
	}

}
