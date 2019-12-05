package library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import library.messagetypes.ExceptionMessage;
import library.messagetypes.KillMessage;
import library.messagetypes.SystemMessage;

public abstract class Actor extends Thread implements Runnable {
	ConcurrentLinkedQueue<Message> mailbox = new ConcurrentLinkedQueue<Message>();
	Actor supervisor;
	public Actor getSupervisor() {
		return supervisor;
	}
	
	private boolean isAlive = true;
	private List<Actor> children = new ArrayList<Actor>();

	protected Actor(Actor supervisor) {
		this.supervisor = supervisor;
	}
	
	public void receiveMessage(Message m) {
		mailbox.add(m);
	}
	
	protected abstract void processMessage(Message m);

	private void processSystemMessage(Message current) {
		if (current instanceof KillMessage) {
			die();
		}
		if (current instanceof ExceptionMessage) {
			if (!this.handleException(current)) {
				die();
				if (this.getSupervisor() != null) {
					this.getSupervisor().receiveMessage(current);
				}
			}
		}
	}
	
	protected boolean handleException(Message current) {
		return false;
	}

	private void processAnyMessage(Message current) {
		if (current instanceof SystemMessage) {
			System.out.println("Received system");
			processSystemMessage((SystemMessage) current);
		} else {
			System.out.println(this + " received " + current + ":" + current.getClass());
			try {
				processMessage(current);
			} catch (Exception e) {
				getSupervisor().receiveMessage(new ExceptionMessage(this, e));
				isAlive = false;
			}
		}
	}
	
	public void run() {
		int sleepCounter = 1; 
		while (isAlive) {
			if (!mailbox.isEmpty()) {
				Message current = mailbox.remove();
				processAnyMessage(current);
				sleepCounter = 1;
			} else {
				sleepCounter *= 2;
				if (sleepCounter > 1000) {
					sleepCounter = 1000;
				}
				try {
					Thread.sleep(sleepCounter);
					System.out.println("Still alive:" + this);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected void registerChild(Actor c) {
		children.add(c);
	}
	
	protected void die() {
		for (Actor child : children) {
			child.receiveMessage(new KillMessage(this));
		}
		isAlive = false;
	}
}
