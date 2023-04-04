package edu.wpi.teamname.navigation;

import java.util.*;

public class AStar {
  public static void printPath(Node target) {
    Node n = target;
    if (n == null) return;
    List<Integer> ids = new ArrayList<>();

    while (n.getParent() != null) {
      ids.add(n.id);
      n = n.getParent();
    }
    ids.add(n.id);
    Collections.reverse(ids);

    for (int id : ids) {
      System.out.print(id + " ");
    }
    System.out.println("");
  }

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

      for (Node nei : n.getNeighbors()) {
        double totalWeight = n.g + nei.findWeight(n);

        if (!openList.contains(nei) && !closedList.contains(nei)) {
          nei.setParent(n);
          nei.g = totalWeight;
          nei.f = nei.g + nei.calculateHeuristic(target);
          openList.add(nei);
        } else {
          if (totalWeight < nei.g) {
            nei.setParent(n);
            nei.g = totalWeight;
            nei.f = nei.g + nei.calculateHeuristic(target);

            if (closedList.contains(nei)) {
              closedList.remove(nei);
              openList.add(nei);
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
