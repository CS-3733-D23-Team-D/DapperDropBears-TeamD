package edu.wpi.teamname.navigation;

import java.io.*;
import java.util.*;
import java.util.Scanner;

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
  public static void main(String[] args) throws Exception {
    //    Node A = new Node("CCONF001L1",	2255,	849,	"L1",	"45 Francis",	"CONF",	"Anesthesia Conf
    // Floor L1",	"Conf");
    //    Node B = new Node();
    //    Node C = new Node();
    //    Node D = new Node();
    //    Node E = new Node();

    // Edge A =
    String file =
        "C:\\Users\\artur\\IdeaProjects\\DapperDropBears-TeamD\\src\\main\\java\\edu\\wpi\\teamname\\navigation\\L1Nodes.csv";
    Scanner sc = new Scanner(new File(file));

    int i = 1;
    ArrayList<String> NodeLine = new ArrayList<String>();
    while (sc.hasNextLine()) {
      NodeLine.add(sc.nextLine());
      i++;
    }

    String[] I;
    int j = 0;
    String line = "";
    while (!NodeLine.isEmpty()) {
      line = NodeLine.get(j);
      I = line.split(",");
      NodeLine.remove(j);

      Node node[](I[0], Integer.parseInt(I[1]), Integer.parseInt(I[2]), I[3], I[4], I[5], I[6], I[7]);
    }

    sc.close();
  }
}
