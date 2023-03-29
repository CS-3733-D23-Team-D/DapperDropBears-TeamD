package edu.wpi.teamname.navigation.AStarNavigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
  public static void main(String[] args) {

    // Head node is starting node
    // G will be 0 for Starter node
    //        Node head = new Node(1, 1);
    //        head.g = 0;

    Node start = new Node(1, 1);
    Node n1 = new Node(1, 3);
    Node n2 = new Node(2, 2);
    Node n3 = new Node(3, 1);
    Node n4 = new Node(3, 3);
    Node n5 = new Node(4, 2);
    Node n6 = new Node(4, 4);

    start.addBranch(n1);
    start.addBranch(n2);
    start.addBranch(n3);
    n1.addBranch(n4);
    n1.addBranch(n2);
    n2.addBranch(n4);
    n2.addBranch(n3);
    n3.addBranch(n4);
    n3.addBranch(n5);
    n4.addBranch(n6);
    n5.addBranch(n6);

    //        System.out.println(n1.toString());
    //        System.out.println(n2.toString());
    //        System.out.println(n3.toString());
    //        System.out.println(n1.findWeight(n2));

    Node res = aStar(n1, n5); // run the A* algorithm
    printPath(res);
  }

  public static Node aStar(Node start, Node target) {
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
  public static double findWeight(Node a, Node b) {
    double x1 = a.getX();
    double x2 = b.getX();
    double y1 = a.getY();
    double y2 = b.getY();

    double x = Math.pow((x2 - x1), 2);
    double y = Math.pow((y2 - y1), 2);
    return Math.sqrt(x + y);
  }
}
