import java.util.concurrent.RecursiveTask;

public class Fib extends RecursiveTask<Integer> {
	public static void main(String[] args) {
		
		Fib f = new Fib(5);
		System.out.println("Fib 5 = " + f.compute());
	}
	
	private int n;
	
	public Fib(int n) {
		this.n = n;
	}
	
	@Override
	protected Integer compute() {
		if (n <= 1) return 1;
		
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
