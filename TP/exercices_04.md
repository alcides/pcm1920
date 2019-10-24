Ex04 Parallel Streams
=====================

1. Re-Re-revisiting trapezoids
------------------------------

Write a program that estimates the integral of a given function f, using the trapezoid rule.

Your function should receive:

1. The function (You can use f x = x * (x-1), but it should work for any function)
2. The lower bound of the integral (0.0)
3. The upper bound of the integral (1.0)
4. The resolution  (10^-7)

The result should be a float approximating with the integral of that function, 0.1(6) in that example.

- Write a sequential version of the program. __(done in Ex01)__
- Write a parallel version of the program using a fixed number of threads __(done in Ex01)__
- Write a Fork Join version of the program __(done in Ex02)__
- Write a Fork Join version of the program that dynamically subdivides the problem until abs( ( area(p1) + area(p2) ) - area(p1+p2))) < 10^-7 __(done in Ex03)__
- Add a granularity control mechanism to your program. __(done in Ex03)__
- Write the first version of the program (resolution as the number of trapezoids) using Streams, both sequentially and in parallel.


2. Revisiting MonteCarlo to find pi.
------------------------------------

Write a program that estimates the value of PI using a Monte Carlo simulation.

Consider a circle with radius 1 centered at the origin of a square defined by the opposing vertices (-1,-1) and (1,1).

By throwing randomly darts inside the square (following a uniform distribution), it is possible to obtain the ratio of darts that fell inside the circle and the total number of darts. From this ratio, you should derive pi.

- Write a sequential version of the program.  __(done in Ex01)__
- Write a parallel version of the program using a fixed number of threads  __(done in Ex01)__
- Find the ideal chunk size for your machine  __(done in Ex01)__
- Write sequential and parallel versions using the Java Stream API
- Write a new version of this example, where two threads generate the random 2D points, and a third thread that receives the points and calculates the approximation of PI.


3. Finally, a new hot problem!
------------------------------

The Heat benchmark is the classic stencil example, where cells in a matrix are updated according to their neighbor cells during several synchronized iterations.

We present a simplification of the example, where the value of a cell at iteration _i+1_ is the average of the values in iteration _i_ of that same cell and its four neighbors.

```java

public class Heat {

	public static final int NX = 2048;
	public static final int NY = 2048;
	public static final int ITERATIONS = 50;
	
	public static void main(String[] args) {
		double[][] oldm = new double[NX][NY];
		double[][] newm = new double[NX][NY];
		
		// Everything else is 0
		oldm[NX/2][NY/2] = 100000;
		
		for (int timestep = 0; timestep <= ITERATIONS; timestep++) {
			
			for (int i=1; i< NX-1; i++) {
				for (int j=1; j< NY-1; j++) {
					double current = newm[i][j];
					newm[i][j] = (current + oldm[i-1][j] + oldm[i+1][j] + oldm[i][j-1] + oldm[i][j+1]) / 5; 
				}
			}
			
			// Swap matrices
			double[][] tmp = newm;
			newm = oldm;
			oldm = tmp;
		}
		
		System.out.println(newm[NX/2 - 10][NY/2 - 10]); // ~3.77
		
	}
}

````


* Write a parallel version of this problem that uses at most N threads (where N is the number of processors) and guaranteeing that the no thread advances to iteration _i+2_ when some other thread is still in iteration _i_. Make use of the class [Phaser](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Phaser.html).

* Write another parallel version using Futures to implement a Dataflow approach. Tasks/Futures should all be scheduled at the beginning, and the dependencies should be resolved during runtime.