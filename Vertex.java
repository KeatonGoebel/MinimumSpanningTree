// FILE: Vertex.java
// Keaton Goebel, Transy University
// Fall 2023
//
// Implentation of the Vertex Class

import java.io.*;
import java.util.ArrayList;

public class Vertex{

	private String point;
	private int key;
	private String parent;

	// Constructor either creates empty Vertex object or intializes private variables 

	public Vertex(){
	}

	public Vertex(String poi, int k, String par){
		point = poi;
		key = k;
		parent = par;
	}

	// Accessors for point, key, and parent

	public String getPoint(){
		return point;
	}

	public int getKey(){
		return key;
	}

	public String getParent(){
		return parent;
	}

	// Mutators for parent and key 

	public void changeKey(int newKey){
		key = newKey;
	}

	public void changeParent(String newParent){
		parent = newParent;
	}

}