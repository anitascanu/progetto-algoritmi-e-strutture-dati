package graphusage;

/**
 * @author Anita Scanu
 */

import java.util.Comparator;
import graph.Graph;
import graph.GraphException;
import java.util.ArrayList;


public class GraphUsage {
  public static void main(String[] args) throws GraphException {
    System.out.println("Creating a undirected graph...");
    Graph<Integer, Integer> g = new Graph<>(new IntegerComparator(), false);
    System.out.println("Adding 5 to the graph...");
    g.addNode(5);
    System.out.println("Adding 4 to the graph...");
    g.addNode(4);
    System.out.println("Adding 6 to the graph...");
    g.addNode(6);
    System.out.println("Adding 1 to the graph...");
    g.addNode(1);
    System.out.println("Adding 2 to the graph...");
    g.addNode(2);
    System.out.println("Adding 9 to the graph...");
    g.addNode(9);
    System.out.println("Adding the edge (9,6) with weight 3 to the graph...");
    g.addEdge(9,6,3);
    System.out.println("Adding the edge (1,2) with weight 1 to the graph...");
    g.addEdge(1,2,1);
    System.out.println("Adding the edge (2,6) with weight 9 to the graph...");
    g.addEdge(2,6,9);
    System.out.println();
    System.out.println();

    g.printGraph();
    System.out.println();
    System.out.println();
    
    System.out.print("Does the graph contains the node 9?   ");
    System.out.println(g.containsNode(9));
    System.out.print("Does the graph contains the node 12?   ");
    System.out.println(g.containsNode(12));

    System.out.print("Does the graph contains the edge (9,6)?   ");
    System.out.println(g.containsEdge(9,6));
    System.out.print("Does the graph contains the edge (6,2)?   ");
    System.out.println(g.containsEdge(6,2));
    System.out.print("Does the graph contains the edge (2,6)?   ");
    System.out.println(g.containsEdge(2,6));
    System.out.print("Does the graph contains the edge (1,5)?   ");
    System.out.println(g.containsEdge(1,5));

    System.out.print("How many vertex in the graph?   ");
    System.out.println(g.numNodes());
    System.out.print("How many edges in the graph?   ");
    System.out.println(g.numEdges());
    System.out.println();
    System.out.println();

    ArrayList<Integer> n = g.getNodes();
    System.out.println("Nodes:");
    System.out.println(n);
    System.out.println();
    System.out.println();

    ArrayList<Graph<Integer,Integer>.Edge<Integer,Integer>> e = g.getEdges();
    System.out.println("Edges:");
    System.out.println(e);
    System.out.println();
    System.out.println();

    n = g.adjacenceOfNode(6);
    System.out.println("Adjacence list of node 6:");
    System.out.println(n);
    System.out.println();
    System.out.println();

    Integer w = g.getWeightOf(9,6);
    System.out.println("Weight of 9-6:   " + w);
    w = g.getWeightOf(1,2);
    System.out.println("Weight of 1-2:   " + w);
    System.out.println();
    System.out.println();

    System.out.println("Deleting the node 6...");
    g.deleteNode(6);
    System.out.println();
    System.out.println();

    g.printGraph();
    System.out.println();
    System.out.println();
    
    System.out.println("Deleting the edge (1,2)...");
    g.deleteEdge(1,2);
    System.out.println();
    System.out.println();

    g.printGraph();
    System.out.println();
    System.out.println();
 
    n = g.getNodes();
    System.out.println("Nodes:");
    System.out.println(n);
    System.out.println();
    System.out.println();

    System.out.println("The graph is directed?   " + g.isDirected());
    System.out.println();
    System.out.println();

    System.out.println("............................................");
    System.out.println();
    System.out.println();

    System.out.println("Creating a directed graph...");
    g = new Graph<>(new IntegerComparator(), true);
    System.out.println("Adding 5 to the graph...");
    g.addNode(5);
    System.out.println("Adding 4 to the graph...");
    g.addNode(4);
    System.out.println("Adding 6 to the graph...");
    g.addNode(6);
    System.out.println("Adding 1 to the graph...");
    g.addNode(1);
    System.out.println("Adding 2 to the graph...");
    g.addNode(2);
    System.out.println("Adding 9 to the graph...");
    g.addNode(9);
    System.out.println("Adding the edge (9,6) with weight 3 to the graph...");
    g.addEdge(9,6,3);
    System.out.println("Adding the edge (1,2) with weight 1 to the graph...");
    g.addEdge(1,2,1);
    System.out.println("Adding the edge (2,6) with weight 9 to the graph...");
    g.addEdge(2,6,9);
    System.out.println();
    System.out.println();

    g.printGraph();
    System.out.println();
    System.out.println();

    System.out.print("Does the graph contains the edge (9,6)?   ");
    System.out.println(g.containsEdge(9,6));
    System.out.print("Does the graph contains the edge (6,2)?   ");
    System.out.println(g.containsEdge(6,2));
    System.out.print("Does the graph contains the edge (2,6)?   ");
    System.out.println(g.containsEdge(2,6));
    System.out.print("Does the graph contains the edge (1,5)?   ");
    System.out.println(g.containsEdge(1,5));

    System.out.print("How many vertex in the graph?   ");
    System.out.println(g.numNodes());
    System.out.print("How many edges in the graph?   ");
    System.out.println(g.numEdges());
    System.out.println();
    System.out.println();

    n = g.adjacenceOfNode(6);
    System.out.println("Adjacence list of node 6:");
    System.out.println(n);
    System.out.println();
    System.out.println();

    System.out.println("Deleting the node 6...");
    g.deleteNode(6);
    System.out.println();
    System.out.println();

    g.printGraph();
    System.out.println();
    System.out.println();
    
    System.out.println("Deleting the edge (1,2)...");
    g.deleteEdge(1,2);
    System.out.println();
    System.out.println();

    g.printGraph();
    System.out.println();
    System.out.println();
 
    System.out.println("The graph is directed?   " + g.isDirected());
    System.out.println();
    System.out.println();
  }
}

