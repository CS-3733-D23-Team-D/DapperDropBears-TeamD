package edu.wpi.teamname.database;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Move {
    @Getter @Setter private int nodeID;
    @Getter @Setter private String longName;
    @Getter @Setter private Date date;
    @Getter @Setter private Node node;
    @Getter @Setter private LocationName locationName;

    public Move (int nodeID, String longName, Date date) {
        this.nodeID = nodeID;
        this.longName = longName;
        this.date = date;
    }



}
