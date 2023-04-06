package edu.wpi.teamname.navigation;

import java.util.*;

public class AStar {

  public static ArrayList<Node> getPath(Node target) {
    Node n = target;

    ArrayList<Node> ids = new ArrayList<>();
    if (n == null) return ids;

    while (n.getParent() != null) {
      ids.add(n);
      n = n.getParent();
    }
    ids.add(n);
    Collections.reverse(ids);

    return ids;
  }

  public static void printPath(Node target) {
    System.out.println(returnStringPath(target));
  }

  public static String returnStringPath(Node target) {
    List<Node> ids = getPath(target);

    String strPath = "";

    for (Node id : ids) {
      strPath += (id.getId() + " ");
    }
    strPath += "\n";

    return strPath;
  }

  public static Node aStar(Graph g) {
    g.setAllG();
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
