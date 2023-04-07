package edu.wpi.teamname.navigation;

import edu.wpi.teamname.database.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Node implements Comparable<Node> {
  @Getter @Setter int id;
  @Getter @Setter private String floor;
  @Getter @Setter private String building;
  @Getter @Setter private int x;
  @Getter @Setter private int y;
  @Getter @Setter private Node parent = null;
  @Getter @Setter private List<Node> neighbors;
  @Getter @Setter private List<Edge> edges;
  @Getter @Setter private double f = Double.MAX_VALUE;
  @Getter @Setter private double g = Double.MAX_VALUE;
  @Getter @Setter private double h;

  Node(int ID, int x, int y, String Floor, String Building) {
    this.x = x;
    this.y = y;
    this.floor = Floor;
    this.building = Building;
    this.h = 0;
    this.id = ID;
    this.neighbors = new ArrayList<>();
    this.edges = new ArrayList<>();
  }

  @Override
  public int compareTo(Node n) {
    return Double.compare(this.f, n.f);
  }

  /**
   * * Gets all of the nodes in the database and puts them into an array list
   *
   * @return An array list of all the nodes in the database
   * @throws SQLException
   */
  public static ArrayList<Node> getAllNodes() throws SQLException {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.DbConnection();
    ArrayList<Node> list = new ArrayList<Node>();

    try (connection) {
      String query = "SELECT * FROM \"Node\"";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("nodeID");
        int xcoord = rs.getInt("xcoord");
        int ycoord = rs.getInt("ycoord");
        String floor = rs.getString("floor");
        String building = rs.getString("building");
        list.add(new Node(id, xcoord, ycoord, floor, building));
      }
    }
    return list;
  }

  public void addEdge(Edge edge, Node s, Node e) {
    if (!this.edges.contains(edge)) {
      edges.add(edge);

      if (this.id == edge.startNodeID) {
        this.neighbors.add(e);
      } else {
        this.neighbors.add(s);
      }
    }
  }

  public double findWeight(Node b) {
    int x1 = this.x;
    int x2 = b.getX();
    int y1 = this.y;
    int y2 = b.getY();

    double x = Math.pow((x2 - x1), 2);
    double y = Math.pow((y2 - y1), 2);
    return Math.sqrt(x + y);
  }

  public String toString() {
    String nei = "";
    for (Node n : neighbors) {
      nei += " " + Integer.toString(n.getId());
    }
    return "NodeID:" + id + " Xcord:" + x + " Ycord:" + y + " Heu: " + h + "Neighbors" + nei;
  }

  public double calculateHeuristic(Node target) {
    // Heuristic will return distance from target
    return Math.sqrt(
        (target.getX() - this.x) * (target.getX() - this.x)
            + (target.getY() - this.y) * (target.getY() - this.y));
  }
}
