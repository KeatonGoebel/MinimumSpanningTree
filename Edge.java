// FILE: Edge.java
// Keaton Goebel, Transy University
// Fall 2023
//
// Implentation of the Edge Class

import java.io.*;
import java.util.ArrayList;

public class Edge{

	private Vertex start;
	private Vertex end;
	private int weight;

	// Constructor either creates an empty Edge object or intializes private variables 

	public Edge(){
	}

	public Edge(Vertex s, Vertex e, int w){
		start = s;
		end = e;
		weight = w;
	}

	// Accessors for start, end, and weight

	public Vertex getStart(){
		return start;
	}

	public Vertex getEnd(){
		return end;
	}

	public int getWeight(){
		return weight;
	}

}