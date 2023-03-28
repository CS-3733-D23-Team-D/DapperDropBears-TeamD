package edu.wpi.teamname.navigation.AStar;
//RouteNode-> a node that is contained in our found path
public class RouteNode <T extends GraphNode> implements Comparable<RouteNode>{
    private final T current;
    private T previous;
    private double routeScore;
    private double estimatedScore;

    RouteNode(T current){
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    RouteNode(T current, T previous, double routeScore, double estimatedScore){
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }
    //compare to method will take two route nodes and compare the estimated score of each of them
    //Returns 1 if current route is greater than other route
    //Returns -1 if current route is less than other route
    //else-> returns 0
    public int compareTo (RouteNode route){
        if(this.estimatedScore > route.estimatedScore){
            return 1;
        }
        else if(this.estimatedScore < route.estimatedScore){
            return -1;
        }
        else return 0;
    }

    public T getCurrent() {
        return current;
    }
    public T getPrevious() {
        return previous;
    }
    public double getRouteScore() {
        return routeScore;
    }

    public double getEstimatedScore() {
        return estimatedScore;
    }

    public void setPrevious(T previous) {
        this.previous = previous;
    }

    public void setRouteScore(double newScore) {
        this.routeScore = newScore;
    }

    public void setEstimatedScore(double estimatedScore) {
        this.estimatedScore = estimatedScore;
    }

}
