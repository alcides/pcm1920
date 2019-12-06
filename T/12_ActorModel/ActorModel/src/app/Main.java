package app;

import app.messagetypes.StartMessage;

public class Main {
	public static void main(String[] args) {
		System.out.println("hello");
		
		BossActor alice = new BossActor();
		alice.receiveMessage(new StartMessage());
		alice.start();
		
	}

}
