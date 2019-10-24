package bugs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class PhaserExample {
	static void startTasks(List<Runnable> tasks, final int iterations) {
		   final Phaser phaser = new Phaser() {
		     protected boolean onAdvance(int phase, int registeredParties) {
		       return phase >= iterations || registeredParties == 0;
		     }
		   };
		   phaser.register(); // register for the first barrier with parties = 1
		   for (final Runnable task : tasks) {
		     phaser.register();
		     new Thread() {
		       public void run() {
		         do {
		           task.run();
		           phaser.arriveAndAwaitAdvance();
		         } while (!phaser.isTerminated());
		       }
		     }.start();
		   }
		   phaser.arriveAndDeregister(); // releases the first barrier
		 }
	
	public static void main(String[] args) {
		List<Runnable> l = new ArrayList<>();
		l.add( () -> { System.out.println("Benfica"); } );
		l.add( () -> { System.out.println("Sporting"); } );
		l.add( () -> { System.out.println("Porto"); } );
		startTasks(l,10);
	}
}
