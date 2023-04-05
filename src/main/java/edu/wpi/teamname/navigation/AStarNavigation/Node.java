package edu.wpi.teamname.navigation.AStarNavigation;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
  private static int idCounter = 0;
  public int id;
  public String name;
  private double x;
  private double y;
  public Node parent = null;
  public List<Edge> neighbors;

  // f: sum of g and h;
  public double f = Double.MAX_VALUE;
  // g: Distance from start and node n
  public double g = Double.MAX_VALUE;
  // heuristic: WILL NEED A FUNCTION TO FIND THIS
  public double h;

  Node(double x, double y, String name) {
    this.x = x;
    this.y = y;
    this.name = name;
    this.h = 0;
    this.id = idCounter++;
    this.neighbors = new ArrayList<>();
  }

  @Override
  public int compareTo(Node n) {
    return Double.compare(this.f, n.f);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public static class Edge {
    public double weight;
    public Node node;

    Edge(double weight, Node node) {
      this.weight = weight;
      this.node = node;
    }
  }

  public void addBranch(Node node) {
    double weight = findWeight(node);
    Edge newEdge = new Edge(weight, node);
    neighbors.add(newEdge);
  }

  public double findWeight(Node b) {
    double x1 = this.x;
    double x2 = b.getX();
    double y1 = this.y;
    double y2 = b.getY();

    double x = Math.pow((x2 - x1), 2);
    double y = Math.pow((y2 - y1), 2);
    return Math.sqrt(x + y);
  }

  // INSERT FUNCTION TO FIND HEURISTATIC
  // CURRENT: SET UP TO RETURN DISTANCE FROM TARGET
  // IS THERE A BETTER FUNCTION FOR THIS??????
  public double calculateHeuristic(Node target) {
    // Heuristic will return distance from target
    return Math.sqrt(
        (target.getX() - this.x) * (target.getX() - this.x)
            + (target.getY() - this.y) * (target.getY() - this.y));
  }

  public String toString() {
    return "Node name: " + name + "|X: " + x + "|Y: " + y + "|H: " + h;
  }
}
