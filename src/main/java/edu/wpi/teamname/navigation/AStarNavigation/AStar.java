package edu.wpi.teamname.navigation.AStarNavigation;

import java.util.*;

public class AStar {
  public static void main(String[] args) {

    List<Node> nodes = new ArrayList<>();
    Node n0 = new Node(1, 1, "n0"); // use as target for example
    Node n1 = new Node(1, 3, "n1");
    Node n2 = new Node(2, 2, "n2");
    Node n3 = new Node(3, 1, "n3");
    Node n4 = new Node(3, 3, "n4");
    Node n5 = new Node(4, 2, "n5");
    Node n6 = new Node(4, 4, "n6"); // Use as start for example

    n0.addBranch(n1);
    n0.addBranch(n2);
    n0.addBranch(n3);
    n1.addBranch(n0);
    n1.addBranch(n4);
    n1.addBranch(n2);
    n2.addBranch(n4);
    n2.addBranch(n3);
    n2.addBranch(n0);
    n2.addBranch(n1);
    n3.addBranch(n0);
    n3.addBranch(n2);
    n3.addBranch(n4);
    n3.addBranch(n5);
    n4.addBranch(n6);
    n4.addBranch(n1);
    n4.addBranch(n2);
    n4.addBranch(n3);
    n5.addBranch(n6);
    n5.addBranch(n3);
    n6.addBranch(n4);
    n6.addBranch(n5);

    nodes.add(n0);
    nodes.add(n1);
    nodes.add(n2);
    nodes.add(n3);
    nodes.add(n4);
    nodes.add(n5);
    nodes.add(n6);

    Graph Test1 = new Graph(nodes);
    System.out.println(Test1.toString());

    Scanner console = new Scanner(System.in);
    System.out.println("Enter your start node:");
    String startNodeStr = console.nextLine();
    System.out.println("Enter your target node:");
    String targetNodeStr = console.nextLine();
    console.close();

    for (Node n : Test1.getNodes()) {
      if (n.name.equals(startNodeStr)) {
        Test1.setStart(n);
        // System.out.println("Start set.");
      }
      if (n.name.equals(targetNodeStr)) {
        Test1.setTarget(n);
        // System.out.println("Target set.");
      }
    }
    Test1.setAllH();
    try {
      // Node res = Test1.aStar(Test1.getStart(), Test1.getTarget());
      Node res = aStar(Test1);
      System.out.print("The path to take is: ");
      printPath(res);
    } catch (Exception e) {
      System.out.print("Could not produce this route.");
    }
  }

  // Eventually Change from print to send to UI to display directions
  public static void printPath(Node target) {
    Node n = target;
    if (n == null) return;
    List<Integer> ids = new ArrayList<>();

    while (n.parent != null) {
      ids.add(n.id);
      n = n.parent;
    }
    ids.add(n.id);
    Collections.reverse(ids);

    for (int id : ids) {
      System.out.print(id + "");
    }
    System.out.println("");
  }
  // This will return the weight(Distance) between 2 nodes

  public static Node aStar(Graph g) {
    Node start = g.getStart();
    Node target = g.getTarget();
    PriorityQueue<Node> closedList = new PriorityQueue<>();
    PriorityQueue<Node> openList = new PriorityQueue<>();

    start.f = start.g + start.calculateHeuristic(target);
    openList.add(start);

    while (!openList.isEmpty()) {
      Node n = openList.peek();
      if (n == target) return n;

      for (Node.Edge edge : n.neighbors) {
        Node m = edge.node;
        double totalWeight = n.g + edge.weight;

        if (!openList.contains(m) && !closedList.contains(m)) {
          m.parent = n;
          m.g = totalWeight;
          m.f = m.g + m.calculateHeuristic(target);
          openList.add(m);
        } else {
          if (totalWeight < m.g) {
            m.parent = n;
            m.g = totalWeight;
            m.f = m.g + m.calculateHeuristic(target);

            if (closedList.contains(m)) {
              closedList.remove(m);
              openList.add(m);
            }
          }
        }
      }
      openList.remove(n);
      closedList.add(n);
    }
    return null;
  }
}
