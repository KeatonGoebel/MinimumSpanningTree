// FILE: MinimumSpanningTree.java
// Keaton Goebel, Transy University
// Fall 2023
//
// Minimum Spanning Tree Driver Program to output the MST and the Total Weight to the screen.
// Entering Verbose prints the vertex elements in the graph, their keys, and their parents as they are changed
// throughout the changeGraph function. getChangedGraph is called once by MST, and again by getTotalWeight so this 
// Data is printed twice. During the getTotalWeight function, Verbose also prints the minimal weights of each individual Vertex.
// The Tree class is also inherited from the Graph class because a tree is a type of graph. In order to execute this program, input 
// java MinimumSpanningTree <inputFile> <root> <optionalVerbose>

import java.io.*;
import java.util.ArrayList;

public class MinimumSpanningTree{
	
	public static void main(String[] args){

		Boolean verbose = false;
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("Verbose")){
				verbose = true;
			}
		}

		// Case where the user inputs only file name, and root

		Boolean correctInput = false;
		if((args.length == 2) && (verbose == false)){
			correctInput = true;
		}

		// Case where the user inputs file name, root, and Verbose  

		if((args.length == 3) && (verbose)){
			correctInput = true;
		}

		if(!correctInput){
			System.out.println("Error: Please provide MinimumSpanningTree Class, file name and root. You may also input Verbose, but that is optional");
			System.exit(1);
		}

		String file = args[0];
		String root = args[1];

		// Testing if the root exists in the graph

		Boolean rootExist = false;
		Tree tree = new Tree(file, root);
		ArrayList<Vertex> vertList = tree.getVertexes();
		for(int i = 0; i < vertList.size(); i ++){
			if(vertList.get(i).getPoint().equals(root)){
				rootExist = true;
				break;
			}
		}
		if(rootExist == false){
			System.out.println("Error: Please Ensure the root exists in the Graph");
			System.exit(1);
		}

		// printing Minimum Spanning Tree and Total Weight

		tree.MST(file, root, verbose);

		// Fixing akward line spacing due to verbose print statements 

		if(!verbose){
			System.out.print("\n");
		}

		tree.getTotalWeight(file, root, verbose);

	}
}