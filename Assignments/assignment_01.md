Assignment01 
============

A Genetic Algorithm for The Knapsack Problem
===============================================

The Knapsack Problem
--------------------

Given a set of _n_ objects, each having a value _v_ and a weight _w_, we want to find the set of _k_ objects that maximize the total value, but has a total weight under _MAX_WEIGHT_.

Given the [complexity of the problem](https://en.wikipedia.org/wiki/Knapsack_problem#Computational_complexity), we will use an heuristic method to find a _good enough_ solution.

Genetic Algorithm
-----------------

The genetic algorithm for the Knapsack uses an array of booleans as the genotype. The algorithm's pseudocode is as follows:

```python
population = createRandomPopulation()

while (gen < NGENERATIONS):
  evaluateFitness(population)
  population = sortPopulationByDecreasingFitness(population)
  population = performCrossOver(population)
  population.mutate()
  
best = population[0]
```

Population is an array of individuals. Each individual is represented by its genotype, and has a fitness (a boolean representing -extraWeight if the totalWeight is higher than the acceptable limit, or +totalValue otherwise ). 

Each generation, the fitness is calculated for each individual. The order in the population array will be used when selecting parents to mate in the CrossOver. Given two random parents (with higher fitness individuals being more probable), the child will have the first [0..x] elements from parent 1 and [x..n] elements from the other parent. The crossover point _x_ is randomly generated between 0 and _n_ (the number of genes). The best element of a generation always remains to next generation, to have a monotonically increasing fitness function in regards to the generation.

There is also a chance that individuals can be mutated. Mutations bit-flip one gene at a given random point in their gene.

After _NGEN_ generations, the process stops, and we have the best so far.


The Task
--------

Parallelize the provided code where its possible.

You can rewrite the code as much as you want. 
 
You can use Threads, ForkJoin or Streams, but...

*Important:* You have to hand-write a parallel sorting algorithm ([MergeSort](https://en.wikipedia.org/wiki/Merge_sort#Parallel_merge_sort), [QuickSort](https://en.wikipedia.org/wiki/Quicksort#Parallelization) or other).

Write a short report on the work done (details below).


The N-Body Problem
==================

The N-Body simulation provided simulates N-bodies in space, initialized with random coordinates (x,y,z), velocities (vx, vy, vz) and mass.

The Task
--------

Parallelize the advance method on the NBodySystem class. The method has a quadratic complexity on the number of bodies.

This method applies the gravity force to all pairs of bodies, as well as advances the bodies according to their current velocity.

Submission Instructions
=======================

You should submit your assignment via email

```
To: docentes-pcm@listas.di.ciencias.ulisboa.pt
Subject: Assignment_1
```

You should attach a zip file with your code and a *PDF* or *txt* report.

The report is as important as the working code — This is a Masters-level course after all! — and will be evaluated as such. You report should include the following:

* description of how parallelization was applied
* rationale for the parallelization method used
* measurements showing whether parallelization was advantageous in each case

The report should not exceed one A4 page.

*DEADLINE:* October 30th, 2019 AOE




