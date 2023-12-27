// FILE: Graph.java
// Keaton Goebel, Transy University
// Fall 2023
//
// Implentation of the Graph Class

import java.io.*;
import java.util.ArrayList;

public class Graph{

	private ArrayList<Edge> graph;
	private String root;

	// Constructor: can either create an empty Graph object or a Graph object created from a 
	// string input file and a string representing the root

	public Graph(){
	}

	public Graph(String input, String root){

		ArrayList<Edge> partialGraph = new ArrayList<Edge>(); 

		try {

			FileReader file = new FileReader(input);
			StreamTokenizer inputStream = new StreamTokenizer(file);
			int tokenType = inputStream.nextToken();

			while(tokenType != StreamTokenizer.TT_EOF){
		
				String start = inputStream.sval;
				tokenType = inputStream.nextToken();
				String end = inputStream.sval;
				tokenType = inputStream.nextToken();
				int weight = (int) inputStream.nval;
				tokenType = inputStream.nextToken();
				Vertex startVertex, endVertex;

				// creating vertex objects for start and ending positions depending on if it is the root or not, and then creating 
				// an edge object from these vertex objects 

				if(start.equals(root)){
					startVertex = new Vertex(start, 0, "NIL");
				}
				else{
					startVertex = new Vertex(start, Integer.MAX_VALUE, root);
				}

				if(end.equals(root)){
					endVertex = new Vertex(end, 0, "NIL");
				}
				else{
					endVertex = new Vertex(end, Integer.MAX_VALUE, root);
				}

				Edge newEdge = new Edge(startVertex, endVertex, weight);
				partialGraph.add(newEdge);
			}
		
		} 
		catch(FileNotFoundException e){
			System.out.println("Error: File Not Found");
			System.exit(1);
		}
		catch(NullPointerException e){
			System.out.println("Error: Invalid Data Inputted");
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("Error: IOException Found");
			System.exit(1);
		}
		graph = partialGraph;
	} 

	// Accessor Function to return the graph private variable

	public ArrayList<Edge> getEdgeList(){
		return graph;
	}

	// member function returns true if the element exists in a list of Vertexes

	public boolean member(String element, ArrayList<Vertex> Vertexes){

		for(int i = 0; i < Vertexes.size(); i++){
			if(Vertexes.get(i).getPoint().equals(element)){
				return true;
			}
		}
		return false;
	}

	// takes in a list of edges and two Vertexes and returns the weight between the start and end Vertexes
	// if weight does not exist, returns -1

	public int getWeight(ArrayList<Edge> graph, Vertex v1, Vertex v2){

		for(int i = 0; i < graph.size(); i++){

			// if the graphs start is equal to v1 and the graphs end is equal to v2 or vice versa, returns weight

			if((graph.get(i).getStart().getPoint().equals(v1.getPoint())) 
				&& (graph.get(i).getEnd().getPoint().equals(v2.getPoint()))){
				return graph.get(i).getWeight();
			}
			if((graph.get(i).getStart().getPoint().equals(v2.getPoint())) 
				&& (graph.get(i).getEnd().getPoint().equals(v1.getPoint()))){
				return graph.get(i).getWeight();
			}
		}
		return -1;
	}

	// getVertexes returns a list of vertexes with regard to uniqueness

	public ArrayList<Vertex> getVertexes(){

		ArrayList<Vertex> vertList = new ArrayList<Vertex>();
		for(int i = 0; i < graph.size(); i++){

			// if graph start point or graph end point is not already in vertList, then add to vertList

			if(!member(graph.get(i).getStart().getPoint(), vertList)){
				vertList.add(graph.get(i).getStart());
			}
			if(!member(graph.get(i).getEnd().getPoint(), vertList)){
				vertList.add(graph.get(i).getEnd());
			}
		}
		return vertList;

	}

	// getMin takes in an arrayList of Vertexes and returns the minimal key Vertex

	public Vertex getMinVert(ArrayList<Vertex> vertList){

		Vertex minVert = vertList.get(0);
		for(int i = 0; i < vertList.size(); i++){

			if(vertList.get(i).getKey() < minVert.getKey()){
				minVert = vertList.get(i);
			}
		}
		return minVert;
	}

	// removeMin takes in an ArrayList of vertexes, removes the vertex with the minimal key, and returns the changed list

	public ArrayList<Vertex> removeMin(ArrayList<Vertex> vertList){

		Vertex minVert = getMinVert(vertList);
		for(int i = 0; i < vertList.size(); i++){
			if(vertList.get(i).getPoint().equals(minVert.getPoint())){
				vertList.remove(i);
				break;
			}
		}
		return vertList;
	}

	// getConnList takes in a string of a root and returns an ArrayList of connections to the root

