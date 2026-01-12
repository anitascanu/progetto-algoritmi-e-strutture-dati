package graph;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 * @author Anita Scanu
 */
public class GraphTestsRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(GraphTests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString()); // report the failure
    }
    System.out.println(result.wasSuccessful());
  }
}
