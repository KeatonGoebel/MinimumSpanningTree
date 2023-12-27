// FILE: Tree.java
// Keaton Goebel, Transy University
// Fall 2023
//
// Implentation of the Tree Class

import java.io.*;
import java.util.ArrayList;

public class Tree extends Graph{

	// Constructor can either create an empty Tree object or a Tree object that read in parameters 
	// and sends them to the constructor of the Graph class

	public Tree(){
	}

	public Tree(String input, String root){
		super(input, root);
	}

	// MST function creates a graph from an input file and a root, manipulates the graph with the getChangedGraph function and calls printMST
	// private function to print the data to the screen 

	public void MST(String file, String root, Boolean verbose) {
    	Graph originalGraph = new Graph(file, root);
    	Graph changedGraph = originalGraph.getChangedGraph(root, verbose);
    	ArrayList<Vertex> vertList = changedGraph.getVertexes();
    	System.out.println("The Minimum Spanning Tree is");
    	printMST(root, vertList);

    	if(verbose){
    		System.out.println("\n");
    	}

	}

	// printMST is a function only accesible to MST which takes in a currentVertex and a Vertex list
	// and prints out every Vertex and their children

	private void printMST(String currentVertex, ArrayList<Vertex> vertList) {

    	System.out.print("( " + currentVertex);
    	for (int i = 0; i < vertList.size(); i++) {
    		
        	if (vertList.get(i).getParent().equals(currentVertex)) {
            	System.out.print(" ");
            	printMST(vertList.get(i).getPoint(), vertList);
        	}
    	}
    	System.out.print(" )");
	}

	// getTotalWeight takes in a string and a file, creates and manipulates a graph, and prints out the total weight of each Vertex.

	public void getTotalWeight(String file, String root, Boolean verbose){

		Graph originalGraph = new Graph(file, root);
		Graph changedGraph = originalGraph.getChangedGraph(root, verbose);
		ArrayList<Vertex> vertList = changedGraph.getVertexes();

		int totalWeight = 0;
		for(int i = 0; i < vertList.size(); i++){

			if(verbose){
				System.out.println("The Minimal Weight of Element " + vertList.get(i).getPoint() + " is " + vertList.get(i).getKey() + "\n");
			}

			totalWeight = totalWeight + vertList.get(i).getKey();

		}
		printTotalWeight(totalWeight);
	}

	// printTotalWeight is a function only accessible to getTotalWeight that outputs the results of getTotalWeight

	private void printTotalWeight(int totalWeight){
		System.out.println("The Total Weight of the Tree is " + totalWeight);
	}
}