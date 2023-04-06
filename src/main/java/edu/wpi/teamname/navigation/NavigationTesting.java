package edu.wpi.teamname.navigation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class NavigationTesting {
  String message = "Hello World";

  @Test
  public void testAStar() {
    Pathfinding p = null;
    try {
      p = new Pathfinding();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    Graph g = p.getGraph();
    AStar a = p.getAStar();

    g.setStart(g.getNodes().get(2));
    g.setTarget(g.getNodes().get(0));

    a.aStar(g);
    String printStr = a.returnStringPath(p.getGraph().getTarget());

    System.out.println(printStr);

    assertEquals(printStr, "110 130 190 100 \n");
  }

  @Test
  public void testPrintMessage() {
    assertEquals(message, "Hello World");
  }

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(NavigationTesting.class);

    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }

    System.out.println(result.wasSuccessful());
    System.out.println("DONE!");
  }
}
