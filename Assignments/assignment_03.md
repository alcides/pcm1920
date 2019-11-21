Assignment03 
============

Binary Tree in Actors
========================

The Task
--------

Write a Binary Tree implementation that uses actors. The goal is to distribute the load to several actors, which might result in a parallel execution.

Idea: Each tree node should be represented as an actor. Actors send messages to child elements to perform operations (insert, remove, contains). The client actor should ask the root element for operations (you should use random numbers) and receive answers (as messages!) in the same order.

Finally, make sure to garbage collect your actors. Remember running actors are not garbage collected.

You are free to use any actor library (your own, Akka, Erlang, Pony, etc).


Submission Instructions
=======================

You should submit your assignment via email

```
To: docentes-pcm@listas.di.ciencias.ulisboa.pt
Subject: Assignment_3
```

You should attach a zip file with your code and a *PDF* or *txt* report.

The report is as important as the working code — This is a Masters-level course after all! — and will be evaluated as such. You report should include the following:

* Description of the actor architecture.
* Detail on operation implementation and garbage collection.

The report should not exceed half A4 page.

*DEADLINE:* December 11th, 2018 AOE




