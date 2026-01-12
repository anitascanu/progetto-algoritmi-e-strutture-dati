package dijkstrausage;

import java.util.Comparator;

/**
 * @author Anita Scanu
 */
public class StringComparator implements Comparator<String>{
  /**
   * Compare function. It compares two string.
   * @param a: the first string to compare
   * @param b: the second string to compare
   * @return a negative integer if this a lexicographically precedes b. The result is a positive integer if this a lexicographically follows b. 
   *         The result is zero if the strings are equal.
   */
  @Override
  public int compare(String a, String b) {
    return a.compareTo(b);
  }
}

