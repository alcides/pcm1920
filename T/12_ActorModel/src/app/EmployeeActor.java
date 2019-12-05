package app;

import library.Actor;
import library.Message;
import app.messagetypes.RequestMessage;
import app.messagetypes.ResponseMessage;

public class EmployeeActor extends Actor {

	protected EmployeeActor(Actor supervisor) {
		super(supervisor);
	}

	@Override
	protected void processMessage(Message m) {
		if (m instanceof RequestMessage) {
			RequestMessage m2 = (RequestMessage) m;
			int result = 2 * m2.getNumber() / 0;
			ResponseMessage reply = new ResponseMessage(this, result);
			m.getSender().receiveMessage(reply);
		}
	}

}
