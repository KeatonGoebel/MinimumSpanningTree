# MinimumSpanningTree
Minimum Spanning Tree is a Java implementation of Dijkstra's Algorithm to find the shortest path between different points in a weighted graph. I wrote it in the fall semester of my senior year at Transylvania University. Dijkstra's algorithm is often used for things such as optimizing flight paths, GPS systems, or supply chains. This implementation of the algorithm returns a tree of results representing different paths, optimized to always have the minimum total distance. It returns both the tree of results in a readable format and the total distance. 
## How does MinimumSpanningTree Work?
Minimum Spanning Tree takes in an input file and a root. Then, it creates a vertex object where each unique point in the input file is assigned an appropriate parent and key value. An edge object is also created from the input file with a starting point, an ending point, and the distance between them. MinimumSpanningTree then goes through an algorithm where each vertex object is compared against all of the other vertices it is connected to and its parent and key values are changed appropriately. Once the algorithm is done, the resulting parent values of the vertex objects create the MinimumSpanningTree. Users can change the root to see different optimized patterns through the graph based on different starting points. Users can also input the keyword "Verbose" **after** the input file and the root. Inputting Verbose will cause the program to print the parent values and key values as they change so that the user can track them through the algorithm. Inputting Verbose also causes the program to print the distances between each point.  
### MinimumSpanningTree Example Inputs and Outputs
MinimumSpanningTree is designed to read input files with a particular format: starting point, ending point, and distance. I have included two graph files for testing. Here are some examples of different inputs and outputs from the program. 

Input File: 
```
a b 16
a c 34
a d 5
a e 24 
b e 12
b c 10
c d 77
c e 28
```
Input From User:
```
java MinimumSpanningTree graph1.in a
```
Output:
```
The Minimum Spanning Tree is
( a (b (c) (e) ) (d) )
The Total weight of the Tree is 43
```
