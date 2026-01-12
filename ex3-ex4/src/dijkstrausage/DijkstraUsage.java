package dijkstrausage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import dijkstra.Dijkstra;
import graph.Graph;
import graph.GraphException;
import java.util.ArrayList;

/**
 * @author Anita Scanu
 */
public class DijkstraUsage {
  
  private static final Charset ENCODING = StandardCharsets.UTF_8;

  private static void testDijkstra(String filepath, String source, String destination) throws IOException, Exception{
    Graph<String, Double> g = new Graph<>(new StringComparator(), false);
    Path inputFilePath = Paths.get(filepath);
    try(BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)){
      String line = null;
      while((line = fileInputReader.readLine()) != null){      
        String[] lineElements = line.split(",");
        if(!g.containsNode(lineElements[0])){
          g.addNode(lineElements[0]);
        }
        if(!g.containsNode(lineElements[1])){
          g.addNode(lineElements[1]);
        }
        if(!g.containsEdge(lineElements[0], lineElements[1])){
          g.addEdge(lineElements[0], lineElements[1], Double.parseDouble(lineElements[2]));
        }
      }
    }
    Dijkstra d = new Dijkstra();
    Double a1 = d.getShortestPath(g, source, destination);
    System.out.println("Shortest path distance from " + source + " to " + destination + ":   " + a1);
    System.out.println();
    System.out.println("Shortest path:");
    d.printShortestPath(g, source, destination);
  }


  /**
   * Java application that loads a file of records in a graph and then print the shortest path
   * from source to destination using the Dijkstra's algorithm.
   * @author Anita Scanu
   * @param args: args[0] is the graph file name, args[1] is the source for the Dijkstra's algorithm
   *        args[2] is the destination for the Dijkstra's algorithm
   */
  public static void main(String[] args) throws Exception{
    if(args.length < 3)
      throw new Exception("Usage: DijkstraUsage <file_name> <source> <destination>");
    testDijkstra(args[0], args[1], args[2]);
  }
    
}

