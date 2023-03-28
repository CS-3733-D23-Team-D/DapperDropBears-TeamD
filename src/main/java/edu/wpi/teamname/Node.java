package edu.wpi.teamname;

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
  @Getter @Setter private ArrayList<Edge> edges;

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

  // changes a node's long and short names
  public void updateNodeName(String newLong, String newShort) {
    this.longName = newLong;
    this.shortName = newShort;
  }

  // Returns all the attributes of a Node as a String
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

  // Updates new x and y coordinates
  public void updateCoordinates(int newX, int newY) {
    this.xCord = newX;
    this.yCord = newY;
  }

  // Adds an edge to the edges list
  public void addEdge(Edge e) {
    if (!edges.contains(e)) {
      edges.add(e);
    }
  }

  // Removes an edge from the edges list
  public void removeEdge(Edge e) {
    if (edges.contains(e)) {
      edges.remove(e);
    }
  }
}
