import java.util.stream.IntStream;

public class Sum {
	static final double errorTolerance = 1.0e-4;
	static double start = 0;
    static double end = 100;
    
	// the function to integrate
    static double computeFunction(double x)  {
        return (x * x + 1.0) * x;
    }
	public static void main(String[] args) throws Exception {

		int ITERATIONS = (int) ((end - start) / errorTolerance);
		
		
		// Parallel streams need to implement spliterator!
		
		double sumOfSquares = IntStream.range(1, ITERATIONS)
								.parallel()
								//.filter((int i) -> i % 2 == 0) 
								.mapToDouble((int i) -> {
									double d = i * (1.0 / ITERATIONS);
									double h = errorTolerance;
									double b = computeFunction(d);
									double B = computeFunction(d + errorTolerance);
									return (b + B) * h / 2;
								})
								.sum();
								//.reduce(0, (double x, double y) -> x+y);
		
		System.out.println(sumOfSquares);
	}
}
