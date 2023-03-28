package edu.wpi.teamname.navigation.AStar;

public class Node implements GraphNode{

    private final String id;
    private final String name;
    private final double x;
    private final double y;

    Node(double x, double y){
        this.id = null;
        this.name = null;
        this.x = x;
        this.y = y;
    }
    public Node(String id, String name, double x, double y){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    @Override
    public String getID() {
        return id;
    }
    public String getName(){
        return name;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

}
