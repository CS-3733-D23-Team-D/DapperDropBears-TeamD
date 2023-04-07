package edu.wpi.teamname.navigation;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Graph {
  @Getter @Setter private Node start;
  @Getter @Setter private Node target;
  @Getter @Setter private List<Node> nodes = new ArrayList<>();
  @Getter @Setter private List<Edge> edges = new ArrayList<>();

  public void addEdge(Edge e) {
    this.edges.add(e);
  }

  Graph(List<Node> nodes, List<Edge> edges) {
    this.start = null;
    this.target = null;
    this.nodes.addAll(nodes);
    this.edges.addAll(edges);
    assignEdges();
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

  private void assignEdges() {
    int i = 0;
    for (Node n : this.nodes) {
      for (Edge e : this.edges) {
        if (n.id == e.startNodeID || n.id == e.endNodeID) {
          n.addEdge(e, nodes.get((e.startNodeID - 100) / 5), nodes.get((e.endNodeID - 100) / 5));
        }
      }
    }
  }

  public void setAllG() {
    if (this.target == null || this.start == null) return;
    for (Node n : this.nodes) {
      n.setG(findWeight(n, this.start));
    }
    start.setG(0);
  }
}
