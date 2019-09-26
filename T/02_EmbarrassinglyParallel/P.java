public class P {

	public static void timeit(String n, Runnable c) {
		long t = System.nanoTime();
		c.run();
		System.out.println(n + ":" + (System.nanoTime() - t));
		System.out.println("----------------------");
	}

	public static void main(String[] args) {
		int N = 1000000;
		int[] array = new int[N];
		for (int i=0; i<N; i++) {
			array[i] = i % (N/10);
		}
		
		int NTHREADS = 24;
		
		Thread[] threads = new Thread[NTHREADS];
	
		
		for (int tid=0; tid < NTHREADS; tid++ ) {
			final int tidInside = tid;
			Runnable r = () -> {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				int startIndex = tidInside * N / NTHREADS;
				int endIndex = (tidInside + 1) * N / NTHREADS ;
				
				for (int i=startIndex; i<endIndex; i++) {
					if (array[i] == 0) System.out.println("Found zero at " + i);
				} 
			};
			
			
			
			/*
			Runnable same_r = new Runnable() {

				@Override
				public void run(int a) {
					System.out.println("Hello alt " + tid);
				}
				
			};*/
			
			threads[tidInside] = new Thread(r);
			threads[tidInside].start();
		}
		
		for (Thread t :threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Bye");
		
		System.out.println(Runtime.getRuntime().availableProcessors());
		
	}
}
