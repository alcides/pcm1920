Ex02 Divide and Conquer Parallel Programs
=========================================

1. Revisiting trapezoids
------------------------

Write a program that estimates the integral of a given function f, using the trapezoid rule.

Your function should receive:

1. The function (You can use f x = x * (x-1), but it should work for any function)
2. The lower bound of the integral (0.0)
3. The upper bound of the integral (1.0)
4. The resolution  (10^-7)

The result should be a float approximating with the integral of that function, 0.1(6) in that example.

- Write a sequential version of the program. __(done in Ex01)__
- Write a parallel version of the program using a fixed number of threads __(done in Ex01)__
- Write a Fork Join version of the program