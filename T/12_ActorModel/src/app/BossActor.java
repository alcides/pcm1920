package app;

import library.Actor;
import library.Message;
import library.messagetypes.KillMessage;
import app.messagetypes.RequestMessage;
import app.messagetypes.ResponseMessage;
import app.messagetypes.StartMessage;

public class BossActor extends Actor {
	
	public static int NUMBER_OF_EMPLOYEES = 4;

	private int total = 0;
	private int ongoingNumber = 0;
	
	public BossActor() {
		super(null);
	}
	
	protected BossActor(Actor supervisor) {
		super(supervisor);
	}

	@Override
	protected void processMessage(Message m) {
		if (m instanceof StartMessage) {
			for (int i=0; i<NUMBER_OF_EMPLOYEES; i++) {
				EmployeeActor e = new EmployeeActor(this);
				registerChild(e);
				e.receiveMessage(new RequestMessage(this, i));
				e.start();
				ongoingNumber = i + 1;
			}
		}
		if (m instanceof ResponseMessage) {
			ResponseMessage m2 = (ResponseMessage) m;
			System.out.println("Alice received " + m2.getCalculatedNumber());
			total += m2.getCalculatedNumber();
			if (total > 1000) {
				System.out.println("More than 100");
				// Stop the system
				die();
			} else {
				RequestMessage req = new RequestMessage(this, ongoingNumber++);
				m.getSender().receiveMessage(req);
			}
		}
		
		
	}

	@Override
	protected boolean handleException(Message current) {
		System.out.println("Handled child");
		//current.getSender().receiveMessage(new KillMessage(this));
		return false;
	}

}
