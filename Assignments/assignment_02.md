Assignment02
============

Floyd-Warshall algorithm
========================

The Floyd–Warshall algorithm is an algorithm for finding all shortest paths in a positively weighted graph. While Dijkstra or A* compute the path between two nodes, or in some extensions between one node and all other nodes, the Floyd-Warshall computes all possible shortest paths at once.

The algorithm receives as input an distance matrix M where M[i,j] represents the distance between nodes i and j.


The algorithm can be written in Python as:

```python
for k in range(0, N):
	for i in range(0, N):
		for j in range(0, N):
			M[i][j] = min(M[i][j], M[i][k] + M[k][j])
```

The Task
--------

Write a sequential CPU and a parallel GPU version. You can use any language as long as it will execute the algorihtm on the GPU.


Submission Instructions
=======================

You should submit your assignment via email

```
To: docentes-pcm@listas.di.ciencias.ulisboa.pt
Subject: Assignment_2
```

You should attach a zip file with your code and a *PDF* or *txt* report.

The report is as important as the working code — This is a Masters-level course after all! — and will be evaluated as such. You report should include the following:

* Description of the necessary modifications for GPU-execution
* Rationale of the choice of parameters for GPU-execution (blocks, threads) and memory usage or transfers.

The report should not exceed one A4 page.

*DEADLINE:* November 27th, 2018 AOE




