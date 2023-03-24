package edu.wpi.teamname;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


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


    // changes a node's long and short names
    public void updateNodeName(String newLong, String newShort) {
        this.longName = newLong;
        this.shortName = newShort;
    }

    // Returns all the attributes of a Node as a String
    public String toString()
    {
        String nIDs = "NodeID: " + this.nodeID;
        String xCordS = "X-coordinate: " + this.xCord;
        String yCordS = "Y-coordinate: " + this.yCord;
        String floorS = "Floor: " + this.floor;
        String buildingS = "Building: " + this.building;
        String typeS = "Node Type: " + this.nodeType;
        String lNameS = "Long Name: " + this.longName;
        String sNameS = "Short Name: " + this.shortName;
        String output = nIDs + " " + lNameS + " " + sNameS + " " + xCordS + " " + yCordS + " " + floorS + " " + buildingS + " " + typeS;
        return output;
    }

    // Updates new x and y coordinates
    public void updateCoordinates(int newX, int newY) {
        this.xCord = newX;
        this.yCord = newY;
    }

}
