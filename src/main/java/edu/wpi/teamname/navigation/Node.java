package edu.wpi.teamname.navigation;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
  public int id;
  public String floor;
  public String building;
  private int x;
  private int y;
  private Node parent = null;
  private List<Node> neighbors;
  private List<Edge> edges;

  // f: sum of g and h;
  public double f = Double.MAX_VALUE;
  // g: Distance from start and node n
  public double g = Double.MAX_VALUE;
  // heuristic: WILL NEED A FUNCTION TO FIND THIS
  public double h;

  Node(int ID, int x, int y, String Floor, String Building) {
    this.x = x;
    this.y = y;
    this.floor = Floor;
    this.building = Building;
    this.h = 0;
    this.id = ID;
    this.neighbors = new ArrayList<>();
    this.edges = new ArrayList<>();
  }

  @Override
  public int compareTo(Node n) {
    return Double.compare(this.f, n.f);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public List<Node> getNeighbors() {
    return neighbors;
  }

  public void setNeighbor(Node n) {
    this.neighbors.add(n);
  }

  public void addEdge(Edge edge, Node s, Node e) {
    if (!this.edges.contains(edge)) {
      edges.add(edge);

      if (this.id == edge.startNodeID) {
        this.setNeighbor(e);
      } else {
        this.setNeighbor(s);
      }
    }
  }

  // shoudl probably have this in Edge class
  public double findWeight(Node b) {
    int x1 = this.x;
    int x2 = b.getX();
    int y1 = this.y;
    int y2 = b.getY();

    double x = Math.pow((x2 - x1), 2);
    double y = Math.pow((y2 - y1), 2);
    return Math.sqrt(x + y);
  }

  public double calculateHeuristic(Node target) {
    return this.h;
  }

  public int getId() {
    return id;
  }

  public String toString() {
    String edg = "";
    for (Edge e : edges) {
      edg += " " + e.toString();
    }
    String nei = "";
    for (Node n : neighbors) {
      nei += " " + Integer.toString(n.getId());
    }
    return "NodeID:" + id + " Xcord:" + x + " Ycord:" + y + " Heu: " + h + nei;
  }
}
