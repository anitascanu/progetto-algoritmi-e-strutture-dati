package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;

/**
 * It represents a graph. The graph can be directed or undirected. The graph must be optimal for scattered data.
 * @author Anita Scanu
 * @param <T>: type of the graph elements
 * @param <S>: type of edge label
 */
public class Graph<T, S> {

  /**
   * It represents a node in the adjacency list of another node.
   * @author Anita Scanu
   * @param <T>: type of the node's value
   * @param <S>: type of edge label 
   */
  class Node<T,S>{
    T value;
    S weight;

    public Node(T value, S weight){
      this.value = value;
      this.weight = weight;
    }

    public T getValue(){
      return this.value;
    }

    public S getWeight(){
      return this.weight;
    }

    public String toString(){
      return this.value.toString();
    }
  }
  
  /**
   * It represents an edge in a graph.
   * @param <T>: type of the source and destination value
   * @param <S>: type of edge label 
   */
  public class Edge<T,S> {
    T source;
    T destination;
    S weight;

    /**
     * It creates a new edge.
     * @param source: the value of the source of the edge
     * @param destination: the value of the destination of the edge
     * @param weight: the weight of the edge
     */
    public Edge(T source, T destination, S weight){
      this.source = source;
      this.destination = destination;
      this.weight = weight;
    }

    /**
     * It returns the weight of an edge.
     * @return the weight of the edge
     */
    public S getWeight(){ return this.weight; }

    public String toString(){
      return "source: " + this.source.toString() + ", destination: " + this.destination.toString() + ", weight: " + this.weight.toString() + "\n";
    }
  }

  HashMap<T, ArrayList<Node<T,S>>> adjList = null; // associate at every value its adjacence list
  public Comparator<? super T> comparator = null;
  boolean isDirected; //true = directed, false = undirected

  /**
   * It creates an empty graph.
   * It accepts as input a comparator implementing the precedence relation between the elements.
   * @param comparator: a comparator implementing the precedence relation between the elements
   * @param isDirected: a boolean value, true means that the graph is directed, false means that the graph is undirected
   * @throws GraphException if the parameter is null
   */
  public Graph(Comparator<? super T> comparator, boolean isDirected) throws GraphException{
    if (comparator == null) throw new GraphException("Graph constructor: comparator parameter cannot be null");
    this.adjList = new HashMap<T, ArrayList<Node<T,S>>>();
    this.comparator = comparator;
    this.isDirected = isDirected;
  }

  /**
   * Addition of a node to the graph.
   * @param value: the value of the new vertex
   * @throws GraphException if the graph already contains that element
   */
  public void addNode(T value) throws GraphException{
    if(this.containsNode(value)) throw new GraphException("Graph addNode: this node already exists");
    this.adjList.putIfAbsent(value, new ArrayList<Node<T,S>>());
  }
 
  /**
   * Addition of an edge in the graph.
   * @param source: the vertex from which the edge starts
   * @param destination: the vertex where the edge arrives 
   * @param weight: the weight of the edge
   * @throws GraphException if the edge already exists
   */
  public void addEdge(T source, T destination, S weight) throws GraphException{
    if(this.containsEdge(source, destination)) throw new GraphException("Graph addEdge: this edge already exists");
    // add destination to the adjacence list of the source
    Node<T,S> destinationNode = new Node<T,S>(destination, weight);
    this.adjList.get(source).add(destinationNode);
    // if the graph is undirected add source to the adjacence list of the source
    if(!this.isDirected()){
      Node<T,S> sourceNode = new Node<T,S>(source, weight);
      this.adjList.get(destination).add(sourceNode);
    }
  }

  /**
   * Check if the graph is directed.
   * @return true if the graph is directed, false if is undirected
   */
  public boolean isDirected(){
    return this.isDirected;
  }

  /**
   * Check if the graph contains a certain node.
   * @param value: the value of the node to search for
   * @return true if the graph contains the node, false if the graph doesn't contain the node
   */
  public boolean containsNode(T value){
    return this.adjList.containsKey(value);
  }

  /**
   * Check if the graph contains a certain edge.
   * @param source: the node from which the edge starts
   * @param destination: the node where the edge arrives
   * @return true if the graph contains the edge, false if the graph doesn't contain the edge
   */
  public boolean containsEdge(T source, T destination){
    // if source or destination are not contained in the graph the edge cannot exists
    if(this.containsNode(source) == false || this.containsNode(destination) == false) return false;
    // for every node in the adjacence list of source
    for(int k = 0; k < this.adjList.get(source).size(); k++){
      // check if this node is equal to destination
      if(this.comparator.compare(this.adjList.get(source).get(k).getValue(), destination) == 0)
        return true;
    }
    return false;
  }

  /**
   * Returns the number of nodes in the graph.
   * @return the number of nodes in the graph
   */
  public int numNodes(){
    return this.adjList.size();
  }

