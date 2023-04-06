package edu.wpi.teamname;

import static org.junit.Assert.assertEquals;

import edu.wpi.teamname.navigation.*;
import edu.wpi.teamname.navigation.Node;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AStarTest {

  public static List<Node> nodes = new ArrayList<>();
  public static List<Edge> edges = new ArrayList<>();

  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(AStarTest.class);

    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }

    System.out.println(result.wasSuccessful());
    System.out.println("DONE!");
  }

  // First test: strait line from node n1 to n5
  @Test
  public void test1() throws Exception {
    Pathfinding p = new Pathfinding();
    Graph g = p.getGraph();
    AStar a = p.getAStar();

    g.setStart(g.getNodes().get(4));
    g.setTarget(g.getNodes().get(2));

    a.aStar(g);
    String printStr = a.returnStringPath(p.getGraph().getTarget());

    assertEquals(printStr, "120 115 200 1795 100 190 130 110 \n");
  }

  @Test
  public void test2() throws Exception {
    Pathfinding p = new Pathfinding();
    Graph g = p.getGraph();
    AStar a = p.getAStar();

    g.setStart(g.getNodes().get(11));
    g.setTarget(g.getNodes().get(10));

    a.aStar(g);
    String printStr = a.returnStringPath(p.getGraph().getTarget());

    assertEquals(printStr, "155 1765 1775 1785 2850 2845 150 \n");
  }

  @Test
  public void test3() throws Exception {
    Pathfinding p = new Pathfinding();
    Graph g = p.getGraph();
    AStar a = p.getAStar();

    g.setStart(g.getNodes().get(280));
    g.setTarget(g.getNodes().get(282));

    a.aStar(g);
    String printStr = a.returnStringPath(p.getGraph().getTarget());

    assertEquals(printStr, "1500 1505 1510 \n");
  }
}
