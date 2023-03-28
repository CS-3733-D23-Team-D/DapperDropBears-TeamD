package edu.wpi.teamname.navigation.AStar;

public class NodeScorer implements Scorer<Node>{

    @Override
    public double computeCost(Node from, Node to) {
        double x1 = from.getX();
        double x2 = to.getX();
        double y1 = from.getY();
        double y2 = to.getY();

        double a = Math.pow((x2-x1), 2);
        double b = Math.pow((y2-y1), 2);
        return Math.sqrt(a+b);
    }

}
