package edu.wpi.teamname.navigation;

import lombok.Getter;
import lombok.Setter;

public class Edge {
  @Getter @Setter int startNodeID;
  @Getter @Setter int endNodeID;

  // Constructor
  public Edge(int startNodeID, int endNodeID) {
    this.startNodeID = startNodeID;
    this.endNodeID = endNodeID;
  }

  // Returns all the attributes of a Node as a String
  public String toString() {
    return "StartNodeID: " + startNodeID + " EndNodeID: " + endNodeID;
  }
}
