Ex05 Making use of GPU power!
=============================

Make sure you have access to a GPU!

If not, ask for an account on franklin!

1. Find local minima!
---------------------

Given an array A of size N, you are to create an array R of size N where R[i] indicates whether A[i] is a local minimum. A[i] is a local minima if 0 < i < N-1 and A[i-1] > A[i] and A[i] < A[i+1].

* Array A should be randomly initiated on the CPU side.
* In which GPU memory is array A in?
* Try to make use of shared memory to improve computation. 
* Try to reduce the memory allocation on the GPU (this will allow bigger arrays)
* Try to change the number of groups/threads to see if performance improves.
* Do you need barrier synchronization? Why?


2. Revisiting the hot problem!
------------------------------

Now try to implement the Heat program (Ex04) on the GPU. If should not be more difficult than local minima conceptually.

* Implement a kernel that performs one iteration at a time.
* Implement just one kernel that performs all iterations. Check for consistency!