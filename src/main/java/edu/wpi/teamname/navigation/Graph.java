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
    assignEdges();
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
  //    while (i < this.nodes.size()) {
  //      int j = 0;
  //      while (j < this.edges.size()) {
  //        if (this.nodes.get(i).id == this.edges.get(j).startNodeID
  //            || this.nodes.get(i).id == this.edges.get(j).endNodeID) {
  //          this.nodes
  //              .get(i)
  //              .addEdge(
  //                  this.edges.get(j),
  //                  nodes.get((edges.get(j).startNodeID - 100) / 5),
  //                  nodes.get((edges.get(j).endNodeID - 100) / 5));
  //        }
  //        j++;
  //      }
  //      i++;
  //    }
}
