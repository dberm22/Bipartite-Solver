# Bipartite-Solver
Presenting 4 different algorithms to solve bipartite graphs:

What are Bipartite Graphs?
Suppose you run a taxi service and own 10 cabs scattered across the city. Your phone is 
ringing off the hook, and you have 10 people waiting for a taxi, all in different parts 
of the city. The goal is to match all of your cars to customers while minimizing the total 
distance travelled, and therefore cost. This is a bipartite graph.

Presented in this library are four different ways to solve the above problem.
From Slowest to fastest, we have:

Naive Brute Force Exhaustive Search - this is where we compute every permutation of taxis to 
     customer and choose the one that minimizes our total cost. This is fine with anything
     less that 8 cars or customers, but explodes (both in time and memory) once you surpass 9
     or 10, and start to run out of memory almost instantly. While it does guarantee optimality, 
     this is in no way optimized to solve it efficiently.
     
Alpha-Beta Pruning Search- This is a variant of exhaustive search, but performs slightly better.
     It starts to build a permutation tree, but only expands nodes that have a possibility of
     producing a more optimal result than the current state. Whereas the naive search will
     expand every node, regardless of value, alpha-beta pruning checks against the current cost
     before expanding each node. While it guarantees optimality, and is guaranteed to be
     atleast as fast, if not faster, than naive search, on average, we only see a 20% increase
     in speed. Do not use this for anything more than 12 or 13 elements. You will run out of
     memory.
     
Jonker-Volgenant (aka LAPJV) - Here is the bread and butter. Guaranteed to provide an optimal
     solution, and FAST! Using linear algebra to row-reduce the cost matrix, then applying
     Dijkstra's algorithm to iterate through the solutions, we are able to solve a 1000x1000
     problem in less than 1s! Truly incredible. If optimality is required, and speed is
     important (when isn't it?), this is the best solution, anywhere on the web!
     
Greedy Search : The fastest of all. While it does NOT guarantee optimality, it does do a fairly
     decent job at giving a near-optimal solution. If speed is the driving factor, you have a huge
     number of elements (>10000), and optimality is not required, greedy search is a viable option.


Each algorithm can be run (and plotted) individually by running the Run*.java in each of the
subdirectories. For example, if you wish to try out lapjv, try running RunLAP.java. You can edit
some variables in the file if you wish to play around with the number of elements, or even the 
cost function.

There is also a Main.java included which runs a speed test and saves the output to a csv which
can then be imported into excel and plotted. Maybe a handfull of people will find this useful.

Even easier, the examples can be run using the executables in the bin directory. Simply click the
executable you wish to run, and a window should pop up with the results. If you want to try
a different number of elements than default, you can run the exe in command prompt and supply
extra arguments. A single digit argument will map N elements to N elements, while two arguments
will map N elements to M elements. N can be greater than, equal to, or less than M.
