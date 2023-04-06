package edu.wpi.teamname.navigation;

import java.io.*;
import java.util.*;
import lombok.Getter;

public class Pathfinding {
  private static List<Node> Nodes = new ArrayList<Node>();
  private static List<Edge> Edges = new ArrayList<Edge>();
  //  private static ArrayList<String> linesOfNodeInfo = new ArrayList<String>();
  //  private static ArrayList<String> linesOfEdgeInfo = new ArrayList<String>();
  //  private static String NodesFile =
  //
  // "/Users/ryan/Documents/GitHub/DapperDropBears-TeamD/src/main/java/edu/wpi/teamname/navigation/Node.csv";
  //  private static String EdgesFile =
  //
  // "/Users/ryan/Documents/GitHub/DapperDropBears-TeamD/src/main/java/edu/wpi/teamname/navigation/Edge.csv";

  @Getter private Graph graph;

  @Getter private AStar aStar;

  public Pathfinding() throws Exception {
    // initializeNodes(ReadCsvLines(NodesFile));
    // initializeEdges(ReadCsvLines(EdgesFile));
    ArrayList<Node> listOfNodes = Node.getAllNodes();
    ArrayList<Edge> listOfEdges = Edge.getAllEdges();

    //    for (int i = 0; i < listOfNodes.size(); i++) {
    //      linesOfNodeInfo.add(nodeToString(listOfNodes.get(i)));
    //    }

    //    for (int i = 0; i < listOfEdges.size(); i++) {
    //      linesOfEdgeInfo.add(edgeToString(listOfEdges.get(i)));
    //    }

    initializeNodes(listOfNodes);
    initializeEdges(listOfEdges);

    //    boolean flag = true;
    //    boolean flagt = true;
    // Commented for now
    //    int startNodeID = 0;
    //    int endNodeID = 0;
    //
    //    Scanner sc = new Scanner(System.in); // System.in is a standard input stream
    //
    //    while (flag) {
    //
    //      System.out.print("Enter Start NodeID: ");
    //      startNodeID = sc.nextInt();
    //
    //      if (startNodeID < 100 || startNodeID > 3000 || startNodeID % 5 != 0) {
    //        System.out.println("Not an acceptable start node. Please run program to try again.");
    //        // System.exit(0);
    //      } else {
    //        flag = false;
    //      }
    //    }
    //
    //    while (flagt) {
    //
    //      System.out.print("Enter End NodeID: ");
    //      endNodeID = sc.nextInt();
    //
    //      if (endNodeID < 100 || endNodeID > 3000 || endNodeID % 5 != 0) {
    //        System.out.println("Not an acceptable end node. Please run program to try again.");
    //        // System.exit(0);
    //      } else {
    //        flagt = false;
    //      }
    //    }
    graph = new Graph(Nodes, Edges);

    //        graph.setStart(graph.getNodes().get(0));
    //        graph.setTarget(graph.getNodes().get(100));
    //        AStar.aStar(graph);
    //        AStar.printPath(graph.getTarget());
  }


  //  public static ArrayList<String> ReadCsvLines(String filename) throws Exception {
  //    // Create a csv scanner object
  //    Scanner sc = new Scanner(new File(filename));
  //
  //    // Pull the csv file lines
  //    ArrayList<String> lines = new ArrayList<String>();
  //    while (sc.hasNextLine()) {
  //      lines.add(sc.nextLine());
  //    }
  //
  //    // Remove the title
  //    lines.remove(0);
  //    return lines;
  //  }

  // Method to convert node data to string
  //  public static String nodeToString(Node n) {
  //    String nodeInfo = "";
  //    nodeInfo =
  //        n.getId() + "," + n.getX() + "," + n.getY() + "," + n.getFloor() + "," +
  // n.getBuilding();
  //    return nodeInfo;
  //  }
  //
  //  // Method to convert edge data to string
  //  public static String edgeToString(Edge e) {
  //    String edgeInfo = "";
  //    edgeInfo = e.getStartNodeID() + "," + e.getEndNodeID();
  //    return edgeInfo;
  //  }

  // Taking strings, parse the data coming from
  public static void initializeNodes(ArrayList<Node> NodeLines) {
    // Initialize the nodes with the node lines data
    int i = 0;
    while (!NodeLines.isEmpty()) {
      // String[] I = NodeLines.get(0).split(",");
      // NodeLines.remove(0);
      Nodes.add(
          new Node(
              NodeLines.get(0).getId(),
              NodeLines.get(0).getX(),
              NodeLines.get(0).getY(),
              NodeLines.get(0).getFloor(),
              NodeLines.get(0).getBuilding()));
      NodeLines.remove(0);
    }
  }

  // Initializes the edges given the list of string containing edge info
  public static void initializeEdges(ArrayList<Edge> EdgeLines) {
    // Initialize the nodes with the node lines data
    int i = 0;
    while (!EdgeLines.isEmpty()) {
      // String[] E = EdgeLines.get(0).split(",");
      // EdgeLines.remove(0);
      Edges.add(new Edge(EdgeLines.get(0).getStartNodeID(), EdgeLines.get(0).getEndNodeID()));
      EdgeLines.remove(0);
    }
  }
}
