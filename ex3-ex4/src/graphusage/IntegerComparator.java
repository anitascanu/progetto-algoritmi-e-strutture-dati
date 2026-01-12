package graphusage;

import java.util.Comparator;

/**
 * @author Anita Scanu
 */
public class IntegerComparator implements Comparator<Integer>{
  /**
   * Compare function. It compares two integer.
   * @param a: the first int to compare
   * @param b: the second int to compare
   * @return 0 if a equals b, a negative number if a is less than b, a positive number if a is greater than b
   */
  @Override
  public int compare(Integer a, Integer b) {
    return a.intValue() - b.intValue();
   }
}

