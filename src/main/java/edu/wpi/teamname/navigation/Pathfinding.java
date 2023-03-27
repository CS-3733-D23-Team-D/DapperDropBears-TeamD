package edu.wpi.teamname.navigation;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

public class Pathfinding {
  private int[][] graph; // hardcoded graph
  private boolean[] visited; // visited nodes
  private int[] parent; // integer array to keep track of parent of each node

  // constructor
  public Pathfinding(int[][] graph) {
    this.graph = graph;
    visited = new boolean[graph.length];
    parent = new int[graph.length];
    Arrays.fill(parent, -1); // initialize parent array with -1
  }

  // method to get the shortest path between 2 points
  public List<Integer> bfsBacktrack(int start, int dest) {
    List<Integer> path = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    visited[start] = true;
    queue.add(start);

    while (!queue.isEmpty()) {
      int curr = queue.poll();
      if (curr == dest) { // destination found
        // backtrack to start and add nodes to path
        int node = dest;
        while (node != start) {
          path.add(node);
          System.out.println("This is parent node:" + parent[node]);
          node = parent[node];
        }
        path.add(start);
        Collections.reverse(path);
        return path;
      }
      for (int i = 0; i < graph.length; i++) {
        if (graph[curr][i] == 1 && !visited[i]) { // if node is adjacent and not visited
          visited[i] = true;
          parent[i] = curr;
          queue.add(i);
        }
      }
    }
    return null; // destination not reachable from start
  }

  // main method to test the algorithm
  public static void main(String[] args) {
    int[][] graph = { // Hardcoded graph
      {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
      {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
      {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
      {0, 1, 0, 0, 1, 1, 1, 0, 1, 1},
      {0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 1, 0, 0, 0, 0, 1, 1},
      {0, 0, 1, 1, 0, 0, 0, 1, 1, 0},
      {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
      {0, 0, 0, 1, 0, 1, 1, 0, 0, 0},
      {1, 0, 0, 1, 1, 1, 0, 0, 0, 0}
    };

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter starting point: ");
    int startNode = sc.nextInt();

    System.out.print("Enter destination: ");
    int endNode = sc.nextInt();

    Pathfinding bfs = new Pathfinding(graph);
    List<Integer> path = bfs.bfsBacktrack(startNode, endNode);
    if (path != null) {
      System.out.println("Path from " + startNode + " to " + endNode + ": " + path);
    } else {
      System.out.println("No path found from " + startNode + " to " + endNode + ".");
    }
  }
}

