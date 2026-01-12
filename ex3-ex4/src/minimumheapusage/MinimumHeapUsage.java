package minimumheapusage;

import java.util.Comparator;
import minimumheap.MinimumHeap;
import minimumheap.MinimumHeapException;

/**
 * @author Anita Scanu
 */
public class MinimumHeapUsage {
  public static void main(String[] args) throws MinimumHeapException {
    // create a new minimum heap with integer elements
    MinimumHeap<Integer> a = new MinimumHeap<>(new IntegerComparator());
    // inserting a few elements
    a.insert(5);
    a.insert(2);
    a.insert(3);
    a.insert(4);
    a.insert(1);
    a.insert(6);
    a.insert(-3);
    a.insert(0);
    a.insert(12);
    a.insert(7);
    a.insert(9);
    a.insert(10);
    a.printHeap();
    
    // testing some MinimumHeap library function
    System.out.println();
    System.out.println("Heap size: " + a.size());

    Integer x = a.getParent(-3);
    System.out.println("Parent of -3: " + x);
    x = a.getParent(10);
    System.out.println("Parent of 10: " + x);
    x = a.getLeft(4);
    System.out.println("Left child of 4: " + x);
    x = a.getRight(4);
    System.out.println("Right child of 4: " + x);
    x = a.getRight(12);
    System.out.println("Right child of 12: " + x);
    
    System.out.println("\nExtract min:");
    System.out.println("Min: " + a.extractMin());
    a.printHeap();

    System.out.println("\nDecrease 3 to -10:");
    a.decrease(3, -10);
    a.printHeap();
  }  
    
}

