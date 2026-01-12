package minimumheap;

import java.beans.Transient;
import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * It specifies a test suite for the Minimum Heap library.
 * @author Anita Scanu
 */
public class MinimumHeapTests {

  /**
   * @author Anita Scanu
   */
  class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
    }
  }

  private Integer i1, i2, i3, i4;
  private MinimumHeap<Integer> minHeap;

  @Before
  public void createMinimumHeap() throws MinimumHeapException{
    i1 = 0;
    i2 = 1;
    i3 = 2;
    i4 = 3;
    minHeap = new MinimumHeap<>(new IntegerComparator());
  }

  @Test
  public void testIsEmpty_zeroElement() throws Exception{
    assertTrue(minHeap.isEmpty());
  }

  @Test
  public void testIsEmpty_oneElement() throws Exception{
    minHeap.insert(i1);
    assertFalse(minHeap.isEmpty());
  }

  @Test
  public void testSize_zeroElement() throws Exception{
    assertEquals(0, minHeap.size());
  }

  @Test
  public void testSize_oneElement() throws Exception{
    minHeap.insert(i1);
    assertEquals(1, minHeap.size());
  }

  @Test
  public void testSize_twoElement() throws Exception{
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertEquals(2, minHeap.size());
  }

  @Test
  // It directly access the MinHeap instance variable minHeap.minHeap
  public void testInsert_oneElement() throws Exception{
    minHeap.insert(i1);
    assertTrue(i1 == minHeap.minHeap.get(0));
  }

  @Test
  // It directly access the MinHeap instance variable minHeap.minHeap
  public void testInsert_threeElement() throws Exception{
    Integer[] arrExpected = {i1,i3,i2};
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertArrayEquals(arrExpected, minHeap.minHeap.toArray());
  }

  @Test
  public void testParent_rootElement() throws Exception{
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertEquals(null, minHeap.getParent(i1));
  }

  @Test
  public void testParent_leafElement() throws Exception{
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertEquals(i1, minHeap.getParent(i3));
  }

  @Test
  public void testLeft_rootElement() throws Exception{
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertEquals(i3, minHeap.getLeft(i1));
  }

  @Test
  public void testLeft_leafElement() throws Exception{
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertEquals(null, minHeap.getLeft(i3));
  }

  @Test
  public void testRight_rootElement() throws Exception{
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertEquals(i2, minHeap.getRight(i1));
  }

  @Test
  public void testRight_leafElement() throws Exception{
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    assertEquals(null, minHeap.getRight(i2));
  }

  @Test
  public void testExtractMin_zeroElement() throws Exception{
    assertEquals(null, minHeap.extractMin());
  }

  @Test
  // It directly access the MinHeap instance variable minHeap.minHeap
  public void testExtractMin_oneElement() throws Exception{
    Integer[] arrExpected = {};
    minHeap.insert(i2);
    minHeap.extractMin();
    assertArrayEquals(arrExpected, minHeap.minHeap.toArray());
  }

  @Test
  // It directly access the MinHeap instance variable minHeap.minHeap
  public void testExtractMin_threeElement() throws Exception{
    Integer[] arrExpected = {i2,i3};
    minHeap.insert(i3);
    minHeap.insert(i1);
    minHeap.insert(i2);
    minHeap.extractMin();
    assertArrayEquals(arrExpected, minHeap.minHeap.toArray());
  }

  @Test
  // It directly access the MinHeap instance variable minHeap.minHeap
  public void testDecrease_oneElement() throws Exception{
    Integer[] arrExpected = {i1};
    minHeap.insert(i2);
    minHeap.decrease(i2,i1);
    assertArrayEquals(arrExpected, minHeap.minHeap.toArray());
  }

  @Test
  // It directly access the MinHeap instance variable minHeap.minHeap
  public void testDecrease_threeElement() throws Exception{
    Integer[] arrExpected = {i1,i3,i2};
    minHeap.insert(i2);
    minHeap.insert(i3);
    minHeap.insert(i4);
    minHeap.decrease(i4,i1);
    assertArrayEquals(arrExpected, minHeap.minHeap.toArray());
  }

}
