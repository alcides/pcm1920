Ex01 Embarrassingly Parallel Programming
========================================

1. Multiplying matrices
--------------------------------

Write a program that receives two matrices of compatible sizes ([MxN] and [NxO]) and returns a new matrix resulting from the multiplication of the other two.  

- Write a sequential version of the program.
- Write a parallel version of the program using a fixed number of threads
- Find the ideal chunk size for your machine


2. Gathering trapezoids together
--------------------------------

Write a program that estimates the integral of a given function f, using the trapezoid rule.

Your function should receive:

1. The function (You can use f x = x * (x-1), but it should work for any function)
2. The lower bound of the integral (0.0)
3. The upper bound of the integral (1.0)
4. The resolution  (10^-7)

The result should be a float approximating with the integral of that function, 0.1(6) in that example.

- Write a sequential version of the program.
- Write a parallel version of the program using a fixed number of threads
- Find the ideal chunk size for your machine


3. Throwing darts in MonteCarlo to find pi.
-------------------------------------------

Write a program that estimates the value of PI using a Monte Carlo simulation.

Consider a circle with radius 1 centered at the origin of a square defined by the opposing vertices (-1,-1) and (1,1).

By throwing randomly darts inside the square (following a uniform distribution), it is possible to obtain the ratio of darts that fell inside the circle and the total number of darts. From this ratio, you should derive pi.

- Write a sequential version of the program.
- Write a parallel version of the program using a fixed number of threads
- Find the ideal chunk size for your machine


4. Refactor, refactor, refactor
-------------------------------

Look at your previous solutions. Surely you must have used copy&paste!

- Write a library that can be used to write parallel programs.
- Rewrite the 3 programs using your library.
- (Bonus!) Design a method for automatically determine the chunk size.