	public ArrayList<Vertex> getConnList(String root){

		ArrayList<Vertex> connList = new ArrayList<Vertex>();
		for(int i = 0; i < graph.size(); i++){

			// if graph start equals root and graph end is not already in connList or vice versa, add graph end to connList 

			if((graph.get(i).getStart().getPoint().equals(root)) 
				&& (!member(graph.get(i).getEnd().getPoint(), connList))){
				connList.add(graph.get(i).getEnd());
			}
			if((graph.get(i).getEnd().getPoint().equals(root)) 
				&& (!member(graph.get(i).getEnd().getPoint(), connList))){
				connList.add(graph.get(i).getStart());
			}
		}
		return connList;

	}

	// getChangedGraph takes in a string representing the root and returns a Graph object where if the connList element is in the vertex List
	// and the weight between the minimum key and the connList element is less than the key of the connList element, then it changes the parent 
	// and the key of every element of the graph for which this applies

	public Graph getChangedGraph(String root, Boolean verbose){

		if(verbose){
			System.out.println("Entering getChangedGraph Function");
		}

		ArrayList<Edge> changedEdges = getEdgeList();
		ArrayList<Vertex> vertList = getVertexes();

		// intializing minVert, setting it to the minimum key Vertex, removing it from the vertex List, and building a connList from that minVert point 

		Vertex minVert = null;
		for(int i = 0; i < vertList.size(); i++){
			if(vertList.get(i).getPoint().equals(root)){
				minVert = vertList.get(i);
				break;
			}
		}
		removeMin(vertList);
		ArrayList<Vertex> connList = getConnList(minVert.getPoint());

		while(!vertList.isEmpty()){

			for(int i = 0; i < connList.size(); i++ ){
				int verboseCounter = 0;

				// checking if weight between minVert and connlist element is less than connList element key and if connList element is currently in vertList

				int connKey = connList.get(i).getKey();
				int currentWeight = getWeight(changedEdges, minVert, connList.get(i));
				if((member(connList.get(i).getPoint(), vertList)) && (currentWeight < connKey)){

					for(int j = 0; j < changedEdges.size(); j++){

						// Now for every graph element equal to connList element, changing connList element parent to minVert point and
						// connList element key to weight between connList element and minVert

						if(changedEdges.get(j).getStart().getPoint().equals(connList.get(i).getPoint())){

							if(verbose){
								System.out.println("Graph Element " + changedEdges.get(j).getStart().getPoint() + ":" + verboseCounter + "'s Key was " + changedEdges.get(j).getStart().getKey() + "\n");
								System.out.println("Graph Element " + changedEdges.get(j).getStart().getPoint() +  ":" + verboseCounter + "'s Parent was " + changedEdges.get(j).getStart().getParent() + "\n");
							}

							changedEdges.get(j).getStart().changeKey(currentWeight);
							changedEdges.get(j).getStart().changeParent(minVert.getPoint());

							if(verbose){
								System.out.println("Graph Element " + changedEdges.get(j).getStart().getPoint() +  ":" + verboseCounter + "'s Key is now " + changedEdges.get(j).getStart().getKey() + "\n");
								System.out.println("Graph Element " + changedEdges.get(j).getStart().getPoint() +  ":" + verboseCounter + "'s Parent is now " + changedEdges.get(j).getStart().getParent() + "\n");
								verboseCounter ++;
							}

						}
						if(changedEdges.get(j).getEnd().getPoint().equals(connList.get(i).getPoint())){

							if(verbose){
								System.out.println("Graph Element " + changedEdges.get(j).getEnd().getPoint() +  ":" + verboseCounter + "'s Key was " + changedEdges.get(j).getEnd().getKey() + "\n");
								System.out.println("Graph Element " + changedEdges.get(j).getEnd().getPoint() +  ":" + verboseCounter + "'s Parent was " + changedEdges.get(j).getEnd().getParent() + "\n");
							}

							changedEdges.get(j).getEnd().changeKey(currentWeight);
							changedEdges.get(j).getEnd().changeParent(minVert.getPoint());

							if(verbose){
								System.out.println("Graph Element " + changedEdges.get(j).getEnd().getPoint() +  ":" + verboseCounter + "'s Key is now " + changedEdges.get(j).getEnd().getKey() + "\n");
								System.out.println("Graph Element " + changedEdges.get(j).getEnd().getPoint() +  ":" + verboseCounter + "'s Parent is now " + changedEdges.get(j).getEnd().getParent() + "\n");
								verboseCounter ++;
							}

						}
					}
				}
				verboseCounter = 0;				
			}

			minVert = getMinVert(vertList);	
			vertList = removeMin(vertList);
			connList = getConnList(minVert.getPoint());	
		}

		if(verbose){
			System.out.println("Leaving getChangedGraph Function");
		}

		Graph changedGraph = new Graph(changedEdges, root);
		return changedGraph;
	}

	// Private constructor to be used to create a new Graph object in the getChangedGraph function

	private Graph(ArrayList<Edge> g, String r){
		graph = g;
		root = r;

	}
}