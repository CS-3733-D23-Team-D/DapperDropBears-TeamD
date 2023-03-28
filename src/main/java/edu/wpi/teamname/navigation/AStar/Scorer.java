package edu.wpi.teamname.navigation.AStar;

public interface Scorer <T extends GraphNode>{
    double computeCost(T from, T to);
}
