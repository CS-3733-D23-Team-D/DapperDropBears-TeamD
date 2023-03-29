package edu.wpi.teamname.navigation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pathfinding {
  private int[][] graph; // hardcoded graph
  private boolean[] visited; // visited nodes
  private int[] parent; // integer array to keep track of parent of each node
  private static int startNodeVal = 0;
  private static int endNodeVal = 0;

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

  public static void createNodes(LinkedList<Node> nodeList) {
    Node conf3 =
        new Node(
            "CCONF003L1",
            2445,
            1245,
            "L1",
            "45 Francis",
            "CONF",
            "Abrams Conference Room",
            "Conf C003L1");
    Node dept4 =
        new Node(
            "CDEPT004L1",
            2310,
            1043,
            "L1",
            "45 Francis",
            "DEPT",
            "Medical Records Film Library Floor L1",
            "Department C004L1");
    Node elevk =
        new Node(
            "WELEV00KL1",
            2220,
            974,
            "L1",
            "45 Francis",
            "ELEV",
            "Elevator K Floor L1",
            "Elevator KL1");
    Node serv1 =
        new Node(
            "CSERV001L1",
            2490,
            1043,
            "L1",
            "45 Francis",
            "SERV",
            "Volunteers Floor L1",
            "Service C001L1");
    Node hall2 =
        new Node(
            "CHALL002L1",
            2445,
            1043,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 2 Floor L1",
            "Hallway C002L1");
    Node hall8 =
        new Node(
            "CHALL008L1",
            2215,
            1045,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 8 Floor L1",
            "Hallway C008L1");
    Node rest2 =
        new Node(
            "CREST002L1",
            2065,
            1284,
            "L1",
            "Tower",
            "REST",
            "Restroom M Elevator Floor L1",
            "Restroom C002L1");
    Node hall7 =
        new Node(
            "CHALL007L1",
            2130,
            1045,
            "L1",
            "Tower",
            "HALL",
            "Hallway 7 Floor L1",
            "Hallway C007L1");
    Node hall6 =
        new Node(
            "CHALL006L1",
            2130,
            1284,
            "L1",
            "Tower",
            "HALL",
            "Hallway 6 Floor L1",
            "Hallway C006L1");
    Node conf2 =
        new Node(
            "CCONF002L1",
            2665,
            1043,
            "L1",
            "45 Francis",
            "CONF",
            "Medical Records Conference Room Floor L1",
            "Conf C002L1");
    Node hall3 =
        new Node(
            "CHALL003L1",
            2445,
            1284,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 3 Floor L1",
            "Hallway C003L1");
    Node lab4 =
        new Node(
            "CLABS004L1",
            2320,
            1284,
            "L1",
            "45 Francis",
            "LABS",
            "Ultrasound Floor L1",
            "Lab C004L1");
    Node lab3 =
        new Node(
            "CLABS003L1",
            2290,
            1284,
            "L1",
            "45 Francis",
            "LABS",
            "Nuclear Medicine Floor L1",
            "Lab C003L1");
    Node lab5 =
        new Node(
            "CLABS005L1",
            2770,
            1284,
            "L1",
            "45 Francis",
            "LABS",
            "CSIR MRI Floor L1",
            "Lab C005L1");

    nodeList.add(hall2);
    nodeList.add(hall3);
    nodeList.add(hall6);
    nodeList.add(hall7);
    nodeList.add(hall8);
    nodeList.add(lab3);
    nodeList.add(lab4);
    nodeList.add(lab5);
    nodeList.add(conf2);
    nodeList.add(conf3);
    nodeList.add(dept4);
    nodeList.add(rest2);
    nodeList.add(serv1);
    nodeList.add(elevk);
  }

  // method to print the nodes in order (will use switch to convert int to the nodes)
  public static void printNodePath(List<Integer> p, LinkedList<Node> nodeList) {
    System.out.println(
        "The path from starting point: "
            + nodeList.get(p.get(0)).toStringID()
            + " to the destination: "
            + nodeList.get(p.get(p.size() - 1)).toStringID()
            + " is:");
    for (int i = 0; i < p.size(); i++) {
      Node current;
      current = nodeList.get(p.get(i));
      String nodeName = current.getNodeID();
      if (i == p.size() - 1) {
        System.out.println(nodeName);
      } else {
        System.out.print(nodeName + " --> ");
      }
    }
  }

  public static void convertToInt(String s, String e, LinkedList<Node> nodeList) {

    for (int i = 0; i < nodeList.size(); i++) {
      if (nodeList.get(i).getNodeID().equals(s)) {
        startNodeVal = i;
      } else if (nodeList.get(i).getNodeID().equals(e)) {
        endNodeVal = i;
      }
    }
  }

  // main method to test the algorithm
  public static void main(String[] args) throws IOException {
    LinkedList<Node> listOfNodes = new LinkedList<>();

    int[][] graph = { // Hardcoded graph
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0}, // Hall2
      {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}, // Hall3
      {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0}, // Hall6
      {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Hall7
      {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, // Hall8
      {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // Lab3
      {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // Lab4
      {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Lab5
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // Conf2
      {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Conf3
      {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Dept4
      {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Rest2
      {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // Serv1
      {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0} // ElevK
    };
    // H2 H3 H6 H7 H8 L3 L4 L5 C2 C3 D4 R2 S1 EK

    createNodes(listOfNodes);

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter start point: ");
    String startNode = reader.readLine();

    System.out.println("Enter destination: ");
    String endNode = reader.readLine();

    convertToInt(startNode, endNode, listOfNodes);

    Pathfinding bfs = new Pathfinding(graph);
    List<Integer> path = bfs.bfsBacktrack(startNodeVal, endNodeVal);
    if (path != null) {
      System.out.println("Path from " + startNode + " to " + endNode + ": " + path);
    } else {
      System.out.println("No path found from " + startNode + " to " + endNode + ".");
    }

    printNodePath(path, listOfNodes);
  }
}
