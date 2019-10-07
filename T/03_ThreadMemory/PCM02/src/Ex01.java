public class Ex01 {
	private static final int NUMBER_OF_THREADS = 4;

	public static void main(String[] args) {
		
		int LOOP_SIZE = 100000;
		
		long count = 0;
	
		Thread[] ts = new Thread[NUMBER_OF_THREADS];
		int[] localValues = new int[NUMBER_OF_THREADS];
		
		for (int t=0; t < NUMBER_OF_THREADS; t++) {
			
			final int localT = t;
			ts[t] = new Thread( () -> {
				int startIndex = localT * LOOP_SIZE / NUMBER_OF_THREADS;
				int endIndex = (localT+1) * LOOP_SIZE / NUMBER_OF_THREADS;
				
				int localCount = 0;
				for (int i=startIndex; i<endIndex; i++) {
					if (i % 10000 == 0) {
						localCount++;
					}
				}
				localValues[localT] += localCount;
			});
			ts[t].setPriority(Thread.MAX_PRIORITY);
			ts[t].start();
			
			Runtime.getRuntime().availableProcessors();
			
		}
	
		for (int t=0; t < NUMBER_OF_THREADS; t++) {
			try {
				ts[t].join();
				count += localValues[t]; 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(count);
	}
}
