package edu.wpi.teamname.navigation;

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Pathfinding {
  private static List<Node> Nodes = new ArrayList<Node>();
  private static List<Edge> Edges = new ArrayList<Edge>();
  private static String NodesFile = "C:\\Users\\Aleksandr Samarin\\Downloads\\Node.csv";
  private static String EdgesFile = "C:\\Users\\Aleksandr Samarin\\Downloads\\Edge.csv";

  public static void main(String[] args) throws Exception {
    initializeNodes(ReadCsvLines(NodesFile));
    initializeEdges(ReadCsvLines(EdgesFile));

    Scanner sc = new Scanner(System.in); // System.in is a standard input stream
    System.out.print("Enter Start NodeID: ");
    int startNodeID = sc.nextInt();

    System.out.print("Enter End NodeID: ");
    int endNodeID = sc.nextInt();

    Graph graph = new Graph(Nodes, Edges);

    //(NodeID - 100)/5
    graph.setStart(graph.getNodes().get((startNodeID - 100) / 5)); // To get index
    graph.setTarget(graph.getNodes().get((endNodeID - 100) / 5));
    AStar.aStar(graph);
    AStar.printPath(graph.getTarget());
  }

  public static ArrayList<String> ReadCsvLines(String filename) throws Exception {
    // Create a csv scanner object
    Scanner sc = new Scanner(new File(filename));

    // Pull the csv file lines
    ArrayList<String> lines = new ArrayList<String>();
    while (sc.hasNextLine()) {
      lines.add(sc.nextLine());
    }

    // Remove the title
    lines.remove(0);
    return lines;
  }

  public static void initializeNodes(ArrayList<String> NodeLines) {
    // Initialize the nodes with the node lines data
    int i = 0;
    while (!NodeLines.isEmpty()) {
      String[] I = NodeLines.get(0).split(",");
      NodeLines.remove(0);
      Nodes.add(
          new Node(
              Integer.parseInt(I[0]), Integer.parseInt(I[1]), Integer.parseInt(I[2]), I[3], I[4]));
      i++;
    }
  }

  public static void initializeEdges(ArrayList<String> EdgeLines) {
    // Initialize the nodes with the node lines data
    int i = 0;
    while (!EdgeLines.isEmpty()) {
      String[] E = EdgeLines.get(0).split(",");
      EdgeLines.remove(0);
      Edges.add(new Edge(Integer.parseInt(E[0]), Integer.parseInt(E[1])));
      i++;
    }
  }
}
