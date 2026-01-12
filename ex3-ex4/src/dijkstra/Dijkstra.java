package dijkstra;

import java.util.Comparator;

import javax.lang.model.util.ElementScanner6;

import java.util.ArrayList;
import java.util.Collections;
import minimumheap.MinimumHeap;
import graph.Graph;
import minimumheap.MinimumHeapException;
import graph.GraphException;

/**
 * The Dijkstra class contains the Dijkstra algorithm for finding the shortest path in a graph.
 * The class also contains a function to print that path and another function that returns the 
 * lenght of that path.
 * @author Anita Scanu
 */
public class Dijkstra {
  /**
   * It represents a Vertex of the graph with other significant information that can be helpful
   * when implementing Dijkstra's algorithm.
   * @author Anita Scanu
   */
  public class Vertex{
    String node = null;
    Double d = null;
    String parent = null;

    public Vertex(String node, Double d){
      this.node = node;
      this.d = d;
    }

    public Vertex(String node, Double d, String parent){
      this.node = node;
      this.d = d;
      this.parent = parent;
    }

    public void setD(Double d){
      this.d = d;
    }

    public void setParent(String parent){
      this.parent = parent;
    }

    public String getNode(){ return this.node; }

    public Double getD(){ return this.d; }

    public String getParent(){ return this.parent; }
  }

  class VertexComparator implements Comparator<Vertex>{
    @Override
    public int compare(Vertex a, Vertex b) {
      if(a.getD() > b.getD())
        return(1);
      else if (a.getD() < b.getD())
        return(-1);
      else{
        if(a.getNode().compareTo(b.getNode()) < 0)
          return(-1);
        else if(a.getNode().compareTo(b.getNode()) > 0)
          return(1);
        else 
          return(0);
      }
    }
  }

  // Check if the graph has negative weights. Return true if it does not have negative weights.
  private boolean hasNotNegativeWeights(Graph<String,Double> g){
    ArrayList<Graph<String,Double>.Edge<String,Double>> e = g.getEdges();
    for(int i = 0; i < e.size(); i++){
      if(e.get(i).getWeight() < 0){
        return false;
      }
    }
    return true;
  }

  // Initialize an array of vertex. Set d of source vertex to 0 and d of every other vertex to infinite.
  private ArrayList<Vertex> initializeSingleSource(Graph<String,Double> g, String source){
    ArrayList<String> nodes = g.getNodes();
    ArrayList<Vertex> V = new ArrayList<>();
    for(int i = 0; i < nodes.size(); i++){
      if(g.comparator.compare(source, nodes.get(i))!=0){
        // setting d of vertex to infinite
        Vertex newVertex = new Vertex(nodes.get(i), Double.MAX_VALUE);
        V.add(newVertex);
      }
      else{
        // setting d of source vertex to 0
        Vertex newVertex = new Vertex(source, 0.0);
        V.add(newVertex);
      }
    }
    return V;
  }

  /**
   * Get the shortest path from the node source in the graph g to all the other nodes.
   * @param g: a graph
   * @param source: the node from where to start
   * @return an ArrayList of vertex with the distances from each node the source
   */
  public ArrayList<Vertex> getShortestPath(Graph<String,Double> g, String source) throws DijkstraException, MinimumHeapException, GraphException{
    // initialize the attributes d and parent of graph's nodes
    ArrayList<Vertex> V = initializeSingleSource(g,source);
    // empty set
    ArrayList<Vertex> S = new ArrayList<>();
    // min-priority queue
    MinimumHeap<Vertex> Q = new MinimumHeap<>(new VertexComparator());
    // insert the nodes in the queue
    for(int i = 0; i < V.size(); i++){
      Q.insert(V.get(i));
    }
    while(!Q.isEmpty()){
      // extract the closest vertex from the queue and add it to S
      Vertex u = Q.extractMin();
      S.add(u);
      ArrayList<String> adjList = g.adjacenceOfNode(u.getNode());
      // for every node in the adjacence list of the extracted node
      for(int k = 0; k < adjList.size(); k++){
        int i = 0;
        // find the index of the k-node in the arraylist V
        while(i < V.size() && g.comparator.compare(V.get(i).getNode(), adjList.get(k)) != 0){
            i++;
        }
        Vertex v = V.get(i);
        // update v.d and v.parent if the shortest path can be shortened by passing from u
        if(v.getD() > (u.getD() + g.getWeightOf(u.getNode(), v.getNode()))){
            Double d = u.getD() + g.getWeightOf(u.getNode(),v.getNode());
            Vertex newV = new Vertex(v.getNode(), d, u.getNode());
            // update the value in the queue
            Q.decrease(v, newV);
            // update the value in the list of vertex
            V.remove(i);
            V.add(newV);
        }
      }
    }
    return S;
  }

