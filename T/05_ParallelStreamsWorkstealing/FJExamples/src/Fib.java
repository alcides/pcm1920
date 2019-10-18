import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Fib extends RecursiveTask<Integer> {
	public static void main(String[] args) {
		

		
		ForkJoinPool p1 = new ForkJoinPool(
				Runtime.getRuntime().availableProcessors()/2
			);
		
		ForkJoinPool p2 = new ForkJoinPool(
				Runtime.getRuntime().availableProcessors()/2
			);
		
		Fib f = new Fib(5);
		p1.execute(f);
		
		p2.execute(f);
		
		
		
		System.out.println("Fib 5 = " + f.join());
	}
	
	private int n;
	
	public Fib(int n) {
		this.n = n;
	}
	
	@Override
	protected Integer compute() {
		if (n <= 1) return 1;
		
		// Max Tasks (100)
		if (ForkJoinTask.getQueuedTaskCount() > 100) return fib(n);
		
		// Surplus (10)
		if (ForkJoinTask.getSurplusQueuedTaskCount() > 10) return fib(n);
				
		
		Fib fm1 = new Fib(n-1);
		Fib fm2 = new Fib(n-2);
		
		fm1.fork();
		fm2.fork();
		
		return fm1.join() + fm2.join();
	}
	
	
	public static int fib(int n) {
		if (n <= 1) return 1;
		return fib(n-1) + fib(n-2);
	}
}
