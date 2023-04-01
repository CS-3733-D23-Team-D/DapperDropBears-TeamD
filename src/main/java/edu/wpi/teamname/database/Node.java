package edu.wpi.teamname.database;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class Node {
  @Getter @Setter private String nodeID;
  @Getter @Setter private int xCord;
  @Getter @Setter private int yCord;
  @Getter @Setter private String floor;
  @Getter @Setter private String building;
  @Getter @Setter private String nodeType;
  @Getter @Setter private String longName;
  @Getter @Setter private String shortName;
  @Getter private ArrayList<Edge> edges;

  // Constructor
  public Node(
      String nID,
      int xCord,
      int yCord,
      String floor,
      String building,
      String nodeType,
      String lName,
      String sName) {
    this.nodeID = nID;
    this.xCord = xCord;
    this.yCord = yCord;
    this.floor = floor;
    this.building = building;
    this.nodeType = nodeType;
    this.longName = lName;
    this.shortName = sName;
    edges = new ArrayList<Edge>();
  }

  /**
   * * Changes the node's long and short names to the provided values
   *
   * @param newLong the updated long name of the node
   * @param newShort the updated short name of the node
   */
  public void updateNodeName(String newLong, String newShort) {
    this.longName = newLong;
    this.shortName = newShort;
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
    String typeS = "Node Type: " + this.nodeType;
    String lNameS = "Long Name: " + this.longName;
    String sNameS = "Short Name: " + this.shortName;
    String output =
        nIDs + " " + lNameS + " " + sNameS + " " + xCordS + " " + yCordS + " " + floorS + " "
            + buildingS + " " + typeS;
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
