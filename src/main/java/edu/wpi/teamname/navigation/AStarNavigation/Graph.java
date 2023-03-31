package edu.wpi.teamname.navigation.AStarNavigation;

import java.util.ArrayList;
import java.util.List;

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
