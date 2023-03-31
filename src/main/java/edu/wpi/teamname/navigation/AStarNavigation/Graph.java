package edu.wpi.teamname.navigation.AStarNavigation;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
  private Node start;
  private Node target;
  private List<Node> nodes = new ArrayList<>();
  private List<Node.Edge> edges = new ArrayList<>();

  Graph(List<Node> nodes) {
    this.start = null;
    this.target = null;
    this.nodes.addAll(nodes);
  }

  Graph() {
    this.start = null;
    this.target = null;
  }

  Graph(List<Node> nodes, List<Node.Edge> edges) {
    this.start = null;
    this.target = null;
    this.nodes.addAll(nodes);
    this.edges.addAll(edges);
  }

  public void addNode(Node n) {
    this.nodes.add(n);
  }

  public void addEdge(Node.Edge e) {
    this.edges.add(e);
  }

  public void setStart(Node n) {
    this.start = n;
  }

  public void setTarget(Node n) {
    this.target = n;
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public Node getStart() {
    return start;
  }

  public Node getTarget() {
    return target;
  }

  public Node aStar(Node start, Node target) {
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

  public String toString() {
    String str = "";
    for (Node n : nodes) {
      str += "Node: " + n.name + ", Branches: ";
      for (Node.Edge e : n.neighbors) str += e.node.name + ": " + e.weight + ", ";
      str += "||";
    }
    return str;
  }

  public void setAllH() {
    if (this.target == null || this.start == null) return;
    for (Node n : this.nodes) {
      n.h = findWeight(n, this.target);
      // n.h = 0;
    }
    target.h = 0;
  }

  public void setAllG() {
    if (this.target == null || this.start == null) return;
    for (Node n : this.nodes) {
      n.g = findWeight(n, this.start);
      // n.h = 0;
    }
    start.g = 0;
  }
}
