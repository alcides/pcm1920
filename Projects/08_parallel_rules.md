P8 Parallel Rules classifier
============================

Write a machine learning classifier that performs training in parallel.

That classifier randomly generates a rule-system with several rules that follow this format:

if features[A] < T1 and features[B] < T2 and features[C] < T3 then classA

where A, B and C are features names, T1, T2 and T3 are random floats and classA, classB and so on are different classes.

Evaluate this classifier against other state of the art classifiers, both in performance and time.