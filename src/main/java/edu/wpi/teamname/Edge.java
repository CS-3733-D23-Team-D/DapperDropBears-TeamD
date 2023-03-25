package edu.wpi.teamname;
import lombok.Getter;
import lombok.Setter;

public class Edge {
    @Getter @Setter String edgeID;
    @Getter @Setter String startNode;
    @Getter @Setter String endNode;

    // Returns all the attributes of a Node as a String
    public String toString() {
        String eIDs = "EdgeID: " + edgeID;
        String startNodeS = "Starting Node: " + startNode;
        String endNodeS = "Ending Node: " + endNode;
        return eIDs + " " + startNodeS + " " + endNodeS;
    }
}
