import java.util.stream.DoubleStream;
import java.util.concurrent.ThreadLocalRandom;

public class Example {
	
	public static int SAMPLES = 100000000;
	
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Wrong number of arguments. Exiting...");
			System.exit(-1);
		}
		boolean sequential = args[0].equals("-s");
		long seed = Long.parseLong(args[1]);
		
		
		long initTime = System.nanoTime();
		double result = -1.0;
		if (sequential) {
			result = Example.sequentialVersion(SAMPLES, seed);
		} else {
			result = Example.parallelVersion(SAMPLES, seed);
		}
		long finishTime = System.nanoTime();
		long time = (finishTime - initTime);
		assert(Math.abs(result - 3.14) <= 0.01);
		System.out.println(sequential + ";" + time);
	}
	
	
	public static double sequentialVersion(int samples, double seed) {
	    long inCircle = DoubleStream.generate(
	      () -> Math.hypot(ThreadLocalRandom.current().nextDouble(1.0), ThreadLocalRandom.current().nextDouble(1.0))
	    )
	      .limit(samples)
	      .unordered()
	      .filter(d -> d < 1)
	      .count()
	    ;
	    return (4.0 * inCircle) / samples;
   }
   
public static double parallelVersion(int samples, double seed) {
    long inCircle = DoubleStream.generate(
      () -> Math.hypot(ThreadLocalRandom.current().nextDouble(1.0), ThreadLocalRandom.current().nextDouble(1.0))
    )
      .limit(samples)
      .unordered()
      .parallel()
      .filter(d -> d < 1)
      .count()
    ;
    return (4.0 * inCircle) / samples;
  }
}