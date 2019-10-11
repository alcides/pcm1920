import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class SuperDuperParallelLibrary {
	
	public static <T> List<T> doInParallel(int ITERATIONS, BiFunction<Integer, Integer, T> code ) {
		int NTHREADS = Runtime.getRuntime().availableProcessors();
		Thread[] ts = new Thread[NTHREADS];
		List<T> l = new ArrayList<>(); // This is actually bug-prone
		
		for (int i=0; i<NTHREADS; i++) {
			final int tid = i;
			ts[i] = new Thread( () -> {
				int startIndex = tid * ITERATIONS / NTHREADS ;
				int endIndex = (tid+1) * ITERATIONS / NTHREADS;
				T result = code.apply(startIndex, endIndex);
				l.add(result);			
			});
			ts[i].start();
		}
		
		for (int i=0; i<NTHREADS; i++) {
			try {
				ts[i].join();
			} catch (InterruptedException e) {
				// Class code : no exceptions
			}
		}
		return l;
	}
	

}
