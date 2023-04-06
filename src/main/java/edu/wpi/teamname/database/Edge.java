package edu.wpi.teamname.database;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class Edge {
  @Getter @Setter String startNode;
  @Getter @Setter String endNode;
  @Getter private ArrayList<Node> nodes;
  // Constructor
  public Edge(String startNode, String endNode) {
    this.startNode = startNode;
    this.endNode = endNode;
    nodes = new ArrayList<Node>();
  }

  /**
   * * Creates a string representation of the edge
   *
   * @return a string representation of the edge
   */
  public String toString() {
    String startNodeS = "Starting Node: " + startNode;
    String endNodeS = "Ending Node: " + endNode;
    return startNodeS + " " + endNodeS;
  }

  /**
   * * Adds the given node to the edge's node list as well as updates the node's edge list to have
   * this edge Only adds the node if the edge has not already been added
   *
   * @param n the node to be added
   */
  public void addNode(Node n) {
    if (!nodes.contains(n) && nodes.size() < 2) {
      nodes.add(n);
    }
    if (!n.getEdges().contains(this)) {
      n.addEdge(this);
    }
  }

  /**
   * * Deletes the given node from the edge's node list and deletes this edge from the given node's
   * edge list Checks to make sure that the node is in the list before deleting
   *
   * @param n the node to be deleted
   */
  public void removeNode(Node n) {
    if (nodes.contains(n)) {
      nodes.remove(n);
    }
    if (n.getEdges().contains(this)) {
      n.removeEdge(this);
    }
  }
}
