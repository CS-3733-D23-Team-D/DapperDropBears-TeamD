package edu.wpi.teamname.navigation.AStarNavigation;

import java.util.List;

public class Graph {
     private Node start;
     private Node target;
     private List<Node> nodes;


     Graph(List<Node> nodes){
         this.start = null;
         this.target = null;
         for(Node node: nodes)
             this.nodes.add(node);
     }
    Graph(List<Node> nodes, Node start, Node target){
        this.start = start;
        this.target = target;
        for(Node node: nodes)
            this.nodes.add(node);
    }

    public void addNode(Node n){
         this.nodes.add(n);
    }
     public void setStart(Node n){
         this.start = n;
     }
     public void setTarget(Node n){
         this.target = n;
     }


}
