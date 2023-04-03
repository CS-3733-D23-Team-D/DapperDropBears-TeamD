package edu.wpi.teamname.navigation;

import java.util.ArrayList;
import java.util.List;

public class Graph {
  private Node start;
  private Node target;
  private List<Node> nodes = new ArrayList<>();
  private List<Edge> edges = new ArrayList<>();

  Graph(List<Node> nodes, List<Edge> edges) {
    this.start = null;
    this.target = null;
    this.nodes.addAll(nodes);
    this.edges.addAll(edges);
  }

  public void addEdge(Edge e) {
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

  public double findWeight(Node a, Node b) {
    double x1 = a.getX();
    double x2 = b.getX();
    double y1 = a.getY();
    double y2 = b.getY();

    double x = Math.pow((x2 - x1), 2);
    double y = Math.pow((y2 - y1), 2);
    return Math.sqrt(x + y);
  }

  public void assignEdges() {
    int i = 0;
    while (i < nodes.size()) {
      int j = 0;
      while (j < edges.size()) {
        if (nodes.get(i).id == edges.get(j).startNodeID
            || nodes.get(i).id == edges.get(j).startNodeID) {
          nodes.get(i).addBranch(edges.get(j));
        }
      }
    }
  }

  //  public String toString() {
  //    String str = "";
  //    for (Node n : nodes) {
  //      str += "Node: " + n.id + ", Branches: ";
  //      for (Node.Edge e : n.neighbors) str += e.node.id + ": " + e.weight + ", ";
  //      str += "||";
  //    }
  //    return str;
  //  }
}
