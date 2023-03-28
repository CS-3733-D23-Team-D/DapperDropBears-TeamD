package edu.wpi.teamname.navigation.AStar;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T extends GraphNode> {
    private final Set<T> nodes;
    private final Map<String, Set<String>> connections;

    public T getNode(String id){
        return nodes.stream().filter(node -> node.getID().equals(id))
                .findFirst().orElseThrow(()-> newIllegalArgumentException("No node from this ID"));
    }

    public Set<T> getConnections(T node){
        return connections.get(node.getID()).stream().map(this::getNode).collect(Collectors.toSet());
    }
}
