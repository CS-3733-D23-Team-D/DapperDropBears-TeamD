package edu.wpi.teamname.navigation;

import static org.junit.Assert.assertEquals;

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
    Graph g = initialize();
    AStar a = p.getAStar();

    g.setStart(g.getNodes().get(0));
    g.setTarget(g.getNodes().get(4));

    a.aStar(g);
    String printStr = a.returnStringPath(p.getGraph().getTarget());

    System.out.println(printStr);

    assertEquals(printStr, "1 3 5 \n");
  }
  // Second test: n10 to n3
  //    @Test
  //    public static void test2(){
  //        Graph g = initialize();
  //        g.setStart(g.getNodes().get(9));
  //        g.setTarget(g.getNodes().get(3));
  //
  //    }
  //    //Third Test: n3 to n10
  //    @Test
  //    public static void test3(){
  //        Graph g = initialize();
  //        g.setStart(g.getNodes().get(2));
  //        g.setTarget(g.getNodes().get(9));
  //
  //    }

  public static Graph initialize() {
    Node n1 = new Node(1, 0, 0, "i", "j");
    Node n2 = new Node(2, 5, 5, "i", "j");
    Node n3 = new Node(3, 5, 0, "i", "j");
    Node n4 = new Node(4, 3, 3, "i", "j");
    Node n5 = new Node(5, 10, 0, "i", "j");
    Node n6 = new Node(6, 0, 10, "i", "j");
    Node n7 = new Node(7, 10, 10, "i", "j");
    Node n8 = new Node(8, 0, 5, "i", "j");
    Node n9 = new Node(9, 10, 5, "i", "j");
    Node n10 = new Node(10, 5, 10, "i", "j");
    Node n11 = new Node(11, 1, 8, "i", "j");
    Node n12 = new Node(12, 7, 7, "i", "j");
    Node n13 = new Node(13, 9, 1, "i", "j");

    Edge e1 = new Edge(1, 3);
    Edge e2 = new Edge(3, 5);
    Edge e3 = new Edge(13, 5);
    Edge e4 = new Edge(13, 4);
    Edge e5 = new Edge(1, 4);
    Edge e6 = new Edge(8, 4);
    Edge e7 = new Edge(4, 2);
    Edge e8 = new Edge(2, 12);
    Edge e9 = new Edge(12, 9);
    Edge e10 = new Edge(12, 7);
    Edge e11 = new Edge(2, 10);
    Edge e12 = new Edge(11, 8);
    Edge e13 = new Edge(11, 6);
    Edge e14 = new Edge(6, 10);

    nodes.add(n1);
    nodes.add(n2);
    nodes.add(n3);
    nodes.add(n4);
    nodes.add(n5);
    nodes.add(n6);
    nodes.add(n7);
    nodes.add(n8);
    nodes.add(n9);
    nodes.add(n10);
    nodes.add(n11);
    nodes.add(n12);

    edges.add(e1);
    edges.add(e2);
    edges.add(e3);
    edges.add(e4);
    edges.add(e5);
    edges.add(e6);
    edges.add(e7);
    edges.add(e8);
    edges.add(e9);
    edges.add(e10);
    edges.add(e11);
    edges.add(e12);
    edges.add(e13);
    edges.add(e14);

    Graph g = new Graph(nodes, edges);
    return g;
  }
}