  /**
   * Get the shortest path distance in a graph from source to destination.
   * @param g: the graph
   * @param source: the node from where to start
   * @param destination: the node to reach
   * @return the minimum distance from source to destination
   * @throws DijkstraException if g or source or destination is null, or if source is not contained in g, or if destination is not
   *         contained in g, or if the graph has negative weights
   */
  public Double getShortestPath(Graph<String,Double> g, String source, String destination) throws DijkstraException, MinimumHeapException, GraphException{
    if(g == null) throw new DijkstraException("Dijkstra getShortestPath: g cannot be null");
    if(source == null) throw new DijkstraException("Dijkstra getShortestPath: source cannot be null");
    if(destination == null) throw new DijkstraException("Dijkstra getShortestPath: destination cannot be null");
    if(!g.containsNode(source)) throw new DijkstraException("Dijkstra getShortestPath: the graph does not contains source");
    if(!g.containsNode(destination)) throw new DijkstraException("Dijkstra getShortestPath: he graph does not contains destination");
    if(!hasNotNegativeWeights(g)) throw new DijkstraException("Dijkstra getShortestPath: the graph has negative weights");

    ArrayList<Vertex> S = getShortestPath(g, source);
    // find destination in S to get the shortest distance
    for(int i = 0; i < S.size(); i++){
      if(S.get(i).getNode().compareTo(destination) == 0){
        return S.get(i).getD();
      }
    }
    return null;
  }

  /**
   * Print the shortest path in a graph from source to destination.
   * @param g: the graph
   * @param source: the node from where to start
   * @param destination: the node to reach
   * @return the minimum distance from source to destination
   * @throws DijkstraException if g or source or destination is null, or if source is not contained in g, or if destination is not
   *         contained in g, or if the graph has negative weights
   */
  public void printShortestPath(Graph<String,Double> g, String source, String destination)throws DijkstraException, MinimumHeapException, GraphException{
    if(g == null) throw new DijkstraException("Dijkstra printShortestPath: g cannot be null");
    if(source == null) throw new DijkstraException("Dijkstra printShortestPath: source cannot be null");
    if(destination == null) throw new DijkstraException("Dijkstra printShortestPath: destination cannot be null");
    if(!g.containsNode(source)) throw new DijkstraException("Dijkstra printShortestPath: the graph does not contains source");
    if(!g.containsNode(destination)) throw new DijkstraException("Dijkstra printShortestPath: he graph does not contains destination");
    if(!hasNotNegativeWeights(g)) throw new DijkstraException("Dijkstra printShortestPath: the graph has negative weights");

    ArrayList<Vertex> S = getShortestPath(g, source);
    for(int i = 0; i < S.size(); i++){
      // find destination in S
      if(S.get(i).getNode().compareTo(destination) == 0){
        ArrayList<String> path = new ArrayList<>();
        // add destination to the path
        path.add(S.get(i).getNode());
        Vertex v = S.get(i);
        String s = v.getParent();
        while(s != null){
          // add the parent node to the path
          path.add(s);
          // find the vertex with node value s
          for(int k = 0; k < S.size(); k++){
            if(S.get(k).getNode().compareTo(s) == 0){
              v = S.get(k);
            }
          }
          s = v.getParent();
        }
        // the path is opposite
        Collections.reverse(path);
        // print the path
        System.out.println(path);
      }
    }
  }
}
