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
          node = parent[node];
        }
        path.add(start);
        Collections.reverse(path);
        return path;
      }
      for (int i = 0; i < graph.length; i++) {
        if (graph[curr][i] == 1 && !visited[i]) { // check if node is adjacent and not visited
          visited[i] = true;
          parent[i] = curr;
          queue.add(i);
        }
      }
    }
    System.out.println(
        "There is no path from the start ot the end destination! Please run and try again.");
    System.exit(0);
    return null; // destination not reachable from start
  }

  public static void createNodes(LinkedList<Node> nodeList) {

    // This is the initialization of the nodes (Will be changed to upload a csv file,
    // auto-formatting also makes teh code a lot longer)
    Node conf1 =
        new Node(
            "CCONF001L1",
            2255,
            849,
            "L1",
            "45 Francis",
            "CONF",
            "Anesthesia Conf Floor L1",
            "Conf C001L1");
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

    Node dept2 =
        new Node(
            "CDEPT002L1",
            1980,
            844,
            "L1",
            "Tower",
            "DEPT",
            "Day Surgery Family Waiting Floor L1",
            "Department C002L1");
    Node dept3 =
        new Node(
            "CDEPT003L1",
            1845,
            844,
            "L1",
            "Tower",
            "DEPT",
            "Day Surgery Family Waiting Exit Floor L1",
            "Department C003L1");
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

    Node hall1 =
        new Node(
            "CHALL001L1", 1732, 924, "L1", "Tower", "HALL", "Hallway 1 Floor L1", "Hallway C001L1");
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
    Node hall4 =
        new Node(
            "CHALL004L1",
            2770,
            1070,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 4 Floor L1",
            "Hallway C004L1");
    Node hall5 =
        new Node(
            "CHALL005L1",
            1750,
            1284,
            "L1",
            "Tower",
            "HALL",
            "Hallway 5 Floor L1",
            "Hallway C005L1");
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
    Node hall9 =
        new Node(
            "CHALL009L1",
            2220,
            904,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 9 Floor L1",
            "Hallway C009L1");
    Node hall10 =
        new Node(
            "CHALL010L1",
            2265,
            904,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 10 Floor L1",
            "Hallway C010L1");
    Node hall11 =
        new Node(
            "CHALL011L1",
            2360,
            849,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 11 Floor L1",
            "Hallway C011L1");
    Node hall12 =
        new Node(
            "CHALL012L1",
            2130,
            904,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 12 Floor L1",
            "Hallway C012L1");
    Node hall13 =
        new Node(
            "CHALL013L1",
            2130,
            844,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 13 Floor L1",
            "Hallway C013L1");
    Node hall14 =
        new Node(
            "CHALL014L1",
            1845,
            924,
            "L1",
            "Tower",
            "HALL",
            "Hallway 14 Floor L1",
            "Hallway C014L1");
    Node hall15 =
        new Node(
            "CHALL015L1",
            2300,
            849,
            "L1",
            "45 Francis",
            "HALL",
            "Hallway 15 Floor L1",
            "Hallway C015L1");

    Node lab1 =
        new Node(
            "CLABS001L1",
            1965,
            1284,
            "L1",
            "Tower",
            "LABS",
            "Outpatient Fluoroscopy Floor L1",
            "Lab C001L1");
    Node lab2 =
        new Node(
            "CLABS002L1", 1750, 1090, "L1", "Tower", "LABS", "Pre-Op PACU Floor L1", "Lab C002L1");
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

    Node rest1 =
        new Node(
            "CREST001L1",
            1732,
            1019,
            "L1",
            "Tower",
            "REST",
            "Restroom L Elevator Floor L1",
            "RestroomC001L1");
    Node rest2 =
        new Node(
            "CREST002L1",
            2065,
            1284,
            "L1",
            "Tower",
            "REST",
            "Restroom M Elevator Floor L1",
            "RestroomC002L1");
    Node rest3 =
        new Node(
            "CREST003L1",
            2300,
            879,
            "L1",
            "45 Francis",
            "REST",
            "Restroom K Elevator Floor L1",
            "RestroomC003L1");
    Node rest4 =
        new Node(
            "CREST004L1",
            2770,
            1160,
            "L1",
            "45 Francis",
            "REST",
            "Restroom H Elevator Floor L1",
            "RestroomC004L1");

    Node retl1 =
        new Node(
            "CRETL001L1",
            2185,
            904,
            "L1",
            "45 Francis",
            "RETL",
            "Vending Machine 1 L1",
            "Retail C001L1");

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
    Node serv2 =
        new Node(
            "CSERV001L2",
            2015,
            1280,
            "L2",
            "45 Francis",
            "SERV",
            "Interpreter Services Floor L2",
            "Service C001L2");

    Node gele =
        new Node(
            "GELEV00QL1",
            1637,
            2116,
            "L1",
            "Shapiro",
            "ELEV",
            "Elevator Q MapNode 7 Floor L1",
            "Elevator Q L1");

    Node gexi =
        new Node(
            "GEXIT001L1",
            1702,
            2260,
            "L1",
            "Shapiro",
            "EXIT",
            "Fenwood Road Exit MapNode 1 Floor L1",
            "Fenwood Road EntranceExit L1");

    Node ghall2 =
        new Node(
            "GHALL002L1",
            1702,
            2167,
            "L1",
            "Shapiro",
            "HALL",
            "Hallway MapNode 2 Floor L1",
            "Hall");
    Node ghall3 =
        new Node(
            "GHALL003L1",
            1688,
            2167,
            "L1",
            "Shapiro",
            "HALL",
            "Hallway MapNode 3 Floor L1",
            "Hall");
    Node ghall4 =
        new Node(
            "GHALL004L1",
            1666,
            2167,
            "L1",
            "Shapiro",
            "HALL",
            "Hallway MapNode 4 Floor L1",
            "Hall");
    Node ghall5 =
        new Node(
            "GHALL005L1",
            1688,
            2131,
            "L1",
            "Shapiro",
            "HALL",
            "Hallway MapNode 5 Floor L1",
            "Hall");
    Node ghall6 =
        new Node(
            "GHALL006L1",
            1665,
            2116,
            "L1",
            "Shapiro",
            "HALL",
            "Hallway MapNode 6 Floor L1",
            "Hall");

    Node gstai1 =
        new Node(
            "GSTAI008L1",
            1720,
            2131,
            "L1",
            "Shapiro",
            "STAI",
            "Stairs MapNode 8 Floor L1",
            "L1 Stairs");

    Node welev1 =
        new Node(
            "WELEV00HL1",
            2715,
            1070,
            "L1",
            "45 Francis",
            "ELEV",
            "Elevator H Floor L1",
            "Elevator HL1");
    Node welev2 =
        new Node(
            "WELEV00JL1",
            2360,
            799,
            "L1",
            "45 Francis",
            "ELEV",
            "Elevator J Floor L1",
            "Elevator JL1");
    Node welev3 =
        new Node(
            "WELEV00KL1",
            2220,
            974,
            "L1",
            "45 Francis",
            "ELEV",
            "Elevator K Floor L1",
            "Elevator KL1");
    Node welev4 =
        new Node(
            "WELEV00LL1", 1785, 924, "L1", "Tower", "ELEV", "Elevator L Floor L1", "Elevator LL1");
    Node welev5 =
        new Node(
            "WELEV00ML1", 1820, 1284, "L1", "Tower", "ELEV", "Elevator M Floor L1", "Elevator ML1");

    // Adding all the nodes to a linked list
    nodeList.add(conf1);
    nodeList.add(conf2);
    nodeList.add(conf3);

    nodeList.add(dept2);
    nodeList.add(dept3);
    nodeList.add(dept4);

    nodeList.add(hall1);
    nodeList.add(hall2);
    nodeList.add(hall3);
    nodeList.add(hall4);
    nodeList.add(hall5);
    nodeList.add(hall6);
    nodeList.add(hall7);
    nodeList.add(hall8);
    nodeList.add(hall9);
    nodeList.add(hall10);
    nodeList.add(hall11);
    nodeList.add(hall12);
    nodeList.add(hall13);
    nodeList.add(hall14);
    nodeList.add(hall15);

    nodeList.add(lab1);
    nodeList.add(lab2);
    nodeList.add(lab3);
    nodeList.add(lab4);
    nodeList.add(lab5);

    nodeList.add(rest1);
    nodeList.add(rest2);
    nodeList.add(rest3);
    nodeList.add(rest4);

    nodeList.add(retl1);

    nodeList.add(serv1);
    nodeList.add(serv2);

    nodeList.add(gele);

    nodeList.add(gexi);

    nodeList.add(ghall2);
    nodeList.add(ghall3);
    nodeList.add(ghall4);
    nodeList.add(ghall5);
    nodeList.add(ghall6);

    nodeList.add(gstai1);

    nodeList.add(welev1);
    nodeList.add(welev2);
    nodeList.add(welev3);
    nodeList.add(welev4);
    nodeList.add(welev5);
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

      // Prints out the path
      if (i == p.size() - 1) {
        System.out.println(nodeName);
      } else {
        System.out.print(nodeName + " --> ");
      }
    }
  }

  // Method to take the string input and convert it to an int for the bfs to run
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
      // The matrix will not be hardcoded for the next prototype, we just have it like this for now.
      // It goes in order from the nodes on the csv for floor 1
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, //
      {
        0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0
      },
      {
        0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0
      }, // hall8
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0
      }, // hall11
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0
      },
      {
        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // hall15
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // lab3
      {
        0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // rest1
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // v
      {
        0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // serv1
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0
      }, // e
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // x
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // ghall2
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0
      }, // ghall5
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0
      },
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0
      }, // stair
      {
        0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // welevh
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // j
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // k
      {
        0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // l
      {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }, // m
    };
    // conf  3  dept  4  hall  3  4  5  6  7  8  9  0  1  2  3  4  5  lab   3  4  5  rest  3  4  v
    // serv  e  x  ghall 4  5  6  s  welev k  l  m

    createNodes(listOfNodes);
    String startNode = "";
    String endNode = "";
    Pathfinding bfs = new Pathfinding(graph);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // get start and endpoint
    System.out.println("Enter start point: ");
    startNode = reader.readLine();

    System.out.println("Enter destination: ");
    endNode = reader.readLine();

    startNode = startNode.toUpperCase();
    endNode = endNode.toUpperCase();

    convertToInt(startNode, endNode, listOfNodes);

    List<Integer> path = bfs.bfsBacktrack(startNodeVal, endNodeVal);
    if (path != null) {
      System.out.println("Path from " + startNode + " to " + endNode + ": " + path);
    } else {
      System.out.println("No path found from " + startNode + " to " + endNode + ".");
    }

    printNodePath(path, listOfNodes);
  }
}
