package edu.wpi.teamname.database;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class Node {
  @Getter @Setter private int nodeID;
  @Getter @Setter private int xCord;
  @Getter @Setter private int yCord;
  @Getter @Setter private String floor;
  @Getter @Setter private String building;
  @Getter private ArrayList<Edge> edges;

  // Constructor
  public Node(int nID, int xCord, int yCord, String floor, String building) {
    this.nodeID = nID;
    this.xCord = xCord;
    this.yCord = yCord;
    this.floor = floor;
    this.building = building;
    edges = new ArrayList<Edge>();
  }

  /**
   * * Creates a string representation of the node
   *
   * @return a string representation of the node
   */
  public String toString() {
    String nIDs = "NodeID: " + this.nodeID;
    String xCordS = "X-coordinate: " + this.xCord;
    String yCordS = "Y-coordinate: " + this.yCord;
    String floorS = "Floor: " + this.floor;
    String buildingS = "Building: " + this.building;
    String output = nIDs + " " + xCordS + " " + yCordS + " " + floorS + " " + buildingS;
    return output;
  }

  /**
   * * Changes the node's x and y coordinates to the provided values
   *
   * @param newX the updated x coordinate of the node
   * @param newY the updated y coordinate of the node
   */
  public void updateCoordinates(int newX, int newY) {
    this.xCord = newX;
    this.yCord = newY;
  }

  /**
   * * Adds the given edge to the node's edge list as well as updates the edge's node list to have
   * this node Only adds the edge if the node has not already been added
   *
   * @param e the edge to be added
   */
  public void addEdge(Edge e) {
    if (!edges.contains(e)) {
      edges.add(e);
    }
    if (!e.getNodes().contains(this)) {
      e.addNode(this);
    }
  }

  /**
   * Updates the node's properties with the provided values, given the unique node ID.
   *
   * @param nodeId the unique identifier for the node to update
   * @param newX the new x-coordinate for the node
   * @param newY the new y-coordinate for the node
   * @param fl the new floor for the node
   * @param bui the new building for the node
   */
  public void updateNode(int nodeId, int newX, int newY, String fl, String bui) {
    if (this.nodeID == nodeId) {
      this.xCord = newX;
      this.yCord = newY;
      this.floor = fl;
      this.building = bui;
    }
  }
  /**
   * * Deletes the given edge from the node's edge list and deletes this node from the given edge's
   * node list Checks to make sure that the edge is in the list before deleting
   *
   * @param e the edge to be deleted
   */
  public void removeEdge(Edge e) {
    if (edges.contains(e)) {
      edges.remove(e);
    }
    if (e.getNodes().contains(this)) {
      e.removeNode(this);
    }
  }
}
