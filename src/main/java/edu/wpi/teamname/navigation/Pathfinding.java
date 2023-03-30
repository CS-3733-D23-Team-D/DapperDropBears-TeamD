package edu.wpi.teamname.navigation;

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Pathfinding {
  private static Node[] Nodes = new Node[46];
  private static Edge[] Edges = new Edge[46];
  private static String NodesFile =
      "C:\\Users\\artur\\IdeaProjects\\DapperDropBears-TeamD\\src\\main\\java\\edu\\wpi\\teamname\\navigation\\L1Nodes.csv";
  private static String EdgesFile =
      "C:\\Users\\artur\\IdeaProjects\\DapperDropBears-TeamD\\src\\main\\java\\edu\\wpi\\teamname\\navigation\\L1Edges.csv";

  public static void main(String[] args) throws Exception {

    ArrayList<String> NodeLines = ReadCsvLines(NodesFile);
    ArrayList<String> EdgeLines = ReadCsvLines(EdgesFile);

    initializeNodes(NodeLines);
    initializeEdges(EdgeLines);
    System.out.println(Nodes[0].getXCord());
    System.out.println(Edges[0].getEdgeID());
  }

  public static ArrayList<String> ReadCsvLines(String filename) throws Exception {
    // Create a csv scanner object
    Scanner sc = new Scanner(new File(filename));

    // Pull the csv file lines
    ArrayList<String> lines = new ArrayList<String>();
    while (sc.hasNextLine()) {
      lines.add(sc.nextLine());
    }

    // Remove the tittles
    lines.remove(0);
    return lines;
  }

  public static void initializeNodes(ArrayList<String> NodeLines) {
    // Initialize the nodes with the node lines data
    int i = 0;
    while (!NodeLines.isEmpty()) {
      String[] I = NodeLines.get(0).split(",");
      NodeLines.remove(0);
      Nodes[i] =
          new Node(
              I[0], Integer.parseInt(I[1]), Integer.parseInt(I[2]), I[3], I[4], I[5], I[6], I[7]);
      i++;
    }
  }

  public static void initializeEdges(ArrayList<String> EdgeLines) {
    // Initialize the nodes with the node lines data
    int i = 0;
    while (!EdgeLines.isEmpty()) {
      String[] E = EdgeLines.get(0).split(",");
      EdgeLines.remove(0);
      Edges[i] = new Edge(E[0], E[1], E[2]);
      i++;
    }
  }
}