  /**
   * Returns the number of edges in the graph.
   * @return the number of edges in the graph.
   */
  public int numEdges(){
    int numEdges = 0;
    // for all the adjacence lists of every node
    for (T key: this.adjList.keySet()){
      numEdges = numEdges + this.adjList.get(key).size();
		} 
    if(this.isDirected) return numEdges;
    else return numEdges/2;
  }

  /**
   * Delete a certain node in the graph.
   * @param value: the value of the node to delete
   * @throws GraphException if value is not a vertex in the graph
   */
  public void deleteNode(T value) throws GraphException{
    if(!this.containsNode(value)) throw new GraphException("Graph deleteNode: the node does not exist");
    if(this.adjList.containsKey(value)){
      // remove every edge if it has value as destination
      for(T key: this.adjList.keySet()){
        if(this.containsEdge(key, value))
          this.deleteEdge(key, value);
      }
      // remove the node
      this.adjList.remove(value);
    }
  }

  /**
   * Delete a certain edge in the graph.
   * @param source: the value of the node from which the edge starts
   * @param destination: the value of the node where the edge arrives
   * @throws GraphException if the edge does not exist
   */
  public void deleteEdge(T source, T destination) throws GraphException{
    if(!this.containsEdge(source,destination)) throw new GraphException("Graph deleteEdge: the edge does not exist");
    for(int k = 0; k < this.adjList.get(source).size(); k++){
      // remove destination from the adjacence list of source
      if(this.comparator.compare(this.adjList.get(source).get(k).getValue(), destination) == 0)
        this.adjList.get(source).remove(k);
    }
    // if the graph is undirected remove source from the adjacence list of destination
    if(!this.isDirected()){
      for(int k = 0; k < this.adjList.get(destination).size(); k++){
        // remove source from the adjacence list of destination
        if(this.comparator.compare(this.adjList.get(destination).get(k).getValue(), source) == 0)
          this.adjList.get(destination).remove(k);
      }
    }
  }

  /**
   * Return all the nodes of the graph.
   * @return an ArrayList with all the nodes of the graph
   */
  public ArrayList<T> getNodes(){
    ArrayList<T> nodes = new ArrayList<>();
    // for every vertex in the graph add it to the arraylist
    for(T key: this.adjList.keySet()){
      nodes.add(key);
    }
    return nodes;
  }

  /**
   * Return all edges of the graph.
   * @return an ArrayList with all the edges of the graph
   */
  public ArrayList<Edge<T,S>> getEdges(){
    ArrayList<Edge<T,S>> edges= new ArrayList<>();
    // for every vertex in the graph
    for(T key: this.adjList.keySet()){
      // for every node in the adjacence list of key (vertex)
      for(int j = 0; j < this.adjList.get(key).size(); j++){
        // create a new edge to add to the arraylist
        // the edge has key as source, the value in the adjacence list as destination and the weight of that edge as weight
        Edge<T,S> newEdge = new Edge<>(key, this.adjList.get(key).get(j).getValue(), this.adjList.get(key).get(j).getWeight());
        edges.add(newEdge);
      }
    }
    return edges;
  }

  /**
   * Return all the nodes in the adjacency list of a certain node.
   * @param value: the source node
   * @return an ArrayList with all the nodes in the adjacency list of the value node
   * @throws GraphException if value is not in the graph
   */
  public ArrayList<T> adjacenceOfNode(T value) throws GraphException{
    if(!this.containsNode(value)) throw new GraphException("Graph adjacenceOfNode: the node does not exist");
    ArrayList<T> nodes = new ArrayList<>();
    // for all the nodes in the adjacence list of value add them in the arraylist
    for(int k = 0; k < this.adjList.get(value).size(); k++){
      nodes.add(this.adjList.get(value).get(k).getValue());
    }
    return nodes;
  }

  /**
   * Get the weight of a certain edge.
   * @param source: the value of the node from which the edge starts
   * @param destination: the value of the node where the edge arrives
   * @return the weight of the edge
   * @throws GraphException if the edge is not contained in the graph
   */
  public S getWeightOf(T source, T destination) throws GraphException{
    if(!this.containsEdge(source,destination)) throw new GraphException("Graph getWeightOf: the edge does not exist");
    // find destination in adjacence list of source and return the edge weight
    for(int k = 0; k < this.adjList.get(source).size(); k++){
      if(this.comparator.compare(this.adjList.get(source).get(k).getValue(), destination) == 0){
        return this.adjList.get(source).get(k).getWeight();
      }
    }
    return null;
  }

  /**
   * Function to print the graph. It prints every node and its adjacence list.
   */
  public void printGraph(){
    for (T key: this.adjList.keySet()){  
			System.out.println(key + " -> " + this.adjList.get(key));
		} 
  }
}
