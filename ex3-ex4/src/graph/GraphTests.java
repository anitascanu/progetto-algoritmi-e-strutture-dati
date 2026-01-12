package graph;

import java.beans.Transient;
import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * It specifies a test suite for the Graph library.
 * @author Anita Scanu
 */
public class GraphTests {

  class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
    }
  }

  private Integer i1,i2,i3,i4;
  private Graph<Integer,Integer> graph;
  private Graph<Integer,Integer> undirected_graph;

  @Before
  public void createGraph() throws GraphException{
    i1 = 0;
    i2 = 1;
    i3 = 2;
    i4 = 3;
    graph = new Graph<>(new IntegerComparator(), true);
    undirected_graph = new Graph<>(new IntegerComparator(), false);
  }

  @Test
  public void testAddNode_oneElement() throws Exception{
    graph.addNode(i1);
    assertTrue(graph.containsNode(i1));
  }

  @Test
  public void testAddNode_twoElement() throws Exception{
    graph.addNode(i3);
    graph.addNode(i1);
    assertTrue(graph.containsNode(i1) && graph.containsNode(i3));
  }

  @Test
  public void testContainsNode_zeroElement() throws Exception{
    assertFalse(graph.containsNode(i2));
  }

  @Test
  public void testContainsNode_oneElement() throws Exception{
    graph.addNode(i1);
    assertFalse(graph.containsNode(i2));
  }

  @Test
  public void testContainsEdge_twoElement() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    graph.addEdge(i1,i2,i4);
    assertTrue(graph.containsEdge(i1,i2));
  }

  @Test
  public void testContainsEdgeReverse_twoElement() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    graph.addEdge(i1,i2,i4);
    assertFalse(graph.containsEdge(i2,i1));
  }

  @Test
  public void testContainsEdge_twoElement_undirected() throws Exception{
    undirected_graph.addNode(i1);
    undirected_graph.addNode(i2);
    undirected_graph.addEdge(i1,i2,i4);
    assertTrue(undirected_graph.containsEdge(i1,i2) && undirected_graph.containsEdge(i2,i1));
  }

  @Test
  public void testContainsEdge_zeroElement() throws Exception{
    assertFalse(graph.containsEdge(i2,i1));
  }

  @Test
  public void testContainsEdge_oneElement() throws Exception{
    graph.addNode(i1);
    assertFalse(graph.containsEdge(i2,i1));
  }

  @Test
  public void testIsDirected() throws Exception{
    assertTrue(graph.isDirected);
  }

  @Test
  public void testIsDirected_undirected() throws Exception{
    assertFalse(undirected_graph.isDirected);
  }

  @Test
  public void testNumNodes_zeroElement() throws Exception{
    assertEquals(0, graph.numNodes());
  }

  @Test
  public void testNumNodes_oneElement() throws Exception{
    graph.addNode(i1);
    assertEquals(1, graph.numNodes());
  }

  @Test
  public void testNumNodes_twoElement() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    assertEquals(2, graph.numNodes());
  }

  @Test
  public void testNumEdges_zeroEdges() throws Exception{
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void testNumEdges_oneEdges() throws Exception{
    graph.addNode(i2);
    graph.addNode(i1);
    graph.addEdge(i1,i2,i4);
    assertEquals(1, graph.numEdges());
  }

  @Test
  public void testNumEdges_twoEdges() throws Exception{
    graph.addNode(i2);
    graph.addNode(i1);
    graph.addNode(i3);
    graph.addEdge(i1,i2,i4);
    graph.addEdge(i3,i2,i4);
    assertEquals(2, graph.numEdges());
  }

  @Test
  public void testNumEdges_threeEdges() throws Exception{
    graph.addNode(i2);
    graph.addNode(i1);
    graph.addNode(i3);
    graph.addEdge(i1,i2,i4);
    graph.addEdge(i3,i2,i4);
    graph.addEdge(i1,i3,i4);
    assertEquals(3, graph.numEdges());
  }

  @Test
  public void testNumEdges_twoEdges_undirected() throws Exception{
    undirected_graph.addNode(i2);
    undirected_graph.addNode(i1);
    undirected_graph.addNode(i3);
    undirected_graph.addEdge(i1,i2,i4);
    undirected_graph.addEdge(i3,i2,i4);
    assertEquals(2, undirected_graph.numEdges());
  }

  @Test
  public void deleteNode_oneElement() throws Exception{
    graph.addNode(i1);
    graph.deleteNode(i1);
    assertFalse(graph.containsNode(i1));
  }

  @Test
  public void deleteNode_twoElements() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    graph.deleteNode(i1);
    assertFalse(graph.containsNode(i1));
  }

  @Test
  public void deleteEdge_twoElements() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    graph.addEdge(i1,i2,i4);
    graph.deleteEdge(i1,i2);
    assertFalse(graph.containsEdge(i1,i2));
  }

  @Test
  public void getNodes() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    graph.addNode(i3);
    ArrayList<Integer> a = graph.getNodes();
    assertTrue(a.contains(i1) && a.contains(i2) && a.contains(i3));
  }

  @Test
  public void adjacenceOfNode_zeroElements() throws Exception{
    ArrayList<Integer> a = graph.getNodes();
    assertTrue(a.isEmpty());
  }

  @Test
  public void adjacenceOfNode_threeElements() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    graph.addNode(i3);
    graph.addEdge(i1,i2,i4);
    graph.addEdge(i1,i3,i4);
    ArrayList<Integer> a = graph.adjacenceOfNode(i1);
    assertTrue(a.contains(i2) && a.contains(i3));
  }

  @Test
  public void getWeight() throws Exception{
    graph.addNode(i1);
    graph.addNode(i2);
    graph.addNode(i3);
    graph.addEdge(i1,i2,i4);
    assertEquals(graph.getWeightOf(i1,i2),i4);
  }

  @Test
  public void getWeight_undirected() throws Exception{
    undirected_graph.addNode(i1);
    undirected_graph.addNode(i2);
    undirected_graph.addNode(i3);
    undirected_graph.addEdge(i1,i2,i4);
    assertEquals(undirected_graph.getWeightOf(i1,i2),i4);
  }
}
